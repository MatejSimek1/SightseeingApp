package hr.tis.hackaton.sightseeingapp.mapper;

import hr.tis.hackaton.sightseeingapp.dto.AttractionDto;
import hr.tis.hackaton.sightseeingapp.dto.LocationDto;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
import hr.tis.hackaton.sightseeingapp.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "location", source = "location")
    @Mapping(target = "attractions", source = "attractions")

    LocationDto toDto(Location location);
    List<AttractionDto> toDtoList(List<Attraction> attractions);
}