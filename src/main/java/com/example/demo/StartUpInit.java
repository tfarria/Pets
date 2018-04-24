package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Component
public class StartUpInit {

    @Autowired
    PetRepository petRepo;

    @PostConstruct
    public String showPet(){
        Pet p= new Pet();
        p.setName("Fluffy");
        p.setType("Dog");
        p.setDescription("Huge Dog");
        p.setImage("/img/pup.png");
        petRepo.save(p);
        return "showpet";
    }


}
