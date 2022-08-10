package ua.hillel.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Maxim Karpenko mkarpenko@modeln.com
 */

public class FirstTest {
  WebDriver driver;

  @BeforeClass
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }
  @Test
  public void loginTest() {
    driver.get("https://the-internet.herokuapp.com/");

    driver.findElement(By.linkText("Form Authentication")).click();

    WebElement usernameInput = driver.findElement(By.cssSelector("#username"));
    usernameInput.clear();
    usernameInput.sendKeys("tomsmith");

    WebElement passInput = driver.findElement(By.xpath("//input[@type='password']"));
    passInput.clear();
    passInput.sendKeys("SuperSecretPassword!");

    driver.findElement(By.cssSelector("button[type='submit']")).click();

    String text = driver.findElement(By.tagName("h2")).getText();
    Assert.assertEquals(text, "Secure Area", "User should be logged in");

  }

  @AfterClass(alwaysRun = true)
  public void closeDriver() {
    driver.quit();
  }
}
