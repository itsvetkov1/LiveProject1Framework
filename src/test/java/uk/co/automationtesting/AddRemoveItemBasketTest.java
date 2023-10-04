package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resourses.Listeners.class)

public class AddRemoveItemBasketTest extends Hooks {

	public AddRemoveItemBasketTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	@Test
	public void addRemoveItem() throws IOException, InterruptedException {
		
			ExtentManager.log("Starting AddRemoveItemBasketTest...");
		
			HomePage home = new HomePage();
			home.getCookie().click();
			home.getTestStoreLink().click();
			
			ShopHomepage shopHome = new ShopHomepage();
			ExtentManager.pass("Reached the Shop Home page");
			shopHome.getProdOne().click();
			
			ShopProductPage shopProd = new ShopProductPage();
			ExtentManager.pass("Reached the Shop product page");
			Select option = new Select(shopProd.getSizeOption());
			option.selectByVisibleText("M");
			ExtentManager.pass("Have successfully selected product size");
			shopProd.getQuantIncrease().click();
			ExtentManager.pass("Have succesfully increased quantity");
			shopProd.getAddToCartBtn().click();
			ExtentManager.pass("Have successfully added product to the basket");
			
			
			ShopContentPanel cPanel = new ShopContentPanel();
			Thread.sleep(2000);
			cPanel.getContinueShopBtn().click();
			Thread.sleep(2000);
			shopProd.getHomepageLink().click();
			Thread.sleep(2000);
			shopHome.getProdTwo().click();
			shopProd.getAddToCartBtn().click();
			Thread.sleep(1000);
			cPanel.getCheckoutBtn().click();
			
			ShoppingCart cart = new ShoppingCart();
			cart.getDeleteItemTwo().click();
			
			waitForElementInvisible(cart.getDeleteItemTwo(), 10);
			
			
			
			System.out.println(cart.getTotalValue().getText());
			
			try {
				Assert.assertEquals("$45.24", cart.getTotalValue().getText());
				ExtentManager.pass("Total amount matches the expected amount");
			} catch (AssertionError e) {
				Assert.fail("Cart amount did not match the expected amount, it found" + cart.getTotalValue().getText() +
						" expected 45.24");
				ExtentManager.fail("The total amount did not match the expected amount");
			}
			
			
			
			
			
			
	}
	

}
