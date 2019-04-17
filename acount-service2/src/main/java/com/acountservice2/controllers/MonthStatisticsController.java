package com.acountservice2.controllers;

import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.services.SpendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Controller
@RequestMapping("/stats")
@Slf4j
public class MonthStatisticsController {

    @Autowired
    SpendingService spendingService;
    @GetMapping("/spendingByCategory")
    public String getMonthlySpendingsByCategoryStats(ModelMap modelMap, @AuthenticationPrincipal User user){
        List<Spending> spendingList =spendingService.getSpendingForMonthOfYear(4, 2019, user);
        Map<String, Double> spendMap;
        spendMap = spendingList.stream().
                collect(groupingBy(spending -> spending.getCategory().getName(),
                        summingDouble(Spending::getAmount)));
        modelMap.addAttribute("spendMap", spendMap);
        return "spendCatStats";
    }
}
