package hw5.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogPage {
	private WebDriver driver;
	private List<WebElement> arrayObject;
	private List<String> arrayString;
	
	private By section = By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper']");
	private By sectionComputer = By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains (text(), 'Компьютеры')]");
	private By sectionItem = By.xpath("//div[@data-id='2']//div[@class='catalog-navigation-list__aside-title']");
	private By itemAccessories = By.xpath("//div[text()=' Комплектующие ']");
	private By nameElement = By.xpath("//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//span[@class='catalog-navigation-list__dropdown-title']");
	private By descriptionElement = By.xpath("//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//span[@class='catalog-navigation-list__dropdown-description']");
	
	private String description;
	private String regexp;
	private Pattern pattern;
	private Matcher matcher;
	
	public CatalogPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean getNameSection(String name) {
		arrayObject = driver.findElements(section);
		arrayString = new ArrayList<>();
		for (WebElement element : arrayObject) {
			arrayString.add(element.getText());
        }
		
		return arrayString.contains(name);
    }
	
	public void openComputerSection() {
		driver.findElement(sectionComputer).click();
    }
	
	public boolean getNameItem(String name) {
		arrayObject = driver.findElements(sectionItem);
		arrayString = new ArrayList<>();
		for (WebElement element : arrayObject) {
			arrayString.add(element.getText());
        }
		
		return arrayString.contains(name);
    }
	
	public void openItemAccessories() {
		driver.findElement(itemAccessories).click();
    }
	
	public List<WebElement> findNamesElements() {
		return driver.findElements(nameElement);
	}
	
	public List<WebElement> findDescriptionElements() {
		return driver.findElements(descriptionElement);
	}
	
	public boolean showQuantity(WebElement desc) {
		description = desc.getText();
		regexp = "\\d+,*\\d*\\sтовар[ова]*";
		pattern = Pattern.compile(regexp);
		matcher = pattern.matcher(description);
        return matcher.find();
	}
	
	public boolean showMinPrice(WebElement desc) {
		description = desc.getText();
		regexp = "от\\s\\d+,\\d{2}\\sр.";
		pattern = Pattern.compile(regexp);
		matcher = pattern.matcher(description);
        return matcher.find();
	}
}
