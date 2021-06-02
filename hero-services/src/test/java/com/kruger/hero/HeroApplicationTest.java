package com.kruger.hero;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.junit4.SpringRunner;
import com.kruger.hero.config.BaseConfiguration;

/**
 * HeroApplicationTest.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@Import(BaseConfiguration.class)
@SpringBootTest(classes = { HeroApplication.class, BaseConfiguration.class })
public class HeroApplicationTest {
    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    @Test
    public void testApplicationContextLoaded() {
        assertThat(propertySourcesPlaceholderConfigurer).isNotNull();
    }

    @Test
    public void testApplicationStarts() {
        HeroApplication.main("--spring.main.web-environment=true",
            " -Dspring-boot.run.jvmArguments=\"-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005\"");
        assertThat(propertySourcesPlaceholderConfigurer).isNotNull();
    }

    @Test
    public void testApplicationStartsNull() {
        HeroApplication.main("--spring.main.web-envirent=false");
        assertThat(propertySourcesPlaceholderConfigurer).isNotNull();
    }
}
