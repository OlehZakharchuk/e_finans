package com.acountservice2.controllers;

import com.acountservice2.config.AccountProps;
import com.acountservice2.entities.Category;
import com.acountservice2.entities.Limit;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.LimitForm;
import com.acountservice2.formobjects.Money;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.LimitRepository;
import com.acountservice2.services.LimitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.time.Year;
import java.util.*;

@Controller
@Slf4j
public class LimitsController {



    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LimitRepository limitRepository;
    @Autowired
    LimitService limitService;
    @Autowired
    AccountProps accountProps;

    @GetMapping("/limits/set")
    public String showLimitsPage(Model model, @AuthenticationPrincipal User user){
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        if(!model.containsAttribute("limitForm")){
            log.info("tworzymy nowy pusty limit");
            LimitForm limitForm = new LimitForm();
            model.addAttribute("limitForm", limitForm);
        }

        model.addAttribute("yearLimit",limitService.getYearLimit(user));
        model.addAttribute("monthLimit", limitService.getMonthLimit(user));
        model.addAttribute("currencies", Money.values());
        return "limits";
    }

        @PostMapping("/limits/add")
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
            //checking if this limit already exists in DB
            limit = limitService.update(limit);

            log.info("Limit jest wrzucony do bazy: "+ limit);
        return "redirect:/limits/set";
        }





}
