package com.kruger.hero.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.controller.HeroController;
import com.kruger.hero.service.IHeroService;
import ec.com.kruger.spring.ws.controller.test.MockMvcControllerBase;

/**
 * HeroControllerTest.
 * 
 * @author Kruger on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(HeroController.class)
public class HeroControllerTest extends MockMvcControllerBase {

    /**
     * Contexto.
     */
    public static final String CONTEXT_PATH = "/heroServices";

    @InjectMocks
    private HeroController controller;

    @Mock
    private IHeroService service;

    @Before
    public void setup() {
        super.setUpBase(controller);
    }

    @Test
    public void testFindAll() throws Exception {
        List<HeroVO> response = new ArrayList<>();
        response.add(HeroVO.builder().heroCode(1).name("SUPERMAN").build());
        response.add(HeroVO.builder().heroCode(2).name("BATMAN").build());
        when(this.service.findAll()).thenReturn(response);
        mockMvc
            .perform(get("/heroServices/api/public/hero/findAll").contextPath(CONTEXT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindById() throws Exception {
        when(this.service.findById(1)).thenReturn(HeroVO.builder().heroCode(1).name("SUPERMAN").build());
        mockMvc
            .perform(get("/heroServices/api/public/hero/findById?heroCode=1").contextPath(CONTEXT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindByName() throws Exception {
        List<HeroVO> response = new ArrayList<>();
        response.add(HeroVO.builder().heroCode(1).name("SUPERMAN").build());
        response.add(HeroVO.builder().heroCode(2).name("BATMAN").build());
        FindNameVO request = FindNameVO.builder().name("man").build();
        when(this.service.findByName(request)).thenReturn(response);
        mockMvc
            .perform(post("/heroServices/api/public/hero/findByName").contextPath(CONTEXT_PATH)
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdate() throws Exception {
        HeroVO request = HeroVO.builder().name("man").heroCode(1).build();
        mockMvc
            .perform(patch("/heroServices/api/public/hero/update").contextPath(CONTEXT_PATH)
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDelete() throws Exception {
        HeroIdVO request = HeroIdVO.builder().heroCode(1).build();
        mockMvc
            .perform(delete("/heroServices/api/public/hero/delete").contextPath(CONTEXT_PATH)
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    
    @Test
    public void testDeleteError() throws Exception {
        HeroIdVO request = HeroIdVO.builder().build();
        mockMvc
            .perform(delete("/heroServices/api/public/hero/delete").contextPath(CONTEXT_PATH)
                .content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
    }
    
}
