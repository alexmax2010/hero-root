package com.kruger.hero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * HeroEntity.
 * 
 * @author acachiguango on 01/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TBLHERO")
public class HeroEntity {
    @Id
    @GeneratedValue
    @Column(name = "HEROCODE", nullable = false)
    private Integer heroCode;

    @Column(name = "NAME")
    private String name;

    public Integer getId() {
        return heroCode;
    }
}
