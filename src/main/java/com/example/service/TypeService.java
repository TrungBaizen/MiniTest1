package com.example.service;

import com.example.model.DTO.ProducerDTO;
import com.example.model.DTO.TypeDTO;
import com.example.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService extends IService<Type>{
    Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable);
}
