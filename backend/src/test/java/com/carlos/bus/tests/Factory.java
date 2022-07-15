package com.carlos.bus.tests;

import com.carlos.bus.dtos.PlaceDTO;
import com.carlos.bus.entities.Place;

public class Factory {

	public static Place createPlace() {
		Place place = new Place(1L, "Maikai", "Maceió", "Alagoas");
		return place;
	}

	public static PlaceDTO createPlaceDTO() {
		Place place = createPlace();
		return new PlaceDTO(place);
	}
}
