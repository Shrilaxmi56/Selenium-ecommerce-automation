package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement cartTitle;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    public boolean isCartPageDisplayed() {
        return cartTitle.getText().equals("Your Cart");
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }
}