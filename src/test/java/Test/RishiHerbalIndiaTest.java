package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page.RishiHerbalIndiaPage;

public class RishiHerbalIndiaTest {
	WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}
	
	@BeforeMethod
	public void urlload()
	{		
		driver.get("https://rishiherbalindia.linker.store/");
		driver.manage().window().maximize();		
	}
	
	@Test
	public void test() throws Exception
	{
		RishiHerbalIndiaPage ob=new RishiHerbalIndiaPage(driver);
		ob.account();
		ob.login();
		ob.logIn("edward076@gmail.com", "Edward076");
		ob.contentverification();
		ob.logoverification();
		ob.linkvalidation();
		ob.titleverification();
		ob.screenshot();
		ob.setup();
		ob.logout();
		
		
	}

	

}
