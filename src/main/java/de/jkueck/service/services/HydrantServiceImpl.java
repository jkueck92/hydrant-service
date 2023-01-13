package de.jkueck.service.services;

import de.jkueck.service.database.Hydrant;
import de.jkueck.service.database.HydrantRepository;
import de.jkueck.service.domain.HydrantCreateDto;
import de.jkueck.service.domain.HydrantDto;
import de.jkueck.service.domain.HydrantUpdateDto;
import de.jkueck.service.services.exception.HydrantNotFoundException;
import de.jkueck.service.services.exception.HydrantStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HydrantServiceImpl implements IHydrantService {

    private final HydrantRepository hydrantRepository;

    @Override
    public HydrantDto create(HydrantCreateDto hydrant) throws HydrantStoreException {
        Optional<HydrantDto> optionalHydrant = this.getByIdent(hydrant.getIdent());
        if (optionalHydrant.isPresent()) {
            throw new HydrantStoreException("can not store new hydrant, hydrant with ident (" + hydrant.getIdent() + ") exists");
        }
        Hydrant newHydrant = this.hydrantRepository.save(Hydrant.builder().ident(hydrant.getIdent()).build());
        return HydrantDto.builder().id(newHydrant.getId()).ident(newHydrant.getIdent()).build();
    }

    @Override
    public Optional<HydrantDto> getByIdent(String ident) {
        Optional<Hydrant> optionalHydrant = this.hydrantRepository.findByIdent(ident);
        return optionalHydrant.map(hydrant -> HydrantDto.builder().id(hydrant.getId()).ident(hydrant.getIdent()).build());
    }

    @Override
    public Optional<HydrantDto> getById(Long id) {
        Optional<Hydrant> optionalHydrant = this.hydrantRepository.findById(id);
        return optionalHydrant.map(hydrant -> HydrantDto.builder().id(optionalHydrant.get().getId()).ident(optionalHydrant.get().getIdent()).build());
    }

    @Override
    public void delete(Long id) {
        this.hydrantRepository.deleteById(id);
    }

    @Override
    public HydrantDto update(HydrantUpdateDto hydrant) throws HydrantNotFoundException {
        Optional<HydrantDto> optionalHydrant = this.getById(hydrant.getId());
        if (optionalHydrant.isEmpty()) {
            throw new HydrantNotFoundException("can not find hydrant with id (" + hydrant.getId() + ") to update");
        }
        optionalHydrant.get().setIdent(hydrant.getIdent());
        Hydrant newHydrant = this.hydrantRepository.save(Hydrant.builder().ident(hydrant.getIdent()).build());
        return HydrantDto.builder().id(newHydrant.getId()).ident(newHydrant.getIdent()).build();
    }

    @Override
    public Set<HydrantDto> getAll() {
        List<Hydrant> hydrantList = this.hydrantRepository.findAll();
        Set<HydrantDto> hydrants = new HashSet<>();
        for (Hydrant hydrant : hydrantList) {
            hydrants.add(HydrantDto.builder().id(hydrant.getId()).ident(hydrant.getIdent()).build());
        }
        return hydrants;
    }

}
