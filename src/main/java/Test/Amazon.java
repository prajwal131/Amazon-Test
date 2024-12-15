package Test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./Softwares/chromedriver1.exe");
		
		 
        ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Users\\Shashank\\Downloads\\chrome-win64\\chrome.exe");
	     options.addArguments("--remote-allow-origins=*");
        

		
      WebDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		
		try {
		driver.get("https://www.amazon.in");
		
		Thread.sleep(1000);
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Wrist Watches");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//handling scroll bar
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		
		//select leather filter checkbox
		WebElement leatherfilter = driver.findElement(By.xpath("//a[@aria-label='Apply the filter Leather to narrow results']//i[@class='a-icon a-icon-checkbox']"));
		leatherfilter.click();
		waitForPageLoad(driver);
		System.out.println("Leather Filter Applied");
		
		//scoll page 
		js.executeScript("window.scrollBy(500, 200)");
		
		//select brand as fastrack
		WebElement fastrackfilter = driver.findElement(By.xpath("//a[@aria-label='Apply the filter Fastrack to narrow results']//i[@class='a-icon a-icon-checkbox']"));
		fastrackfilter.click();
		waitForPageLoad(driver);
		System.out.println("Fastrack Brand Filter Applied");
		
		//navigating to 2nd page
		WebElement secondpage = driver.findElement(By.xpath("//a[normalize-space()='2']"));
		secondpage.click();
		waitForPageLoad(driver);
		System.out.println("Navigated to 2nd page");
		
		Thread.sleep(3000);

		//selecting first item in the 2nd page
		WebElement firstproduct = driver.findElement(By.xpath("(//a[@target='_blank']//h2//span)[1]"));
		firstproduct.click();
		waitForPageLoad(driver);
		System.out.println("First product selected in 2nd page");
		
		//switching between the tabs
		Set<String> tabswitch = driver.getWindowHandles();
		String[] handles = tabswitch.toArray(new String[0]);
		
		driver.switchTo().window(handles[1]);
		System.out.println("Now in Tab:"+ driver.getTitle());
		
		//add product to cart
		WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
		addtocart.click();
		System.out.println("Product successfully added to cart");
		
		Thread.sleep(5000);
		
		
	}
		finally {
			driver.quit();
		}
	}
	private static void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));
}
}
