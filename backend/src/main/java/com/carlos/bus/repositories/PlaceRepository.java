package com.carlos.bus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlos.bus.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
