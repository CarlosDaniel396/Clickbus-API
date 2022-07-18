package com.carlos.bus.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.bus.dtos.PlaceDTO;
import com.carlos.bus.entities.Place;
import com.carlos.bus.repositories.PlaceRepository;
import com.carlos.bus.services.exceptions.ResourceNotFoundException;

@Service
public class PlaceService {

	@Autowired
	private PlaceRepository repository;

	@Transactional(readOnly = true)
	public Page<PlaceDTO> findAllPaged(String name, Pageable pageable) {
		Page<Place> list = repository.find(name, pageable);
		return list.map(x -> new PlaceDTO(x));
	}

	@Transactional(readOnly = true)
	public PlaceDTO findById(Long id) {
		Optional<Place> obj = repository.findById(id);
		Place entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PlaceDTO(entity);
	}

	@Transactional
	public PlaceDTO insert(PlaceDTO dto) {
		Place entity = new Place();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new PlaceDTO(entity);
	}

	@Transactional
	public PlaceDTO update(Long id, PlaceDTO dto) {
		try {
			Place entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new PlaceDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	private void copyDtoToEntity(PlaceDTO dto, Place entity) {

		entity.setName(dto.getName());
		entity.setCity(dto.getCity());
		entity.setState(dto.getState());
	}
}
