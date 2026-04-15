package test;

	import java.lang.reflect.Method;
	import java.time.Duration;
	import java.util.HashMap;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;

	import com.aventstack.extentreports.*;

import util.ExtentManager;

	public class BaseTest {

	    protected WebDriver driver;
	    protected ExtentReports extent;
	    protected ExtentTest test;

	    @BeforeMethod
	    public void setup(Method method) {

	        // 🔥 Extent Report setup
	        extent = ExtentManager.getReport();
	        test = extent.createTest(method.getName());

	        ChromeOptions options = new ChromeOptions();

	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-save-password-bubble");
	        options.addArguments("--disable-infobars");
	        options.addArguments("--disable-extensions");
	        options.addArguments("--disable-dev-shm-usage");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-blink-features=AutomationControlled");

	        HashMap<String, Object> prefs = new HashMap<>();
	        prefs.put("credentials_enable_service", false);
	        prefs.put("profile.password_manager_enabled", false);

	        options.setExperimentalOption("prefs", prefs);

	        driver = new ChromeDriver(options);

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }

	    @AfterMethod
	    public void tearDown() {

	        extent.flush();   // 🔥 VERY IMPORTANT (generates report)

	        driver.quit();
	    }
	}