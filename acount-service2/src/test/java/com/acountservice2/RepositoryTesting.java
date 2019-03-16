package com.acountservice2;

import com.acountservice2.entities.Category;
import com.acountservice2.entities.Spending;
import com.acountservice2.repositories.CategoryRepository;
import com.acountservice2.repositories.SpendingRepository;
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
public class RepositoryTesting {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SpendingRepository spendingRepository;
    @Test
    public void testRepoCategory(){
        Category category = categoryRepository.findAll().iterator().next();
        Assert.assertNotNull(category);
    }
    @Test
    public void testRepoSpending(){
        double amount = 2.5;
        Category category = categoryRepository.findAll().iterator().next();

        spendingRepository.save(new Spending((long)7, amount, category, new Date()));
        List<Spending> spendings = spendingRepository.findByAmount(amount);
        Assert.assertNotNull(spendings.get(0));
    }
}
