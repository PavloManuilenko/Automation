package bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;


public class TestSteps extends StoryBase{

    @Given("I open Google Page")
    public void openBrowser() {
        driver.get("google.com");
    }

    @When("When")
    public void search() {
        System.out.println("When");
    }

    @Then("Then")
    public void checkSearch() {
        System.out.println("Then");
    }
}
