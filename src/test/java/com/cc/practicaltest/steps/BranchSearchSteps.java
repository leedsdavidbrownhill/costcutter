package com.cc.practicaltest.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertTrue;

public class BranchSearchSteps {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
    }

    @Given("^I am on the Costcutter search page$")
    public void visitHomepage() {
        driver.get("https://www.costcutter.co.uk/location-finder/");
    }

    @When("^I search for \"(.*)\"$")
    public void searchFor(String query) {
        WebElement element = driver.findElement(By.name("searchLocation"));
        element.sendKeys(query);
        driver.findElement(By.xpath("//*[@id=\"findOptions\"]/div[1]/form/fieldset/button")).click();
    }

    @Then("^the page should contain \"(.*)\"$")
    public void checkTitle(String query) {

        //*[@id="totals"]/div/h3/span
        Boolean found =  driver.findElement(By.xpath("//*[contains(text(), query)]")).isDisplayed();
        //WebElement element = driver.findElement(By.xpath("//*[@id=\"totals\"]/div/h3/span"));
        assertTrue(found);

    }
    @After
    public void closeBrowser() {
        driver.quit();
    }
}
