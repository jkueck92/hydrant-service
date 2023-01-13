package de.jkueck.service;

import de.jkueck.service.database.Hydrant;
import de.jkueck.service.database.HydrantRepository;
import de.jkueck.service.domain.HydrantCreateDto;
import de.jkueck.service.domain.HydrantDto;
import de.jkueck.service.services.HydrantServiceImpl;
import de.jkueck.service.services.exception.HydrantStoreException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HydrantServiceTest {

    @Mock
    private HydrantRepository hydrantRepository;

    @InjectMocks
    private HydrantServiceImpl hydrantService;

    private Hydrant hydrant;

    @BeforeEach
    public void setup() {
        this.hydrant = new Hydrant();
        this.hydrant.setId(1L);
        this.hydrant.setIdent("H001");
    }

    @Test
    @DisplayName("saveHydrant - with exception due to ident exists")
    public void save_hydrantWithException() {

        Optional<Hydrant> optionalHydrant = Optional.of(this.hydrant);

        given(this.hydrantRepository.findByIdent(this.hydrant.getIdent())).willReturn(optionalHydrant);

        Assertions.assertThrows(HydrantStoreException.class, () -> {
            this.hydrantService.create(HydrantCreateDto.builder().ident("H001").build());
        });

        verify(this.hydrantRepository, never()).save(any(Hydrant.class));

    }

    @Test
    @DisplayName("getByIdent - success")
    public void getByIdent_success() {

        given(this.hydrantRepository.findByIdent(this.hydrant.getIdent())).willReturn(Optional.of(this.hydrant));

        Optional<HydrantDto> optionalHydrant = this.hydrantService.getByIdent(this.hydrant.getIdent());

        Assertions.assertNotNull(optionalHydrant);
        Assertions.assertEquals(optionalHydrant.isPresent(), Boolean.TRUE);
    }

    @Test
    @DisplayName("getByIdent - failure")
    public void getByIdent_failure() {

        given(this.hydrantRepository.findByIdent(this.hydrant.getIdent())).willReturn(Optional.empty());

        Optional<HydrantDto> optionalHydrant = this.hydrantService.getByIdent(this.hydrant.getIdent());

        Assertions.assertNotNull(optionalHydrant);
        Assertions.assertEquals(optionalHydrant.isEmpty(), Boolean.TRUE);

    }

    @Test
    @DisplayName("getById - success")
    public void getById_success() {

        given(this.hydrantRepository.findById(this.hydrant.getId())).willReturn(Optional.of(this.hydrant));

        Optional<HydrantDto> optionalHydrant = this.hydrantService.getById(this.hydrant.getId());

        Assertions.assertNotNull(optionalHydrant);
        Assertions.assertEquals(optionalHydrant.isPresent(), Boolean.TRUE);
    }

    @Test
    @DisplayName("getById - failure")
    public void getByIdt_failure() {

        given(this.hydrantRepository.findById(this.hydrant.getId())).willReturn(Optional.empty());

        Optional<HydrantDto> optionalHydrant = this.hydrantService.getById(this.hydrant.getId());

        Assertions.assertNotNull(optionalHydrant);
        Assertions.assertEquals(optionalHydrant.isEmpty(), Boolean.TRUE);

    }

}
