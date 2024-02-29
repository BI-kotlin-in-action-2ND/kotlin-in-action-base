package kr.bi.lottospring.repostitory

import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.QueryFactoryImpl
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreatorImpl
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import kr.bi.lottospring.entity.QUsers
import kr.bi.lottospring.entity.Users
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :
    JpaRepository<Users, Long>,
    UserCustomCriteriaRepository,
    UserCustomQuerydslRepository,
    UserCustomJdslRepository {
    fun findByName(name: String): Users

    @Query("SELECT u FROM Users u WHERE name = :name")
    fun findByNameCustom(name: String): Users

    fun findByNameAndAddressAndEmailLikeOrderByIdDesc(
        name: String,
        address: String,
        email: String,
    ): List<Users>
}

interface UserCustomCriteriaRepository {
    fun findByNameCriteria(name: String): Users

    fun findByNameAndAddressAndEmailLikeOrderByIdDescCriteria(
        name: String,
        address: String,
        email: String,
    ): List<Users>
}

class UserCustomCriteriaRepositoryImpl(private val em: EntityManager) : UserCustomCriteriaRepository {
    override fun findByNameCriteria(name: String): Users {
        val criteriaBuilder = em.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Users::class.java)
        val root = criteriaQuery.from(Users::class.java)
        criteriaQuery.select(root)
        criteriaQuery.where(criteriaBuilder.equal(root.get<String>("name"), name))
        return em.createQuery(criteriaQuery).singleResult
    }

    override fun findByNameAndAddressAndEmailLikeOrderByIdDescCriteria(
        name: String,
        address: String,
        email: String,
    ): List<Users> {
        val criteriaBuilder = em.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Users::class.java)
        val root = criteriaQuery.from(Users::class.java)
        criteriaQuery.select(root)
        criteriaQuery.where(
            criteriaBuilder.and(
                criteriaBuilder.equal(root.get<String>("name"), name),
                criteriaBuilder.equal(root.get<String>("address"), address),
                criteriaBuilder.like(root.get("email"), email),
            ),
        )
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get<Long>("id")))
        return em.createQuery(criteriaQuery).resultList
    }
}

@Configuration
class JpaQueryFactoryConfig {
    @Bean
    fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}

interface UserCustomQuerydslRepository {
    fun findByNameQuerydsl(name: String): Users?
}

class UserCustomQuerydslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserCustomQuerydslRepository {
    override fun findByNameQuerydsl(name: String): Users? {
        val users = QUsers.users
        return jpaQueryFactory
            .selectFrom(users)
            .where(users.name.eq(name))
            .fetchOne()
    }
}

@Configuration
class QueryFactoryConfig {
    @Bean
    fun queryFactory(entityManager: EntityManager): QueryFactory {
        return QueryFactoryImpl(
            criteriaQueryCreator = CriteriaQueryCreatorImpl(entityManager),
            subqueryCreator = SubqueryCreatorImpl(),
        )
    }
}

interface UserCustomJdslRepository {
    fun findByNameJdsl(name: String): Users
}

class UserCustomJdslRepositoryImpl(
    private val queryFactory: QueryFactory,
) : UserCustomJdslRepository {
    override fun findByNameJdsl(name: String): Users =
        queryFactory.listQuery<Users> {
            select(entity(Users::class))
            from(entity(Users::class))
            where(col(Users::name).equal(name))
            // select *
            // from Users
            // where name = name
        }[0]
}
