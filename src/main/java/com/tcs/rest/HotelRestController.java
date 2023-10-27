package com.tcs.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.HotelRequest;
import com.tcs.dto.HotelResponse;
import com.tcs.service.IHotelService;

@RestController
@Valid
public class HotelRestController {

	@Autowired
	private IHotelService service;

	@PostMapping("/save")
	public ResponseEntity<?> bookHotel(@Valid @RequestBody HotelRequest request) {
		HotelResponse bookHotel = service.bookHotel(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(bookHotel);
	}

	@GetMapping("/getAllRooms")
	public ResponseEntity<?> getHotelRoomNo() {
		List<HotelResponse> allRooms = service.getAllRooms();
		return ResponseEntity.status(HttpStatus.OK).body(allRooms);
	}

	@GetMapping("/hotel/{id}")
	public ResponseEntity<?> getHotelRoom(@Valid @PathVariable Integer id) {
		HotelResponse roomNo = service.getRoomNo(id);
		return ResponseEntity.status(HttpStatus.OK).body(roomNo);
	}

}
