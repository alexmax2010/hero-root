package com.kruger.hero.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kruger.hero.HeroVO;
import com.kruger.hero.service.IHeroService;

/**
 * HeroSecureController.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */

@RestController
@RequestMapping("/hero")
@Lazy
public class HeroSecureController {
    @Lazy
    @Autowired
    private IHeroService heroService;

    /**
     * Obtiene todos los heroes.
     * 
     * @author acachiguango on 01/06/2021
     * @return Lista HeroVO
     */

    @GetMapping(value = "/findAll")
    public List<HeroVO> findAll() {
        return this.heroService.findAll();
    }
}
