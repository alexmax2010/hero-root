package com.kruger.hero.repository;

import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.kruger.hero.FindNameVO;
import com.kruger.hero.HeroIdVO;
import com.kruger.hero.HeroVO;
import com.kruger.hero.entity.HeroEntity;
import com.kruger.hero.entity.QHeroEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import ec.com.kruger.spring.orm.jpa.repository.JPAQueryDslBaseRepository;

/**
 * HeroRepository.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Lazy
@Repository
public class HeroRepository extends JPAQueryDslBaseRepository<HeroEntity> implements IHeroRepository {

    /**
     * Constructor of HeroEntity.
     */
    public HeroRepository() {
        super(HeroEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HeroVO> findAll() {
        QHeroEntity qHeroEntity = QHeroEntity.heroEntity;
        JPQLQuery<HeroVO> query = from(qHeroEntity).select(bean(HeroVO.class, qHeroEntity.heroCode, qHeroEntity.name));
        return query.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HeroVO findById(Integer heroCode) {
        QHeroEntity qHeroEntity = QHeroEntity.heroEntity;
        JPQLQuery<HeroVO> query = from(qHeroEntity).select(bean(HeroVO.class, qHeroEntity.heroCode, qHeroEntity.name));
        BooleanBuilder where = new BooleanBuilder();
        where.and(qHeroEntity.heroCode.eq(heroCode));
        query.where(where);
        return query.fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<HeroVO> findByName(FindNameVO request) {
        QHeroEntity qHeroEntity = QHeroEntity.heroEntity;
        JPQLQuery<HeroVO> query = from(qHeroEntity).select(bean(HeroVO.class, qHeroEntity.heroCode, qHeroEntity.name));
        BooleanBuilder where = new BooleanBuilder();
        where.and(qHeroEntity.name.containsIgnoreCase(request.getName()));
        query.where(where);
        return query.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(HeroVO request) {
        QHeroEntity qHeroEntity = QHeroEntity.heroEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qHeroEntity.heroCode.eq(request.getHeroCode()));
        update(qHeroEntity).where(where).set(qHeroEntity.name, request.getName()).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(HeroIdVO request) {
        QHeroEntity qHeroEntity = QHeroEntity.heroEntity;
        JPADeleteClause deleteClause = new JPADeleteClause(getEntityManager(), qHeroEntity);
        deleteClause.where(qHeroEntity.heroCode.eq(request.getHeroCode())).execute();
    }

}
