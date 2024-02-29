package kr.bi.lottospring.dao

import kr.bi.lottospring.entity.Users
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.stereotype.Repository

@Repository
class UserDao(
    private val sqlSession: SqlSessionTemplate,
) {
    fun findByNameMybatis(name: String): Users {
        return sqlSession.selectOne("main.getUserByName", name) as Users
    }
}
