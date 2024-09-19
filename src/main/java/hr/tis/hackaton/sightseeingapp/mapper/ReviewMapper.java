package hr.tis.hackaton.sightseeingapp.mapper;

import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.dto.ReviewDto;
import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;
import hr.tis.hackaton.sightseeingapp.model.Location;
import hr.tis.hackaton.sightseeingapp.model.Review;
import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {


    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "location", source = "attraction.location.name")
    @Mapping(target = "attractionName", source = "attraction.name")
    ReviewDto toDto(Review review);

    //Review toEntity(ReviewDto reviewDto);

}
