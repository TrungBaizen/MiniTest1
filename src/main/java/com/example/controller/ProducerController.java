package com.example.controller;


import com.example.model.Producer;
import com.example.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/producers")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("producer/index");
        modelAndView.addObject("producers", producerService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("producer/delete");
        modelAndView.addObject("producer", producerService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        producerService.delete(id);
        return "redirect:/producers";
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("producer/create");
        modelAndView.addObject("producer", new Producer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Producer producer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("producer/create");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/producers");
        producerService.save(producer);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Producer producer = producerService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("producer/update");
        modelAndView.addObject("producer", producer);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Producer producer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("producer/update");
            modelAndView.addObject("producer", producer);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/producers");
        producerService.save(producer);
        return modelAndView;
    }

    @GetMapping("/statistic")
    public ModelAndView showStatistic(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("producer/statistic");
        modelAndView.addObject("statistics", producerService.findQuantityInProducerByIdProducer(pageable));
        return modelAndView;
    }
}
