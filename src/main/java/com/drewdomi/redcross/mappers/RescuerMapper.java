package com.drewdomi.redcross.mappers;

import com.drewdomi.redcross.dtos.RescuerDto;
import com.drewdomi.redcross.models.Rescuer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RescuerMapper {
    RescuerMapper INSTANCE = Mappers.getMapper(RescuerMapper.class);

    List<RescuerDto> rescuersToRescuerDtos(List<Rescuer> rescuers);

    RescuerDto rescuerToRescuerDto(Rescuer rescuer);
}
