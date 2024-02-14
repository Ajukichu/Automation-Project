package Page;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class RishiHerbalIndiaPage {
	WebDriver driver;
	By logo=By.xpath("//*[@id=\"logo\"]/a/img");
	By account=By.xpath("//*[@id=\"top-links\"]/ul/li[1]/a");
	By login=By.xpath("//*[@id=\"top-links\"]/ul/li[1]/ul/li[2]/a");
	By email=By.xpath("//*[@id=\"cont-login-with-email\"]/form/div[1]/input");
	By password=By.xpath("//*[@id=\"cont-login-with-email\"]/form/div[2]/input");
	By logIn=By.xpath("//*[@id=\"cont-login-with-email\"]/form/button[1]");
	By search=By.xpath("//*[@id=\"search\"]/input");
	By product=By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div/div[1]/div/a/img");
	By addtowishlist=By.xpath("//*[@id=\"prod_cont\"]/div/div[4]/button[1]/span");
	By viewwishlist=By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span");
	
	//constructor
	public RishiHerbalIndiaPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void account()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(account).click();
		
	}
	public void login()
	{
		driver.findElement(login).click();
	}
	public void logIn (String Email,String Password)
	{
		driver.findElement(email).sendKeys(Email);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(logIn).click();
	}
	
	//title verification
	public void titleverification()
	{
		String actualtitle=driver.getTitle();
		System.out.println(actualtitle);
		String expectedtitle="Rishi Herbal India";
		if(actualtitle.contains(expectedtitle))
		{
			System.out.println("Title verified successfully");
		}
		else
		{
			System.out.println("Incorrect Title verfied");
		}
	}
	
	//content verification
	public void contentverification()
	{
		String content=driver.getPageSource();
		if(content.contains("AGRICULTURE"))
		{
			System.out.println("Content verfied successfully");
		}
		else
		{
			System.out.println("Incorrect content verified");
		}
	}
	
	//link validation
	public void linkvalidation() throws Exception
	{
		String baseurl="https://rishiherbalindia.linker.store/";
		driver.get(baseurl);
		URL a=new URL(baseurl);
		HttpURLConnection b=(HttpURLConnection)a.openConnection();
		b.connect();
		
		if(b.getResponseCode()==200)
		{
			System.out.println("Valid url : " +baseurl);
		}
		else
		{
			System.out.println("Invalid url : " +baseurl);
		}
		
	}
	
	//logoverification
	public void logoverification() 
	{
		WebElement logos= driver.findElement(logo);
		if(logos.isDisplayed())
		{
			System.out.println("Logo is present");
		}
		else
		{
			System.out.println("Logo is absent");
		}

	}
	
	//screenshot
	public void screenshot() throws Exception
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src,new File("./ScreenShot//rishiherbalindiahome.png"));

	}
	
	public void setup() throws Exception
	{
		driver.findElement(search).sendKeys("Herbal Agro Growth Booster",Keys.ENTER);
		String parentwindow=driver.getWindowHandle();
		Thread.sleep(3000);
		driver.findElement(product).click();
		
		Set<String> allwindowhandles=driver.getWindowHandles();
		for(String handle:allwindowhandles)
		{
			if(!handle.equalsIgnoreCase(parentwindow))
			{
				driver.switchTo().window(handle);
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(5000);
				driver.findElement(addtowishlist).click();
				driver.navigate().refresh();
				Thread.sleep(3000);
				driver.findElement(viewwishlist).click();			
			}
		}	

	}
	public void logout()
	{
		driver.findElement(addtowishlist).click();
		driver.findElement(viewwishlist).click();
		driver.findElement(account).click();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[1]/ul/li[3]/a")).click();
	}
	
}























