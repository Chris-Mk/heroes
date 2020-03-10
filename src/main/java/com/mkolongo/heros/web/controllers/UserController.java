package com.mkolongo.heros.web.controllers;

import com.mkolongo.heros.domain.entities.UserPrincipal;
import com.mkolongo.heros.domain.models.binding.UserRegisterModel;
import com.mkolongo.heros.domain.models.service.UserServiceModel;
import com.mkolongo.heros.domain.models.view.UserProfileModel;
import com.mkolongo.heros.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterModel", new UserRegisterModel());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegisterModel") UserRegisterModel userRegisterModel,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors() || !userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            return "register";
        }

        userService.register(mapper.map(userRegisterModel, UserServiceModel.class));
        return "redirect:/users/home";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        if (userPrincipal.getUser().getHero() == null) {
            return "home-hero-not-created";
        }

        model.addAttribute("profile", userService.getProfile(userPrincipal.getUsername()));
        return "home-with-created-hero";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserProfileModel profile = userService.getProfile(principal.getName());
        model.addAttribute("profile", profile);
        return "profile";
    }
}
