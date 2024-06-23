package com.example.controller;

import com.example.model.Car;
import com.example.service.CarService;
import com.example.service.ProducerService;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final ProducerService producerService;

    @Autowired
    public CarController(CarService carService, TypeService typeService, ProducerService producerService) {
        this.carService = carService;
        this.typeService = typeService;
        this.producerService = producerService;
    }


    @GetMapping("")
    public ModelAndView showList(@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("car/index");
        modelAndView.addObject("cars", carService.findAllPageAndSort(pageable));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("car/delete");
        modelAndView.addObject("car", carService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        carService.delete(id);
        return "redirect:/cars";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("car/create");
        modelAndView.addObject("car", new Car());
        modelAndView.addObject("types",typeService.findAll());
        modelAndView.addObject("producers",producerService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Car car , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("car/create");
            modelAndView.addObject("types",typeService.findAll());
            modelAndView.addObject("producers",producerService.findAll());
            return modelAndView;
        }
        try {
            carService.save(car);
        }catch (IllegalArgumentException e){
            bindingResult.rejectValue("code","Không thể thêm xe",e.getMessage());
            ModelAndView modelAndView = new ModelAndView("car/create");
            modelAndView.addObject("types",typeService.findAll());
            modelAndView.addObject("producers",producerService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/cars");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Car car = carService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("car/update");
        modelAndView.addObject("car", car);
        modelAndView.addObject("types",typeService.findAll());
        modelAndView.addObject("producers",producerService.findAll());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Car car , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("car/update");
            modelAndView.addObject("types",typeService.findAll());
            modelAndView.addObject("producers",producerService.findAll());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        carService.save(car);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView showSearch(@RequestParam String s , @PageableDefault(value = 5) Pageable pageable) {
        Page<Car> cars;
        if (s.isEmpty()) {
            cars = carService.findAllPageAndSort(pageable);
        } else {
            cars =carService.findByNameContainingPageAndSort(s , pageable);
        }
        ModelAndView modelAndView = new ModelAndView("car/search");
        modelAndView.addObject("cars", cars);
        modelAndView.addObject("s",s);
        return modelAndView;
    }
}
