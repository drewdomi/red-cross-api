package com.drewdomi.redcross.mappers;

import com.drewdomi.redcross.dtos.RescuerDto;
import com.drewdomi.redcross.models.Rescuer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RescuerMapper {
    RescuerMapper INSTANCE = Mappers.getMapper(RescuerMapper.class);

    RescuerDto toProjection(Rescuer rescuer);
}
