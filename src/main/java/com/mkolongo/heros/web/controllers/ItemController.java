package com.mkolongo.heros.web.controllers;

import com.mkolongo.heros.domain.entities.Slot;
import com.mkolongo.heros.domain.entities.UserPrincipal;
import com.mkolongo.heros.domain.models.binding.ItemCreateModel;
import com.mkolongo.heros.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("itemCreateModel", new ItemCreateModel());
        model.addAttribute("slots", Slot.values());
        return "create-item";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("itemCreateModel") ItemCreateModel itemCreateModel,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create-item";
        }

        itemService.create(itemCreateModel);
        return "redirect:/items/merchant";
    }

    @GetMapping("/merchant")
    public String merchant(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "merchant";
    }

    @PostMapping("/merchant/{id}")
    public String merchant(@PathVariable String id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        itemService.buyItem(id, userPrincipal.getUser().getHero());
        return "redirect:/heroes/details";
    }
}
