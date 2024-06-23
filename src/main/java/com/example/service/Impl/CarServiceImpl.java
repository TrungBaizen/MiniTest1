package com.example.service.Impl;

import com.example.model.Car;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void save(Car car) {
        if (carRepository.existsByCode(car.getCode())) {
            throw new IllegalArgumentException("Mã số biển đã tồn tại");
        }
        carRepository.save(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Page<Car> findAllPageAndSort(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Page<Car> findByNameContainingPageAndSort(String name , Pageable pageable) {
        return carRepository.findByNameContaining(name , pageable);
    }
}
