package com.palmieri.cucumber.steps;

import com.palmieri.cucumber.models.User;
import cucumber.api.DataTable;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserSteps {

        private User user = new User();
        private ArrayList<User> userList = new ArrayList<>();

        @Given("^that the user (.*) is given a task to clear (.*) certification exam$")
        public void certificationName(String name, String certificationName){
            user.setName(name);
            user.setCertification(certificationName);
        }

        @When("^(.*) got (\\d+) marks in exam$")
        public void gotMarks(String name, int marks){
            user.setName(name);
            user.setMarks(marks);
        }

        @Then("^(.*) is known as (.*) certified$")
        public void certificatedYes(String name, String certificationName){
            assertThat(name,is(user.getName()));
            assertThat(user.getCertification(),equalTo(certificationName));
            assertThat(user.getResult(),is(true));
        }

        @Then("^(.*) is not known as (.*) certified$")
        public void certificatedNo(String name, String certificationName){
            assertThat(name,is(user.getName()));
            assertThat(user.getCertification(),equalTo(certificationName));
            assertThat(user.getResult(),is(false));
        }

    @Given("^that more users take a subject exam$")
    public void haveBooksInTheStoreByList(DataTable table) {

        List<List<String>> rows = table.asLists(String.class);

        for (List<String> columns : rows) {
            User us = new User();
            us.setName(columns.get(0));
            us.setCertification(columns.get(1));
            us.setMarks(Integer.valueOf(columns.get(2)));
            userList.add(us);
        }
    }
    @Then("^only (.*) users have passed the exam$")
            public void howManyPassed(int check){
        int num = 0;
            for (User u: userList) {
            if(u.getResult()) num++;
        }
            assertThat(num,equalTo(check));
    }

}


