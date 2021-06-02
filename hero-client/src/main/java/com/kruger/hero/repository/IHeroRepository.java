package com.kruger.hero.repository;

import java.util.List;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.entity.HeroEntity;
import ec.com.kruger.spring.orm.repository.IQueryDslBaseRepository;

/**
 * IHeroRepository.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
public interface IHeroRepository extends IQueryDslBaseRepository<HeroEntity> {
    /**
     * Obtiene todos los heroes.
     * 
     * @author acahiguango on 01/06/2021
     * @return Lista HeroVO
     */
    List<HeroVO> findAll();

    /**
     * Obtiene heroe por su id.
     * 
     * @author acahiguango on 01/06/2021
     * @param heroCode codigo
     * @return HeroVO
     */
    HeroVO findById(Integer heroCode);

    /**
     * Obtiene todos los super heroes que contienen, en su nombre, el valor de un
     * parametro.
     * 
     * @author acahiguango on 02/06/2021
     * @param request parametros a consultar
     * @return Lista HeroVO
     */
    List<HeroVO> findByName(FindNameVO request);

    /**
     * Actualiza el heroe.
     * 
     * @author acahiguango on 02/06/2021
     * @param request HeroVO
     */
    void update(HeroVO request);

    /**
     * Elimina el heroe.
     * 
     * @author acahiguango on 02/06/2021
     * @param request HeroIdVO
     */
    void delete(HeroIdVO request);

}
