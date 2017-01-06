package niki_ai;

import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidKeyCode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;

import org.openqa.selenium.remote.CapabilityType;


public class nikiApp {
	
	WebDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException, InterruptedException {
		File app = new File(System.getProperty("user.dir")+ "/niki_ai_app-debug.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "MI 4I");
		capabilities.setCapability(CapabilityType.VERSION,"5.0.2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.nikiapp");
		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
		
	}
	
	@Test
		public void blankUserName()
		{
		driver.findElement(By.id("com.nikiapp:id/login_username")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.nikiapp:id/startChatBtn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	
	
	@Test
	public void addUserName()
	{
	driver.findElement(By.id("com.nikiapp:id/login_username")).sendKeys("hi@xyz.com");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.nikiapp:id/startChatBtn")).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	
	@Test
		public void closeGroupChat(){
		driver.findElement(By.name("GroupChat")).click();
		driver.findElement(By.name("Enter message")).sendKeys("hi");
		driver.findElement(By.id("com.nikiapp:id/sendMessage")).click();
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.name("GroupChat")).click();
		driver.findElement(By.className("android.widget.ImageButton")).click();		
	}
	
	
	@Test
		public void privateChat() {
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.findElement(By.id("com.nikiapp:id/privateChat")).click();
		driver.findElement(By.id("com.nikiapp:id/editTextDialogUserInput")).sendKeys("sitaram");
		driver.findElement(By.name("OK")).click();
	}
	
	
	@Test
	public void openPrivateChat()
	{
		driver.findElement(By.id("com.nikiapp:id/chatName")).sendKeys("Sitaram");
		((AndroidDriver) driver).tap(1, 100, 100, 100);
		driver.findElement(By.className("android.widget.ImageButton")).click();
	}
	
	@AfterTest
	public void end(){
		driver.quit();
	}
}
