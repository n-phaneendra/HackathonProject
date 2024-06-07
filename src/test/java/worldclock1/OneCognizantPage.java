package worldclock1;
 
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class OneCognizantPage 
{
 
	WebDriver driver;
	//CONSTRUCTOR
	OneCognizantPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//web elements
 
	@FindBy(xpath = "//div[@class=\"viewAllHotAppsBtn\"]") WebElement all_apps_loc;
	@FindBy(xpath = "//div[@class=\"aZHolder\"]") WebElement atoz_loc;
	@FindBy(xpath = "//div[@class=\"aZHolder\"]/div") List<WebElement> alphabat_apps;
	@FindBy(xpath = "//div[@class=\"appStoreAppName\"]") List<WebElement> apps;
	//action Methods
	//click on view all apps
	public void clickallapps() throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10000));
		mywait.until(ExpectedConditions.elementToBeClickable(all_apps_loc));
		Screenshot.ScreenshotElement(all_apps_loc);
		all_apps_loc.click();
	}
	//validate a-z
	public boolean validateAToZ() throws InterruptedException
	{
		Thread.sleep(5000);
		boolean status = atoz_loc.isDisplayed();
		return status;
	}
	//click on any alphabet
	public void clickonalphabate(String a) throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		for(WebElement alpha:alphabat_apps)
		{
			System.out.println(alpha.getText()+" is displayed.");
		}
		for(WebElement alpha:alphabat_apps)
		{
			if(alpha.getText().equalsIgnoreCase(a))
			{
				Screenshot.ScreenshotElement(alpha);
				Thread.sleep(3000);
				alpha.click();
				Thread.sleep(2000);
				
				for(WebElement app: apps)
				{
					System.out.println("Apps with this alphabate");
					System.out.println(app.getText());
				}
				Screenshot.FullScreenshot(driver, a+" page");
				Thread.sleep(3000);
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("window.scrollBy(0,500)");
				Screenshot.FullScreenshot(driver, a+" page2");
				Thread.sleep(1000);
				js.executeScript("window.scrollBy(0,500)");
				Screenshot.FullScreenshot(driver, a+" page3");
			}
		}
	}
}