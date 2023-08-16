package com.amadeus.flightsearch.Controller;

import com.amadeus.flightsearch.Core.Result.DataResult;
import com.amadeus.flightsearch.Core.Result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDController<EntityResponseDto, EntitySaveRequestDto, EntityUpdateRequestDto> {
    public ResponseEntity<Result> add(EntitySaveRequestDto entitySaveRequestDto);

    public ResponseEntity<DataResult<List<EntityResponseDto>>> getAll();

    public ResponseEntity<DataResult<EntityResponseDto>> getById(Long id);

    public ResponseEntity<Result> updateById(EntityUpdateRequestDto entityUpdateRequestDto);

    public ResponseEntity<DataResult<EntityResponseDto>> deleteById(Long id);
}
