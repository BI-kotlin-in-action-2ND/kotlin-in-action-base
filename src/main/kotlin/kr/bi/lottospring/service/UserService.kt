package kr.bi.lottospring.service

import kr.bi.lottospring.dao.UserDao
import kr.bi.lottospring.entity.Users
import kr.bi.lottospring.repostitory.UserRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service

@Service
class UserService(
    private val jdbcTemplate: JdbcTemplate,
    private val userDao: UserDao,
    private val userRepository: UserRepository,
) {
    fun init() {
        jdbcTemplate.update("delete from users")
        jdbcTemplate.update(
            "insert into users (name, email, address) values (?, ?, ?)",
            "jayden",
            "example@example.com",
            "seoul",
        )
    }

    fun findByNameJDBC(name: String): Users {
        init()
        val sql = "SELECT * FROM users WHERE name = ?"
        return jdbcTemplate.queryForObject(
            sql,
            name,
        ) { rs, _ ->
            Users(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("address"),
            )
        }
    }

    fun findByNameMybatis(name: String): Users {
        init()
        return userDao.findByNameMybatis(name)
    }

    fun findByName(name: String): Users {
        init()
        return userRepository.findByName(name)
    }

    fun findByNameCustom(name: String): Users {
        init()
        return userRepository.findByNameCustom(name)
    }

    fun findByNameCriteria(name: String): Users {
        init()
        return userRepository.findByNameCriteria(name)
    }

    fun findByNameQuerydsl(name: String): Users {
        init()
        return userRepository.findByNameQuerydsl(name) ?: throw Exception("User Not Found")
    }
}
