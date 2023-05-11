package rw.project.mapper.bicycle.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;
import rw.project.dto.post.BicycleDto_POST;
import rw.project.model.Bicycle;

@Mapper(componentModel = "spring")
public interface DtoToBicycle_POST extends Converter<BicycleDto_POST, Bicycle> {
    @Mapping(target = "id", ignore = true)
    @Override
    Bicycle convert(BicycleDto_POST source);
}
