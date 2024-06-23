package com.example.service;

import com.example.model.DTO.ProducerDTO;
import com.example.model.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProducerService extends IService<Producer>{
    Page<ProducerDTO> findQuantityInProducerByIdProducer(Pageable pageable);
}
