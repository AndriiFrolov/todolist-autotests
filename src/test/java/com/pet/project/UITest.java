package com.pet.project;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UITest extends BaseTest {

    @Test
    public void twoNewRecordsDefinedInJson() {
        open(testConfig.baseUrl());
        String summary = "TestTask" + RandomStringUtils.randomAlphabetic(10);
        String description = "TestDescription" + RandomStringUtils.randomAlphabetic(10);

        $(By.id("summary-text")).sendKeys(summary);
        $(By.id("description-text")).sendKeys(description);
        $(By.id("add-btn")).click();


        SelenideElement row = $(By.xpath(String.format("//*[.='%s']", summary))).parent();
        String createdSummary = row.find(By.id("summary")).getText();
        String createdDescription = row.find(By.id("description")).getText();
        String createdStatus = row.find(By.id("status")).getText();

        assertThat(createdSummary, equalTo(summary));
        assertThat(createdDescription, equalTo(description));
        assertThat(createdStatus, equalTo("NOT_STARTED"));
    }

}
