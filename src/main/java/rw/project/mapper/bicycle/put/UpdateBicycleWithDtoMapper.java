package rw.project.mapper.bicycle.put;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.model.Bicycle;

@Mapper(componentModel = "spring")
public interface UpdateBicycleWithDtoMapper {
    @Mapping(target = "id", ignore = true)
    Bicycle updateWithDto(@MappingTarget Bicycle bicycle, BicycleDto_PUT bicycleDto_put);
}