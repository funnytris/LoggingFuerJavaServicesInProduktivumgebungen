package rw.project.mapper.bicycle;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import rw.project.dto.BicycleDto;
import rw.project.model.Bicycle;

@Mapper(componentModel = "spring")
public interface BicycleToDto extends Converter<Bicycle, BicycleDto> {
    @Override
    BicycleDto convert(Bicycle source);
}

