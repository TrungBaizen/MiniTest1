package com.example.service.Impl;

import com.example.model.DTO.ProducerDTO;
import com.example.model.Producer;
import com.example.repository.ProducerRepository;
import com.example.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Iterable<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public Page<ProducerDTO> findQuantityInProducerByIdProducer(Pageable pageable) {
        return producerRepository.findQuantityInProducerByIdProducer(pageable);
    }
}
