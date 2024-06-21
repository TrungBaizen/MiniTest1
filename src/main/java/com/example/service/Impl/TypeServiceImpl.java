package com.example.service.Impl;

import com.example.model.Type;
import com.example.repository.CarRepository;
import com.example.repository.TypeRepository;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return typeRepository.findById(id);
    }
}
