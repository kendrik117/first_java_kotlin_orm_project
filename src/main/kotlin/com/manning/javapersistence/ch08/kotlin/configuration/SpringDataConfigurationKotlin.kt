package com.manning.javapersistence.ch08.kotlin.configuration

import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@EnableJpaRepositories("com.manning.javapersistence.ch08.kotlin.repository")
open class SpringDataConfigurationKotlin {
    @Bean
    open fun dataSource(): DataSource = DriverManagerDataSource().apply {
        setDriverClassName("com.mysql.cj.jdbc.Driver")
        url = "jdbc:mysql://${env("DB_HOST", "localhost")}:${env("DB_PORT", "3306")}/${databaseName("CH_8_K1")}?createDatabaseIfNotExist=true&serverTimezone=UTC"
        username = setting("db.username", "DB_USERNAME", "karim")
        password = setting("db.password", "DB_PASSWORD", "Mypass@123")
    }

    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory) =
        JpaTransactionManager(entityManagerFactory)

    @Bean
    open fun jpaVendorAdapter(): JpaVendorAdapter = HibernateJpaVendorAdapter().apply {
        setDatabase(Database.MYSQL)
        setShowSql(true)
    }

    @Bean
    open fun entityManagerFactory() = LocalContainerEntityManagerFactoryBean().apply {
        dataSource = dataSource()
        setJpaVendorAdapter(jpaVendorAdapter())
        setPackagesToScan("com.manning.javapersistence.ch08.kotlin.model")
        jpaPropertyMap = mapOf(
            "hibernate.hbm2ddl.auto" to "create",
            "hibernate.dialect" to "org.hibernate.dialect.MySQL8Dialect"
        )
    }

    private fun databaseName(baseName: String) =
        "${baseName}_${System.getProperty("app.db.suffix", "V_ITest")}"

    private fun env(name: String, defaultValue: String) =
        System.getenv(name) ?: defaultValue

    private fun setting(propertyName: String, envName: String, defaultValue: String) =
        System.getProperty(propertyName) ?: env(envName, defaultValue)
}
