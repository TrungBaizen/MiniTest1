package com.example.controller;

import com.example.model.Car;
import com.example.service.CarService;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final TypeService typeService;

    @Autowired
    public CarController(CarService carService, TypeService typeService) {
        this.carService = carService;
        this.typeService = typeService;
    }


    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("cars", carService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("car", carService.findById(id).get());
        modelAndView.addObject("categoryName", carService.findById(id).get().getType().getName());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/cars";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("car", new Car());
        modelAndView.addObject("types",typeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Car car , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        carService.save(car);
        return showList();
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Car car = carService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("car", car);
        modelAndView.addObject("types",typeService.findAll());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Car car , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        carService.save(car);
        return showList();
    }
}
