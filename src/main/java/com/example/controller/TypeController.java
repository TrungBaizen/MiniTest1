package com.example.controller;

import com.example.model.Type;
import com.example.service.CarService;
import com.example.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService , CarService carService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("types", typeService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("type", typeService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/types";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Type type , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        typeService.save(type);
        return showList();
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Type type = typeService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Type type , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("update");
            modelAndView.addObject("type", type);
            return modelAndView;
        }
        typeService.save(type);
        return showList();
    }
}
