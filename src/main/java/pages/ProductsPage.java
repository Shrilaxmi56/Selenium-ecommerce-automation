package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

//	Global WebDriver
	WebDriver driver;

	
//	parameterized constructor to decorate the page 
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		
//		Decorate the page 
		PageFactory.initElements(driver, this);
		
	}
	
	
//
@FindBy(className = "title")
WebElement productTitle;

@FindBy(id = "add-to-cart-sauce-labs-backpack")
WebElement addBackpack;

@FindBy(className = "shopping_cart_link")
WebElement cartIcon;

public boolean isProductsPageDisplayed() {
    return productTitle.isDisplayed();
}

public void addProductToCart() {
    addBackpack.click();
}

public void goToCart() {
    cartIcon.click();
}
}