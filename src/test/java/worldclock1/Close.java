package worldclock1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Close {

		public static void Popup(WebDriver driver)
		{
			try 
			{
				WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
				mywait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title=\"Close\"]"))).click();
				
			}
			catch(Exception e) 
			{
			
			}
		}
	}
