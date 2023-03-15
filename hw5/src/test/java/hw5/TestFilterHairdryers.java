package hw5;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFilterHairdryers {
	
	private static ChromeOptions chromeOptions = new ChromeOptions();
	private static WebDriver driver;
	private String ELEMENT_PATTERN = "//div[@class='schema-product__title']//span[contains(text(), '%s')]";
	
	@Test
	public void testFilterBrand() {
		chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 250)");
        driver.findElement(By.xpath("//input[@value='rowenta']/following-sibling::span")).click();
        
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(String.format(ELEMENT_PATTERN, "Rowenta")), 30));
        
        List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='schema-product__title']//span"));
        for (WebElement ellemm : listElements) {
        	Assertions.assertTrue(ellemm.getText().contains("Rowenta"));
        }
        
        driver.quit();
	}
	
	@Test
	public void testFilterBrands() {
		chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        driver.get("https://onliner.by");
        driver.findElement(By.xpath("//span[text()='Фены']")).click();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 350)");
        driver.findElement(By.xpath("//input[@value='dyson']/following-sibling::span")).click();
        driver.findElement(By.xpath("//div[@class='schema-filter-control__item']")).click();
        driver.findElement(By.xpath("//div[@class='schema-filter-popover__column-item']//span[text()='Braun']")).click();
        
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(String.format(ELEMENT_PATTERN, "Braun")), 1));
        
        List<WebElement> listElements = driver.findElements(By.xpath("//div[@class='schema-product__title']//span"));
        for (WebElement ellemm : listElements) {
        	Assertions.assertTrue(ellemm.getText().contains("Dyson") || ellemm.getText().contains("Braun"));
        }
        
        driver.quit();
	}
}
