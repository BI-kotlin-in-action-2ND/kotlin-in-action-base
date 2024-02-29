package kr.bi.lottospring.config

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource

@Configuration
@ComponentScan
class AppConfig {
    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        val res: Array<Resource> = PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*SQL.xml")
        sqlSessionFactoryBean.setMapperLocations(*res)
        return sqlSessionFactoryBean.getObject()
    }

    @Bean
    fun sqlSessionTemplate(sqlSessionFactory: SqlSessionFactory?): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
