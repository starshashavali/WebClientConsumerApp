package com.tcs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tcs.dto.HotelRequest;
import com.tcs.dto.HotelResponse;
import com.tcs.service.IHotelService;

import reactor.core.publisher.Flux;

@Service
public class IHotelServiceImpl implements IHotelService {

	private String BOOK_HOTEL = "http://13.233.0.240:8086/save";
	private String GET_ROOM = "http://13.233.0.240:8086/hotel/{id}";
	private String GET_ALL_ROOMS = "http://13.233.0.240:8086/getAllRooms";

	@Override
	public HotelResponse bookHotel(HotelRequest request) {
		WebClient create = WebClient.create();
		HotelResponse block = create.post().uri(BOOK_HOTEL).bodyValue(request).retrieve()
				.bodyToMono(HotelResponse.class).block();
		return block;
	}

	@Override
	public HotelResponse getRoomNo(Integer roomNo) {

		WebClient create = WebClient.create();
		HotelResponse response = create.get().uri(GET_ROOM, roomNo).retrieve().bodyToMono(HotelResponse.class).block();
		return response;
	}

	@Override
	public List<HotelResponse> getAllRooms() {
		WebClient create = WebClient.create();
		Flux<HotelResponse> responseFlux = create.get().uri(GET_ALL_ROOMS).retrieve().bodyToFlux(HotelResponse.class);

		return responseFlux.collectList().block();
	}

}
