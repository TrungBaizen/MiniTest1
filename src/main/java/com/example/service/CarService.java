package com.example.service;

import com.example.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService extends IService<Car>{
    Page<Car> findByNameContainingPageAndSort(String name , Pageable pageable);
    Page<Car> findAllPageAndSort(Pageable pageable);
}
