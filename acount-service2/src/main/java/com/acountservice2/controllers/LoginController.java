package com.acountservice2.controllers;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.Money;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.services.DateForMainPageInterface;
import com.acountservice2.services.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/mainpage")
public class LoginController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DateForMainPageInterface dateService;
    @Autowired
    LimitService limitService;
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
        categoryRepository.findAll().forEach(categories::add);
        model.addAttribute("categories", categories);
        model.addAttribute("currencies", Money.values());
        double monthSpending = dateService.getMonthSpending(user);
        double yearSpending = dateService.getYearSpending(user);
        double monthLimit = limitService.getMonthLimit(user);
        double yearLimit = limitService.getYearLimit(user);
        double averageMonthSpending=monthSpending/ Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        averageMonthSpending = Double.parseDouble(String.format("%.2f", averageMonthSpending));
        double monthRemains = monthLimit-monthSpending;
        if(monthRemains<0)
            monthRemains=0;
        monthRemains = Double.parseDouble(String.format("%.2f", monthRemains));

        double yearRemains = yearLimit - yearSpending;
        if(yearRemains <0)
            yearRemains=0;
        yearRemains = Double.parseDouble(String.format("%.2f", yearRemains));

        double currentDaySpending = dateService.getCurrentDaySpending(user);

        model.addAttribute("monthSpending", monthSpending);
        model.addAttribute("yearSpending", yearSpending);
        model.addAttribute("monthLimit", monthLimit);
        model.addAttribute("yearLimit", yearLimit);
        model.addAttribute("averageMonthSpending", averageMonthSpending);
        model.addAttribute("currentDaySpending", currentDaySpending);
        model.addAttribute("monthRemains", monthRemains);
        model.addAttribute("yearRemains", yearRemains);

        return "main_page";
    }


}
