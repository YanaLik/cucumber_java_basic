package stepDefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;


public class TestSteps {
    private WebDriver driver;

    public TestSteps() {
        this.driver = Hooks.driver;
    }


    @Given("^I (?:am on|open) list of people with jobs page$")
    public void iAmOnListOfPeoplePage() throws Throwable {
        driver.get("https://kristinek.github.io/site/tasks/list_of_people_with_jobs.html");
    }

    @When("^I click on add person button$")
    public void iClickAddPersonButton() throws Throwable {
        driver.findElement(By.xpath("//*[text()='Add person']")).click();
    }

    @When("^I enter name in add person page \"([^\"]*)\"$")
    public void iEnterNameInAddPersonPage(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter job in add person page \"([^\"]*)\"$")
    public void iEnterJobInAddPersonPage(String job) throws Throwable {
        driver.findElement(By.id("job")).clear();
        driver.findElement(By.id("job")).sendKeys(job);
    }


    @When("^I enter input in add person page$")
    public void iEnterInputInAddPersonPage(Map<String, String> personInput) throws Throwable {
        if (personInput.containsKey("name")) {
        iEnterNameInAddPersonPage(personInput.get("name"));}
        iEnterJobInAddPersonPage(personInput.get("job"));
        Thread.sleep(5000);
        }

    @And("^I click add button$")
    public void iClickAddButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @Then("^I can see name 4 \"([^\"]*)\" in list of people$")
    public void iCanSeeNameInListOfPeople (String name) {
        assertEquals(name, driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]")).get(3).getText());
    }

    @Then("^I can see job 4 \"([^\"]*)\" in list of people$")
    public void iCanSeeJobInListOfPeople (String job) {
        String actualText = driver.findElements(By.xpath("//*[contains(@class,'job')]")).get(3).getText();
        assertEquals(job, actualText);
    }

    @When("^I click on edit pencil for person 3$")
    public void iClickOnEditPencil() throws Throwable {
        driver.findElements(By.xpath("//*[contains(@class,'fa fa-pencil')]")).get(2).click();
    }

    @When("^I click on edit button$")
    public void iClickOnEditButton() throws Throwable {
        driver.findElement(By.id("modal_button")).click();
    }

    @When("^I enter values in edit person page$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
    }
    @Then("^I can see name 3 \"([^\"]*)\" in list of people$")
    public void iCanSeeName3InListOfPeople (String name) {
        assertEquals(name, driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]")).get(2).getText());
    }

    @Then("^I can see job 3 \"([^\"]*)\" in list of people$")
    public void iCanSeeJob3InListOfPeople (String job) {
        String actualText = driver.findElements(By.xpath("//*[contains(@class,'job')]")).get(2).getText();
        assertEquals(job, actualText);
    }

    @When("^I click on remove sign for person 3$")
    public void iClickOnRemoveButton() throws Throwable {
        driver.findElements(By.xpath("//*[contains(@class,'w3-closebtn closebtn')]")).get(2).click();
    }


    @Then("^I can not see name Jane in list of people$")
      public void iCanNotSeeName3InListOfPeople () throws Exception {
        List<WebElement> names = driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]"));
        for (WebElement name : names) {
            if (name.getText().contains("Jane")) {
                fail();
            }
        }
    }
        @Then("^I can not see job Accountant in list of people$")
        public void iCanNotSeeJob3InListOfPeople () throws Exception {
            List<WebElement> jobs = driver.findElements(By.xpath("//*[contains(@class,'job')]"));
            for (WebElement job : jobs) {
                if (job.getText().contains("Accountant")) {
                    fail();
                }
            }
        }
        @When("^I click on reset button$")
    public void iClickOnResetButton() throws Throwable {
        driver.findElement(By.xpath("//*[text()='Reset List']")).click();
    }
    @When("^I enter values in add person page$")
    public void iEnterValuesAddPersonPage (Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
    }

    @And("^I see original list of names without added one$")
    public void iSeeOriginalListWithoutAddedOne() throws Throwable {
        assertNotEquals("4",driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]")).size());
    }

    @And("^I see original list of names including removed one$")
    public void iSeeOriginalListIncludingRemovedOne() throws Throwable {
        assertNotEquals("2",driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]")).size());
    }

    @And("^I see original list of names without inserted changes$")
    public void iSeeOriginalListWithoutInsertedChanges() throws Throwable {
        List<WebElement> allNames = driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]"));
        for (WebElement allName : allNames) {
            if (allName.getText().contains("John")) {
                fail();
            }
        }
    }

    @When("^I click on clear button$")
    public void iClickOnClearButton() throws Throwable {
        driver.findElement(By.xpath("//*[text()='Clear all fields']")).click();
    }

    @Then("^I see clear fields of the form$")
    public void iSeeClearFieldsOfTheForm() throws Throwable {
        String expectedName = "";
        assertEquals(expectedName,driver.findElement(By.id("name")).getAttribute("value"));
        String expectedJob = "";
        assertEquals(expectedName,driver.findElement(By.id("job")).getAttribute("value"));
    }
    @And("^I check the list, click on reset button, and check the new list$")
    public void iCompareTwoLists() throws Throwable {
        List<WebElement> allNames = driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]"));
        driver.findElement(By.xpath("//*[text()='Reset List']")).click();
        List<WebElement> allNamesAfterChange = driver.findElements(By.xpath("//*[contains(@class,'w3-xlarge name')]"));
        assertNotEquals(allNames,allNamesAfterChange);

    }


}







