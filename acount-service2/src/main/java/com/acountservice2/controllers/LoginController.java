package com.acountservice2.controllers;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.Money;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.services.DateForMainPageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mainpage")
public class LoginController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DateForMainPageInterface dateService;

    @GetMapping
    public String logInUser(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("nameOfLogedUser", user.getFirstName());
        model.addAttribute("loginOfLogedUser", user.getLogin());

        if(!model.containsAttribute("spendingForm")) {
            SpendingForm spendingForm = new SpendingForm();
            model.addAttribute("spendingForm", spendingForm);
        }

        List<Spending> currentUserSpend = dateService.getLastNSpendingOfCurrentUser(user.getId());
        model.addAttribute("allSpending", currentUserSpend);

        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categories.add(category));
        model.addAttribute("categories", categories);
        model.addAttribute("currencies", Money.values());

        return "main_page";
    }
}
