package com.examples.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features={"classpath:features/todo.feature"}, glue={"com.examples.cucumber"})

public class CucumberRunner {
}