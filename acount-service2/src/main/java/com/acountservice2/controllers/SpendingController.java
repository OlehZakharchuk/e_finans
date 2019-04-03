package com.acountservice2.controllers;

import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.SpendingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/spending")
@Slf4j
public class SpendingController {

   private SpendingRepository spendingRepo;
    private CategoryRepository categoryRepository;
    public SpendingController(SpendingRepository spendingRepo, CategoryRepository categoryRepository) {
        this.spendingRepo = spendingRepo;
        this.categoryRepository=categoryRepository;
    }



    @PostMapping("/add")
    public String addNewSpending(@Valid SpendingForm spendingForm, BindingResult bindingResult,
                                 @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
        //todo pobieranie aktualnego kursu walut

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.spendingForm", bindingResult);
            redirectAttributes.addFlashAttribute("spendingForm", spendingForm);

            System.out.println("błąd przy dodawaniu wytraty");
            return "redirect:/mainpage";
        }


        Spending spending = spendingForm.toSpending(user.getId());
        System.out.println(spending);
        spendingRepo.save(spending);
        return "redirect:/mainpage";
    }


}
