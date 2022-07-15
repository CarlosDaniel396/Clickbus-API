package com.carlos.bus.dtos;

import java.io.Serializable;

import com.carlos.bus.entities.Place;

public class PlaceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String city;
	private String state;

	public PlaceDTO() {
	}

	public PlaceDTO(Long id, String name, String city, String state) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
	}

	public PlaceDTO(Place entity) {
		id = entity.getId();
		name = entity.getName();
		city = entity.getCity();
		state = entity.getState();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
