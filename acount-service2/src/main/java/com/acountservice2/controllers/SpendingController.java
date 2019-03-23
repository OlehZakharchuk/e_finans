package com.acountservice2.controllers;

import com.acountservice2.config.AccountProps;
import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.SpendingForm;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.SpendingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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


//    @GetMapping("/full")
//    public ResponseEntity<Iterable<Spending>> getAllSpendingOfCurrentUser(@AuthenticationPrincipal User user){
//        List<Spending> spendingsOfCurrentUser= spendingRepo.findByUserId((long)user.getId());
//        if(spendingsOfCurrentUser.isEmpty())
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(spendingsOfCurrentUser, HttpStatus.OK);
//    }
//    @GetMapping
//    public Spending getById(@AuthenticationPrincipal User user){
//        log.info(user.toString());
//        return spendingRepo.findAll().iterator().next();
//    }

}
