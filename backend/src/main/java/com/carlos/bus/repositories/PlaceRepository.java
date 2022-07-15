package com.carlos.bus.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carlos.bus.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	@Query("SELECT DISTINCT obj FROM Place obj WHERE "
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')))")
	Page<Place> find(String name, Pageable pageable);
}
