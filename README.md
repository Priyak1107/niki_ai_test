# niki_ai_test
niki_ai App is a demo chatting App.

# Selenium + Appium test case for niki_ai Android App using eclispe IDE and TestNG
Selenium is a portable software testing framework for web applications. 
Appium is an open-source tool for automating native, mobile web, and hybrid applications on iOS and Android platforms.
We are using Eclispe IDE for writing testcases in java and ui automator has been used to find the locators.

# Motivation
# Appium http://appium.io/
# Selenium http://www.seleniumhq.org/

# Installation Process
git clone https://github.com/Priyak1107/niki_ai_test.git
import it as eclispe project
Run it as "TestNG Test"

# Code Example
How to initialize webdrivers
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
  
  Sample positive test case for adding a valid user
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
  
  # Test Output
  In above positive test case, it validates a user and it redirects user to next screen.
  
  
