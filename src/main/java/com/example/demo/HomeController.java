package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PetRepository petRepo;

    @RequestMapping("/")
    public String showPet(Model model){
        model.addAttribute("petList", petRepo.findAll());
        return "showpet";
    }

    @RequestMapping("/add")
    public String addPet(Model model){

        model.addAttribute("pet", new Pet());
        return "addpet";
    }

    @RequestMapping("/savepet")
    public String savePet(@Valid @ModelAttribute("pet") Pet toSave, BindingResult result){
        if(result.hasErrors()){
            return "addpet";
        }
        petRepo.save(toSave);
        return "redirect:/";
    }

    @RequestMapping("/changestatus/{id}")
    public String foundPet(@PathVariable("id") long id){
        Pet thisPet = petRepo.findById(id).get();

        thisPet.setFound(!thisPet.isFound());

        petRepo.save(thisPet);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public String updatePet(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepo.findById(id));
        return "addpet";
    }
    @RequestMapping("/delete/{id}")
    public String deletePet(@PathVariable("id") long id){
        petRepo.deleteById(id);
        return "redirect:/";
    }
}
