package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

public class EndToEndTest extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void completeOrderTest() {

        test.info("Opening application");

        // Step 1: Open site
        loginPage.openURL();
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        test.pass("Login page displayed");

        test.info("Logging into application");

        // Step 2: Login
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isProductsPageDisplayed());
        test.pass("Login successful, products page displayed");

        test.info("Adding product to cart");

        // Step 3: Add product
        productsPage.addProductToCart();
        test.pass("Product added to cart");

        // Step 4: Go to cart
        productsPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed());
        test.pass("Navigated to cart page");

        test.info("Proceeding to checkout");

        // Step 5: Checkout
        cartPage.clickCheckout();
        test.pass("Checkout page opened");

        test.info("Entering user details");

        // Step 6: Enter details
        checkoutPage.enterDetails("Sri", "Laksmi", "560001");
        test.pass("User details entered");

        // 🔥 CLOSE POPUP
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();
        } catch (Exception e) {}

        test.info("Continuing checkout");

        // Step 7: Continue
        checkoutPage.clickContinue();
        test.pass("Moved to overview page");

        test.info("Finishing order");

        // Step 8: Finish
        checkoutPage.finishOrder();
        test.pass("Clicked finish");

        test.info("Verifying order success");

        // Step 9: Verify
        if (checkoutPage.isOrderSuccessful()) {
            test.pass("Order placed successfully");
        } else {
            test.fail("Order failed");
            Assert.fail();
        }
    }
}