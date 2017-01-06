package niki_ai;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void test1_blankUserNameTest()
	{
		driver.findElement(By.id("com.nikiapp:id/login_username")).sendKeys("");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.nikiapp:id/startChatBtn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
			driver.findElement(By.name("GroupChat"));
			Assert.assertTrue(false);
		}catch(Exception e){
			Assert.assertTrue(true); // driver should not be able to find the next screen indicating login has failed
		}
	}


	@Test
	public void test2_addUserName()
	{
		driver.findElement(By.id("com.nikiapp:id/login_username")).sendKeys("hi@xyz.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.nikiapp:id/startChatBtn")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
			driver.findElement(By.name("GroupChat"));
			Assert.assertTrue(true); // driver should be able to find the next screen indicating login is successful
		}catch(Exception e){
			Assert.assertTrue(false); 
		}
	}

	@Test
	public void test3_sendGroupChatMessage(){
		driver.findElement(By.name("GroupChat")).click();
		driver.findElement(By.name("Enter message")).sendKeys("hi");
		driver.findElement(By.id("com.nikiapp:id/sendMessage")).click();
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test4_verifySentGroupMessage(){
		driver.findElement(By.name("GroupChat")).click();
		String message = driver.findElement(By.id("com.nikiapp:id/chatMessage")).getText();
		Assert.assertEquals("hi", message);
	}

	@Test
	public void test5_closeGroupChat(){
		driver.findElement(By.className("android.widget.ImageButton")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try{
			driver.findElement(By.name("com.nikiapp:id/msg_box"));
			Assert.assertTrue(false); // driver should not be able to find the group chat window
		}catch(Exception e){
			Assert.assertTrue(true); 
		}
	}

	@Test
	public void test6_createPrivateChatWithUser() {
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.findElement(By.id("com.nikiapp:id/privateChat")).click();
		driver.findElement(By.id("com.nikiapp:id/editTextDialogUserInput")).sendKeys("sitaram");
		driver.findElement(By.name("OK")).click();
	}

	@Test
	public void test7_openPrivateChat(){
		driver.findElement(By.name("Sitram")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void test8_closePrivateChat(){
		driver.findElement(By.className("android.widget.ImageButton")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void end(){
		driver.quit();
	}
}
