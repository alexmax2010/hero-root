package com.kruger.hero;

import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.kruger.hero.config.BaseConfiguration;
import ec.com.kruger.security.sso.springboot2.configuration.SecurityKeycloakConfiguration;
import ec.com.kruger.spring.metric.config.DefaultApplicationContextMetricConfig;
import ec.com.kruger.spring.ws.config.WebConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * HeroApplication.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.kruger.hero")
@Import({ BaseConfiguration.class, DefaultApplicationContextMetricConfig.class, WebConfig.class,
    SecurityKeycloakConfiguration.class })
@Slf4j
public class HeroApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

    /**
     * Main to run app.
     *
     * @param args args to pass app
     */
    public static void main(String... args) {
        try {
            SpringApplication app = new SpringApplication(HeroApplication.class);
            app.run(args);
        } catch (Exception throwable) {
            if (!Objects.equals(throwable.getClass().getName(),
                "org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")
                && log.isErrorEnabled()) {
                log.error("********************************Ha ocurrido una exception****************************");
                log.error("Exception: " + throwable.toString());
                log.error("Root Cause: " + ExceptionUtils.getRootCause(throwable).toString());
            }
        }
    }

    /**
     * commandLineRunner.
     *
     * @param ctx the ctx
     * @return CommandLineRunner instance
     */
    @Bean
    public CommandLineRunner commandLineRunner(ListableBeanFactory ctx) {
        if (log.isDebugEnabled()) {
            log.debug("Beans Loaded by Spring Boot:{}", ctx.getBeanDefinitionCount());
        }
        return args -> {
            if (log.isDebugEnabled()) {
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    log.debug("Bean:{}", beanName);
                }
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

}
