package com.oracle.db23c.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.oracle.db23c.mapper")
public class OracleDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }

//    @Bean
//    @Primary
//    public SqlSessionFactory getSqlSessionFactory(HikariDataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        // factoryBean.setDataSource(new Log4jdbcProxyDataSource(dataSource));
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setTypeAliasesPackage("com.oracle.db23c.po");
////        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
////                .getResources("classpath:mapper/**/*.xml"));
//
//        return factoryBean.getObject();
//    }


}
