package hw5.tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import hw5.pages.CatalogPage;
import hw5.pages.MainPage;

class TestOnliner {
	private static WebDriver driver;
    private static ChromeOptions chromeOptions = new ChromeOptions();
    private MainPage mainPage;
    private CatalogPage catalogPage;
    
	@BeforeEach
    public void openOnliner() {
		chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver_win32\\chromedriver.exe");
		
		Map<String, String> mobile = new HashMap<String, String>();
		mobile.put("deviceName","Samsung Galaxy S8+");
		chromeOptions.setExperimentalOption("mobileEmulation", mobile);
		
		driver = new ChromeDriver(chromeOptions);
        driver.get("https://onliner.by");
        
        mainPage = new MainPage(driver);
        catalogPage = new CatalogPage(driver);
        mainPage.openCatalog();
    }

	@Test
	public void testAvailableSection() {
		Assertions.assertAll(
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Onlíner Prime")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Электроника")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Компьютеры и сети")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Бытовая техника")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("На каждый день")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Стройка и ремонт")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Дом и сад")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Авто и мото")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Красота и спорт")),
        		() -> Assertions.assertTrue(catalogPage.getNameSection("Детям и мамам"))
        );
	}
	
	@Test
	public void testItemsSection() {
		catalogPage.openComputerSection();
        Assertions.assertAll(
        		() -> Assertions.assertTrue(catalogPage.getNameItem("Ноутбуки, компьютеры, мониторы")),
        		() -> Assertions.assertTrue(catalogPage.getNameItem("Комплектующие")),
        		() -> Assertions.assertTrue(catalogPage.getNameItem("Хранение данных")),
        		() -> Assertions.assertTrue(catalogPage.getNameItem("Сетевое оборудование"))
        );
	}

	@Test
	void testElementsItem() {
		catalogPage.openComputerSection();
		catalogPage.openItemAccessories();
        
        for (WebElement name : catalogPage.findNamesElements()) {
        	Assertions.assertFalse(name.getText().isEmpty());
        }
        
        for (WebElement desc : catalogPage.findDescriptionElements()) {
        	Assertions.assertAll(
        			() -> Assertions.assertTrue(catalogPage.showQuantity(desc)),
        			() -> Assertions.assertTrue(catalogPage.showMinPrice(desc))
            );
        }
	}
		
	@AfterEach
	public void cleanUp() {
		driver.quit();
    }
}
