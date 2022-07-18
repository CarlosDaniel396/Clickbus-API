package com.carlos.bus.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.carlos.bus.dtos.PlaceDTO;
import com.carlos.bus.entities.Place;
import com.carlos.bus.repositories.PlaceRepository;
import com.carlos.bus.services.exceptions.ResourceNotFoundException;
import com.carlos.bus.tests.Factory;

@ExtendWith(SpringExtension.class)
public class PlaceServiceTests {

	@InjectMocks
	private PlaceService service;

	@Mock
	private PlaceRepository repository;

	private long existingId;
	private long nonExistingId;
	private Place place;
	private PlaceDTO placeDTO;
	private PageImpl<Place> page;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		place = Factory.createPlace();
		placeDTO = Factory.createPlaceDTO();
		page = new PageImpl<>(List.of(place));

		Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);

		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(place));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Mockito.when(repository.find(any(), any())).thenReturn(page);
	}

	@Test
	public void findAllPagedShouldReturnPage() {

		Pageable pageable = PageRequest.of(0, 10);

		Page<PlaceDTO> result = service.findAllPaged("", pageable);

		Assertions.assertNotNull(result);
	}

	@Test
	public void findByIdShouldReturnPlaceDTOWhenIdExists() {

		PlaceDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
}
