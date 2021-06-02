package com.kruger.hero.service;

import java.util.List;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.entity.HeroEntity;
import com.kruger.hero.repository.IHeroRepository;
import ec.com.kruger.spring.service.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;

/**
 * HeroService.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@CacheConfig(cacheNames = "superhero")
@Transactional("jpaTransactionManager")
@Lazy
@Service
public class HeroService extends BaseService<HeroEntity, IHeroRepository> implements IHeroService {

    /**
     * Constructor with dependencies.
     *
     * @param repository The repository to inject
     */
    public HeroService(IHeroRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable(value = "allsuperheroes")
    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<HeroVO> findAll() {
        log.info("Ejecutando consulta a la DB.");
        return this.repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public HeroVO findById(Integer heroCode) {
        return this.repository.findById(heroCode);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(value = "jpaTransactionManager", readOnly = true)
    @Override
    public List<HeroVO> findByName(FindNameVO request) {
        return this.repository.findByName(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(HeroVO request) {
        this.repository.update(request);
    }

    /**
     * {@inheritDoc}
     */
    @CacheEvict(value = "allsuperheroes", allEntries = true)
    @Override
    public void delete(HeroIdVO request) {
        this.repository.delete(request);
    }

}
