package testsuite;

import browsefactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String BaseUrl = "http://the-internet.herokuapp.com/login";
    @Before

    public void setUp()
    {
        openBrowser(BaseUrl);
    }
    //  Verifying user should be able to login with valid credentials
    @Test
        public void userSholdLoginSuccessfullyWithValidCredentials(){
        String expectedText = "Secure Area";
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[normalize-space()='Secure Area']"));
        String actualText = actualTextElement.getText();

        Assert.assertEquals("Can not access secure area",expectedText,actualText);
    }
    // Verifying error message with invalid user name and valid password
    @Test
    public void verifyTheUsernameErrorMessage(){
        // This is from requirement
        String expectedText = "Your username is invalid!\n" +
                "×";
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith1");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");
        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();
        WebElement actualTextElement = driver.findElement(By.id("flash"));
        String actualText = actualTextElement.getText();
        // Verifying actual text is matching with expected text
        Assert.assertEquals("Error message text not displayed",expectedText,actualText);
    }
    @Test
    // Verifying error message with valid user name and invalid password
    public void verifyThePasswordErrorMessage(){
        // This is from requirement
        String expectedText = "Your password is invalid!\n" +
                "×";
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");
        WebElement loginButton = driver.findElement(By.xpath("//i[contains(text(),'Login')]"));
        loginButton.click();
        WebElement actualTextElement = driver.findElement(By.id("flash"));
        String actualText = actualTextElement.getText();
        System.out.println(actualText);
        // Verifying actual text is matching with expected text
        Assert.assertEquals("Error message text not displayed",expectedText,actualText);
    }

    @After
    public void tearDown()
    {
      // closeBrowser();
    }
}
