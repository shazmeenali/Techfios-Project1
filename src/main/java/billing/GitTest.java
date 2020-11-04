package billing;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GitTest {

	WebDriver driver;

	@Before
	public void LaunchBrowser() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.techfios.com/billing/?ng=admin");

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void loginAccess() throws InterruptedException {
		
	

		WebElement USERNAME_FIELD = driver.findElement(By.xpath("//input[@id='username']"));

		WebElement PASSWORD_FIELD = driver.findElement(By.xpath("//input[@id='password']"));

		By LOGIN_BUTTON = (By.xpath("//button[@name='login']"));

		USERNAME_FIELD.sendKeys("demo@techfios.com");

		PASSWORD_FIELD.sendKeys("abc123");

		driver.findElement(LOGIN_BUTTON).click();

		driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'Add New Order')]"))));

		driver.findElement(By.xpath("//a[contains(text(),'Add New Order')]")).click();

		WebElement ADDORDER_TITLE = driver.findElement(By.xpath("//h2[contains(text(),' Orders ')]"));

		System.out.println(ADDORDER_TITLE.getText());
		
		WebElement PRODUCT_DROPDOWN = driver.findElement(By.xpath("//select[@id='pid']"));

		Select Product = new Select(PRODUCT_DROPDOWN);
		
		Product.selectByVisibleText("Wheels");
		
		WebElement CUSTOMER_DROPDOWN = driver.findElement(By.xpath("//select[@id='cid']"));

		Select Customer = new Select(CUSTOMER_DROPDOWN);
		
		Customer.selectByVisibleText("test");
		
		WebElement STATUS_DROPDOWN = driver.findElement(By.xpath("//select[@id='status']"));

		Select Status = new Select(STATUS_DROPDOWN);
		
		Status.selectByVisibleText("Active");
		
		WebElement BILLING_CYCLE = driver.findElement(By.xpath("//select[@id='billing_cycle']"));
		
		Select Billing_cycle = new Select(BILLING_CYCLE);
		
		Billing_cycle.selectByVisibleText("Quarterly");
		
		WebElement SUBMIT_ORDER = driver.findElement(By.xpath("//button[@id='submit']"));
		
		SUBMIT_ORDER.click();
		
		
		


		
			
		}
	public void tearDown() {

		driver.close();

	}
}
