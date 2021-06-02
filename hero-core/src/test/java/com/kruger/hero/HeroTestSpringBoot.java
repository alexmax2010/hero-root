package com.kruger.hero;

import java.util.Objects;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.kruger.hero.config.BaseConfiguration;
import ec.com.kruger.spring.boot.test.SecurityUserInfoTestConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * HeroTestSpringBoot.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@Import({ BaseConfiguration.class, SecurityUserInfoTestConfiguration.class })
@SpringBootApplication(scanBasePackages = { "com.kruger.hero" })
public class HeroTestSpringBoot {

    /**
     * Main run spring boot app.
     *
     * @param args an array of {@link String} objects.
     */
    public static void main(String... args) {

        try {
            SpringApplication app = new SpringApplication(HeroTestSpringBoot.class);
            app.run(args);

        } catch (Exception throwable) {
            if (!Objects.equals(throwable.getClass().getName(),
                "org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")
                && log.isErrorEnabled()) {
                log.error(
                    "*************************************Ha ocurrido una exception**********************************");
                log.error("Exception: " + throwable.toString());
                log.error("Root Cause: " + ExceptionUtils.getRootCause(throwable).toString());
            }
        }
    }
}
