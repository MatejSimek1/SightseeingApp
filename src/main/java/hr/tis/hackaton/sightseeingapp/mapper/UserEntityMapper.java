package hr.tis.hackaton.sightseeingapp.mapper;

import hr.tis.hackaton.sightseeingapp.dto.UserEntityDto;
import hr.tis.hackaton.sightseeingapp.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserEntityDto toDto(UserEntity userEntity);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    UserEntity toEntity(UserEntityDto userEntityDto);

}
