package com.ecoride.passengerservice.mapper;

import com.ecoride.passengerservice.dto.rating.RatingRequestDto;
import com.ecoride.passengerservice.dto.rating.RatingResponseDto;
import com.ecoride.passengerservice.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    Rating toEntity(RatingRequestDto ratingRequestDto);
    RatingResponseDto toDto(Rating rating);

}
