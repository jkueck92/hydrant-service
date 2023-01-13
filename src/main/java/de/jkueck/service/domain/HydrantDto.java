package de.jkueck.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HydrantDto {

    private Long id;

    private String ident;

}
