package billing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPage {

	WebDriver driver;
	String browser = null;
	String url = null;

	@BeforeTest
	public void readConfig() {

		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("./Config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
			System.out.println(browser);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void Init() {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", ".\\EdgeDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

	}

	@Test
	public void logintest() {
		// By locators:
		By USERNAME_FIELD = By.xpath("//input[@id='username']");
		By PASSWORD_FIELD = By.xpath("//input[@id='password']");
		By SUBMIT_BUTTON = By.xpath("//button[@name='login']");

		// Data:
		String UserName = "demo@techfios.com";
		String PassWord = "abc123";

		driver.findElement(USERNAME_FIELD).sendKeys(UserName);
		driver.findElement(PASSWORD_FIELD).sendKeys(PassWord);
		driver.findElement(SUBMIT_BUTTON).click();

		Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Wrong Page");

		driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, 3);

		wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]"))));

		driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]")).click();

		Random rnd = new Random();

		int random = rnd.nextInt(999);

		String fullName = "shazmeen" + random;

		driver.findElement(By.xpath("//input[@id='account']")).sendKeys(fullName);

	}

}
