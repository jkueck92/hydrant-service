package de.jkueck.service.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "hydrants")
@NoArgsConstructor
@AllArgsConstructor
public class Hydrant extends AbstractEntity {

    private String ident;

}
