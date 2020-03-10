package com.mkolongo.heros.web.controllers;

import com.mkolongo.heros.domain.entities.Gender;
import com.mkolongo.heros.domain.entities.UserPrincipal;
import com.mkolongo.heros.domain.models.binding.HeroCreateModel;
import com.mkolongo.heros.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/heroes")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("heroCreateModel", new HeroCreateModel());
        model.addAttribute("gender", Gender.values());
        return "create-hero";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("heroCreateModel") HeroCreateModel heroCreateModel,
                         BindingResult bindingResult,
                         Principal principal) {

        if (bindingResult.hasErrors()) {
            return "create-hero";
        }

        heroService.create(heroCreateModel, principal.getName());
        return "redirect:/users/home";
    }

    @GetMapping("/details")
    public String details(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("hero", userPrincipal.getUser().getHero());
        return "heroDetails";
    }
}
