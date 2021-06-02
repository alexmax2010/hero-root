package com.kruger.hero.repository;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;

/**
 * HeroRepositoryTest.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroRepositoryTest {

    @Autowired
    private IHeroRepository repository;

    @Before
    public void setup() {
    }

    @Test
    public void testFindAll() {
        List<HeroVO> response = this.repository.findAll();
        Assert.assertNotNull(response);
    }

    @Test
    public void testFindById() {
        Integer heroCode = 1;
        HeroVO response = this.repository.findById(heroCode);
        Assert.assertEquals(heroCode, response.getHeroCode());
    }

    @Test
    public void testFindByName() {
        String findName = "man";
        FindNameVO request = FindNameVO.builder().name(findName).build();
        List<HeroVO> response = this.repository.findByName(request);
        assertThat(response.get(1).getName(), containsStringIgnoringCase(findName));
    }

    @Test
    public void testUpdate() {
        this.repository.update(HeroVO.builder().heroCode(1).name("SUPERMAN EDIT").build());
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        this.repository.delete(HeroIdVO.builder().heroCode(1).build());
        Assert.assertTrue(true);
    }
}
