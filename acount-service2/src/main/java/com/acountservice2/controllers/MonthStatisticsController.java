package com.acountservice2.controllers;

import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.formobjects.StatisticForm;
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
    @GetMapping("/spendingByCategoryCurrMon")
    public String getCurrentMonthlySpendingsByCategoryStats(ModelMap modelMap, @AuthenticationPrincipal User user){
        List<Spending> spendingList =spendingService.getSpendingForMonthOfYear(4, 2019, user);
        Map<String, Double> spendMap = getCategorySumPair(spendingList);
        modelMap.addAttribute("spendMap", spendMap);

        StatisticForm statForm = new StatisticForm();
        modelMap.addAttribute("statForm", statForm);

        return "spendCatStats";
    }

    @GetMapping("/spendingByCategory")
    public String getSpendingsByCategoryStats(ModelMap modelMap,
                                              StatisticForm statForm, @AuthenticationPrincipal User user){

        List<Spending> spendingList =null;
        if(statForm.getIsYear()){  //get spending for whole year
            spendingList=spendingService.getListSpendingForYear(user, statForm.getYear());
        }
        else {
            spendingList=spendingService.getSpendingForMonthOfYear(statForm.getMonth(), statForm.getYear(), user);
        }

        Map<String, Double> spendMap = getCategorySumPair(spendingList);
        modelMap.addAttribute("spendMap", spendMap);

        modelMap.addAttribute("statForm", statForm);

        return "spendCatStats";
    }
    private Map<String, Double> getCategorySumPair(List<Spending> spendingList){
        return spendingList.stream().
                collect(groupingBy(spending -> spending.getCategory().getName(),
                        summingDouble(Spending::getAmount)));


    }
}
