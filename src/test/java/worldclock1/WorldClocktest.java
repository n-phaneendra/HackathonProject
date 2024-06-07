package worldclock1;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorldClocktest 
{
	WebDriver driver;
	Becognizantpage bp;
	OneCognizantPage op;
	String becognizanthandle;
	@BeforeClass
	@Parameters({"Browser"})
	public void driversetup(String br) throws InterruptedException
	{
		if(br.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else
		{
			System.out.println("Wrong Input.");
		}
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		driver.manage().window().maximize();
		Thread.sleep(10000);
		bp = new Becognizantpage(driver);
		op= new OneCognizantPage(driver);
	}
	
	@Test(priority = 0,groups = {"sanity","smoke","regression"})
	public void details() throws InterruptedException, IOException
	{
		String user = bp.user();
		Screenshot.FullScreenshot(driver, "user");
		System.out.println("User Details :\n"+user+"\n");
	}
	
	@Test(priority = 1,groups = {"sanity","smoke","regression"})
	public void validateWorldclock() throws IOException, InterruptedException
	{
		
		Boolean status = bp.world();
		Thread.sleep(2000);
		Screenshot.FullScreenshot(driver, "worldclock");
		Assert.assertEquals(true, status);
		System.out.println("WorldClock Section is displayed.\n");
	}
	
	@Test(priority = 2,groups = {"sanity","regression"})
	public void validatecurrentWeek() throws InterruptedException
	{
		Thread.sleep(2000);
		Boolean status = bp.weekdayvalidate();
		Assert.assertEquals(true, status);
		System.out.println("Week Days validates.\n");
	}
	
	@Test(priority = 3,groups = {"sanity","regression"})
	public void validatedate() throws InterruptedException
	{
		Thread.sleep(3000);
		Boolean status = bp.datevalidate();
		Assert.assertEquals(true,status);
		System.out.println("Date validates.\n");
		
	}
	
	@Test(priority = 4,groups = {"regression"})
	public void validatebangtimezone() throws InterruptedException
	{
		Thread.sleep(3000);
		Boolean status = bp.bangtimezonevalidate();
		Assert.assertEquals(true, status);
		System.out.println("Bangalore Time-Zone validateds.\n");
	}
	
	@Test(priority = 5,groups = {"regression"})
	public void bangtimevalidate() throws InterruptedException
	{
		Boolean status = bp.bengtimevalidates();
		Assert.assertEquals(true, status);
		System.out.println("Bangalore time validated.\n");
		
	}
	
	@Test(priority =6,groups = {"regression"})
	public void londontimezoneValidate()
	{
		Boolean status = bp.londontimezonevalidate();
		Assert.assertEquals(true, status);
		System.out.println("London TimeZone Validated.\n");
	}
	
	@Test(priority = 7,groups = {"regression"})
	public void LondonTimeValidate()
	{
		Boolean status = bp.londontimevalidate();
		Assert.assertEquals(true, status);
		System.out.println("London Time validated.\n");
	}
	
	@Test(priority = 8,groups = {"regression"})
	public void LondonTimeZonedifValidate()
	{
		Boolean status = bp.londonTimezonedifvalidate();
		Assert.assertEquals(true, status);
		System.out.println("London Time Zone diff validated.\n");
	}
	
	@Test(priority = 9,groups = {"regression"})
	public void NYtimezonevalidate()
	{
		Boolean status = bp.NYtimezonevalidate();
		Assert.assertEquals(true, status);
		System.out.println("NEW-YORK Time Zone validated.\n");
	}
	
	@Test(priority = 10,groups = {"regression"})
	public void NYtimevalidate()
	{
		Boolean status = bp.NYtimevalidate();
		Assert.assertEquals(true, status);
		System.out.println("NEW-YORK Time validated.\n");
	}
	
	@Test(priority = 11,groups = {"regression"})
	public void NYtimedifvalidate()
	{
		Boolean status = bp.NYTimezonedifvalidate();
		Assert.assertEquals(true, status);
		System.out.println("NEW-YORK Time Zone dif validated.\n");
	}
	//close the pop up
	@Test(priority = 12,groups = {"sanity","regression"})
	public void Nclosepopup()
	{
		Close.Popup(driver);
	}
	
	
	@Test(priority = 13,groups = {"sanity","smoke","regression"})
	public void clickonecognizant() throws IOException
	{
		becognizanthandle = driver.getWindowHandle();
		bp.clickonecog();
		Screenshot.FullScreenshot(driver, "onecog");
		System.out.println("Clicked on one cognizant.\n");
	}
	
	
	//switch to one cognizant
	@Test(priority = 14,groups = {"sanity","smoke","regression"})
	public void changewindow()
	{
		Set<String> windowhandles = driver.getWindowHandles();
		for(String Window:windowhandles) {
			if(Window.equals(becognizanthandle)) {
			}
			else {
				driver.switchTo().window(Window);
			}
		}
	}
	
	//click on view all applications
	@Test(priority = 15,groups = {"sanity","smoke","regression"})
	public void Clickallapps() throws InterruptedException, IOException
	{
		op.clickallapps();
		System.out.println("All Apps clicked.");
	}
	
	@Test(priority = 16,groups = {"sanity","regression"})
	public void ValidateAtoZ() throws InterruptedException, IOException
	{
		Boolean status = op.validateAToZ();
		Assert.assertEquals(true, status);
		System.out.println("A-Z Alphabets are displayed in App Store header.");
	}
	
	@Test(priority = 17,groups = {"sanity","regression"})
	public void Clickonalphabate() throws InterruptedException, IOException
	{
		System.out.println("Enter the alphabet you want to check.");
		Scanner sc = new Scanner(System.in);
		String a =sc.next();
		op.clickonalphabate(a);
		
		
	}
	
	
	@AfterClass
	public void close()
	{
		driver.quit();
	}
	
}
