package hr.tis.hackaton.sightseeingapp.mapper;

import hr.tis.hackaton.sightseeingapp.dto.AttractionDto;
import hr.tis.hackaton.sightseeingapp.model.Attraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttractionMapper {
    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "type", source = "type")
    AttractionDto toDto(Attraction attraction);

}