package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	 WebDriver driver;

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    @FindBy(id = "first-name")
	    WebElement firstName;

	    @FindBy(id = "last-name")
	    WebElement lastName;

	    @FindBy(id = "postal-code")
	    WebElement postalCode;

	    @FindBy(id = "continue")
	    WebElement continueBtn;

	    @FindBy(className = "complete-header")
	    WebElement successMsg;

	    public void enterDetails(String fname, String lname, String zip) {
	        firstName.sendKeys(fname);
	        lastName.sendKeys(lname);
	        postalCode.sendKeys(zip);
	    }

	    public void clickContinue() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	    }

	    public void finishOrder() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.urlContains("checkout-step-two"));

	        WebElement finishBtn = driver.findElement(By.id("finish"));

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", finishBtn);
	        js.executeScript("arguments[0].click();", finishBtn);
	    }

	    public boolean isOrderSuccessful() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.urlContains("checkout-complete"));
	        wait.until(ExpectedConditions.visibilityOf(successMsg));

	        return successMsg.getText().contains("Thank you for your order!");
	    }
		}