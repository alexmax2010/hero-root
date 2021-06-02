package com.kruger.hero;

import com.kruger.hero.validate.NotBlankConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FindNameVO.
 * 
 * @author acachiguango on 02/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindNameVO {
    @NotBlankConstraint(message = "Invalid parameter name")
    private String name;
}
