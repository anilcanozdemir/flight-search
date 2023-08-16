package com.amadeus.flightsearch.Service.Contrats;

import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;

import java.util.List;

public interface CRUDService<EntityResponseDto, EntitySaveRequestDto, EntityUpdateRequestDto> {

    Result add(EntitySaveRequestDto entitySaveRequestDto);

    DataResult<EntityResponseDto> deleteByid(Long id);

    DataResult<List<EntityResponseDto>> getAll();

    DataResult<EntityResponseDto> getById(Long id);

    Result updateById(EntityUpdateRequestDto entityUpdateRequestDto);
}
