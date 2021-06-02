package com.kruger.hero.config;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.kruger.hero.entity.HeroEntity;
import com.kruger.hero.repository.HeroRepository;
import ec.com.kruger.spring.orm.jpa.config.SecurityAuditListenerConfig;

/**
 * Base spring configuration.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@EntityScan(basePackageClasses = { HeroEntity.class })
@EnableJpaRepositories(basePackageClasses = HeroRepository.class)
@ComponentScan(basePackages = "com.kruger.hero")
@EnableTransactionManagement
@Import(SecurityAuditListenerConfig.class)
public class BaseConfiguration {

    /**
     * <p>
     * transactionManager.
     * </p>
     *
     * @param emf a {@link javax.persistence.EntityManagerFactory} object.
     * @return a {@link org.springframework.transaction.PlatformTransactionManager}
     *         object.
     */
    @Bean
    @Autowired
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

}
