package uk.co.automationtesting;

import java.io.IOException;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.*;


@Listeners(resourses.Listeners.class)

public class OrderCompleteTest2 extends Hooks {

	public OrderCompleteTest2() throws IOException {
		super();
	}
	


	@Test
	public void endToEndTest() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(getDriver(), 20);
		
		ExtentManager.log("Starting OrderCompleteTest...");
		
		HomePage home = new HomePage();
		//wait.until(ExpectedConditions.elementToBeClickable(home.getCookie())).click();
		waitForElementClickable(home.getCookie(), 10);
		home.getCookie().click();
		wait.until(ExpectedConditions.elementToBeClickable(home.getTestStoreLink())).click();
		ExtentManager.pass("Have successfully reached store homepage");
		
		ShopHomepage shopHome = new ShopHomepage();
		wait.until(ExpectedConditions.elementToBeClickable(shopHome.getProdOne())).click();
		
		ShopProductPage shopProd = new ShopProductPage();
		wait.until(ExpectedConditions.elementToBeClickable(shopProd.getSizeOption()));
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		
		ShopContentPanel cPanel = new ShopContentPanel();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(cPanel.getCheckoutBtn())).click();
		
		ShoppingCart cart = new ShoppingCart();
		wait.until(ExpectedConditions.elementToBeClickable(cart.getHavePromo())).click();
		Thread.sleep(2000);
		cart.getPromoTextBox().sendKeys("20OFF");
		Thread.sleep(1000);
		cart.getPromoAddBtn().click();
		cart.getProceedToCheckoutBtn().click();
		
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		wait.until(ExpectedConditions.elementToBeClickable(pInfo.getFirstNameField()));
		pInfo.getFirstNameField().sendKeys("Ivaylo");
		pInfo.getLastnameField().sendKeys("Tsvetkov");
		pInfo.getEmailField().sendKeys("tes1t@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		Thread.sleep(1000);
		orderDelivery.getAddressField().sendKeys("123 Mein A1");
		orderDelivery.getCityField().sendKeys("Sofia");
		wait.until(ExpectedConditions.elementToBeClickable(orderDelivery.getStateDropdown()));
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("57000");
		orderDelivery.getContinueBtn().click();
		
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(shipMethod.getContinueBtn())).click();
		
		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox();
		orderPay.getOrderBtn().click();
	}
}
