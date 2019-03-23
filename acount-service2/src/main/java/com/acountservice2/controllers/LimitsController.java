package com.acountservice2.controllers;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Limit;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.LimitForm;
import com.acountservice2.formobjects.Money;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.LimitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/limits")
@Slf4j
public class LimitsController {



    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LimitRepository limitRepository;

    @GetMapping("/set")
    public String showLimitsPage(Model model){
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categories.add(category));
        if(!model.containsAttribute("limitForm")){
            log.info("tworzymy nowy pusty limit");
            LimitForm limitForm = new LimitForm();
            model.addAttribute("limitForm", limitForm);
        }
        model.addAttribute("currencies", Money.values());
        return "limits";
    }

        @PostMapping("/add")
    public String addNewLimit(@Valid LimitForm limitForm, BindingResult errors,
                              @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes,
                              Model model){
        if(errors.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.limitForm",
                    errors);
            redirectAttributes.addFlashAttribute("limitForm", limitForm);
            System.out.println("błąd przy dodawaniu limitu");
            return "redirect:/limits/set";
        }
            Limit limit = limitForm.toLimit(user.getId());
            limitRepository.save(limit);
            log.info("Limit jest wrzucony do bazy: "+ limit);
        return "redirect:/limits/set";
        }



}
