package com.pet.project;


import com.pet.project.config.TestConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITest;
import org.testng.annotations.BeforeSuite;


public class BaseTest implements ITest {

    protected String testName;

    protected TestConfig testConfig = ConfigFactory.create(TestConfig.class, System.getProperties());
    
    @Override
    public String getTestName() {
        return testName;
    }
}
