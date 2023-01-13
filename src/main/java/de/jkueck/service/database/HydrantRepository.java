package de.jkueck.service.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HydrantRepository extends JpaRepository<Hydrant, Long> {

    Optional<Hydrant> findByIdent(final String ident);

}
