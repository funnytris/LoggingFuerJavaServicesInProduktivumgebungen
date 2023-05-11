package rw.project.mapper.bicycle;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import rw.project.dto.BicycleDto;
import rw.project.model.Bicycle;

@Mapper(componentModel = "spring")
public interface DtoToBicycle extends Converter<BicycleDto, Bicycle> {
    @Override
    Bicycle convert( BicycleDto source);
}
