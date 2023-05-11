package rw.project.mapper.bicycle.put;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.model.Bicycle;

@Mapper(componentModel = "spring")
public interface DtoToBicycle_PUT extends Converter<BicycleDto_PUT, Bicycle> {
    @Mapping(target = "id", ignore = true)
    @Override
    Bicycle convert( BicycleDto_PUT source);
}
