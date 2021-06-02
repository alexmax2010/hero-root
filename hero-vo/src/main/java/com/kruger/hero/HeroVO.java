package com.kruger.hero;

import com.kruger.hero.validate.NotBlankConstraint;
import com.kruger.hero.validate.PositiveIntConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HeroVO.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroVO {
    @PositiveIntConstraint
    private Integer heroCode;
    @NotBlankConstraint(message = "Invalid parameter")
    private String name;
}
