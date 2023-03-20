package hw5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	private WebDriver driver;
	private By underlay = By.xpath("//a[@class='header-style__underlay']");
	private By navigationItem = By.xpath("//a[@class='header-style__link header-style__link_primary']/span[text()='Каталог' and @class='header-style__sign']");

	public MainPage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void openCatalog() {
		driver.findElement(underlay).click();
		WebElement searchCatalog = driver.findElement(navigationItem);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", searchCatalog);
    }
}
