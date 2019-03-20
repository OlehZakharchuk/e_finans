package com.acountservice2;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.entities.User;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.SpendingRepository;
import com.acountservice2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class RepositoryTesting {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SpendingRepository spendingRepository;
    @Autowired
    UserRepository userRepository;
    @Test
    public void testRepoCategory(){
//        Category category = categoryRepository.findAll().iterator().next();
//        System.out.println(category.getSpendings().size());
//        Assert.assertNotNull(category);
    }
    @Test
    public void ifUserWithSpending2andhalfexist(){
        Spending spending = spendingRepository.findAll().iterator().next();
        Assert.assertNotNull(spending);
    }
    @Test
    public void findAnyUser(){
        User user =userRepository.findAll().iterator().next();
        Assert.assertNotNull(user);
    }
    @Test
    public void ifUserUserHasSpending(){
        User user = userRepository.findById((long)7).get();
//        Spending spending = user.getSpendings().get(0);
//        log.info(spending.toString());
//        Assert.assertNotNull(spending);
    }


    @Test
    public void testRepoSpending(){
//        double amount = 2.5;
//        Category category = categoryRepository.findAll().iterator().next();
//
//        User user = userRepository.findById((long)7).get();
//        Assert.assertNotNull(user);
//        spendingRepository.save(new Spending(user, amount, category, new Date()));
//        List<Spending> spendings = spendingRepository.findByAmount(amount);
//        Assert.assertNotNull(spendings.get(0));
    }
}
