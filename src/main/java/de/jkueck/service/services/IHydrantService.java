package de.jkueck.service.services;

import de.jkueck.service.domain.HydrantCreateDto;
import de.jkueck.service.domain.HydrantDto;
import de.jkueck.service.domain.HydrantUpdateDto;
import de.jkueck.service.services.exception.HydrantNotFoundException;
import de.jkueck.service.services.exception.HydrantStoreException;

import java.util.Optional;
import java.util.Set;

public interface IHydrantService {

    HydrantDto create(HydrantCreateDto hydrant) throws HydrantStoreException;

    Optional<HydrantDto> getByIdent(String ident);

    Optional<HydrantDto> getById(Long id);

    void delete(Long id);

    HydrantDto update(HydrantUpdateDto hydrant) throws HydrantNotFoundException;

    Set<HydrantDto> getAll();

}
