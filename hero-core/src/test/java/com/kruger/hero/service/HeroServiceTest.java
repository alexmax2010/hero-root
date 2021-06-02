package com.kruger.hero.service;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.repository.HeroRepository;

/**
 * HeroServiceTest.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class HeroServiceTest {
    @InjectMocks
    private HeroService service;

    @Mock
    private HeroRepository repository;

    @Before
    public void setup() {
        List<HeroVO> response = new ArrayList<>();
        response.add(HeroVO.builder().heroCode(1).name("SUPERMAN").build());
        response.add(HeroVO.builder().heroCode(2).name("BATMAN").build());
        when(this.repository.findAll()).thenReturn(response);
    }

    @Test
    public void testFindAll() {
        List<HeroVO> response = this.service.findAll();
        Assert.assertNotNull(response);
    }

    @Test
    public void testFindById() {
        Integer heroCode = 1;
        HeroVO responseMock = HeroVO.builder().heroCode(heroCode).name("SUPERMAN").build();
        when(this.repository.findById(heroCode)).thenReturn(responseMock);

        HeroVO response = this.service.findById(heroCode);
        Assert.assertEquals(responseMock.getHeroCode(), response.getHeroCode());
    }

    @Test
    public void testFindByName() {
        List<HeroVO> responseMock = new ArrayList<>();
        responseMock.add(HeroVO.builder().heroCode(1).name("SUPERMAN").build());
        responseMock.add(HeroVO.builder().heroCode(2).name("BATMAN").build());
        String findName = "man";
        FindNameVO request = FindNameVO.builder().name(findName).build();
        when(this.repository.findByName(request)).thenReturn(responseMock);
        List<HeroVO> response = this.service.findByName(request);
        assertThat(response.get(1).getName(), containsStringIgnoringCase(findName));
    }

    @Test
    public void testUpdate() {
        this.service.update(HeroVO.builder().heroCode(1).name("SUPERMAN EDIT").build());
        Assert.assertTrue(true);
    }

    @Test
    public void testDelete() {
        this.service.delete(HeroIdVO.builder().heroCode(1).build());
        Assert.assertTrue(true);
    }

}
