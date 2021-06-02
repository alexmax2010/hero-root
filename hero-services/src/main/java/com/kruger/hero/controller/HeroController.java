package com.kruger.hero.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.ResponseVO;
import com.kruger.hero.service.IHeroService;
import ec.com.kruger.spring.ws.controller.BaseController;

/**
 * HeroController.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/public/hero")
@Lazy
public class HeroController extends BaseController {

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

    /**
     * Obtiene heroe por id.
     * 
     * @author acachiguango on 01/06/2021
     * @return Lista HeroVO
     */
    @GetMapping(value = "/findById")
    public HeroVO findById(@RequestParam Integer heroCode) {
        return this.heroService.findById(heroCode);
    }

    /**
     * Obtiene todos los super heroes que contienen, en su nombre, el valor de un
     * parametro.
     * 
     * @author acachiguango on 02/06/2021
     * @param request parametro
     * @return Lista HeroVO
     */
    @PostMapping("/findByName")
    public List<HeroVO> findByName(@Valid @RequestBody FindNameVO request) {
        return this.heroService.findByName(request);
    }

    /**
     * Actualiza heroe.
     * 
     * @author acachiguango on 02/06/2021
     * @param request HeroVO
     * @return ResponseVO
     */
    @PatchMapping("/update")
    public ResponseVO update(@Valid @RequestBody HeroVO request) {
        this.heroService.update(request);
        return ResponseVO.builder().success(true).code(200).build();
    }

    /**
     * Elimina heroe.
     * 
     * @author acachiguango on 02/06/2021
     * @param request HeroIdVO
     * @return ResponseVO
     */
    @DeleteMapping("/delete")
    public ResponseVO delete(@Valid @RequestBody HeroIdVO request) {
        this.heroService.delete(request);
        return ResponseVO.builder().success(true).code(200).build();
    }

}
