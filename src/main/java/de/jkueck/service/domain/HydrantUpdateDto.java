package de.jkueck.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HydrantUpdateDto {

    private Long id;

    private String ident;

}
