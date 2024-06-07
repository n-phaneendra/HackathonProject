package worldclock1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Screenshot {

	public static void FullScreenshot(WebDriver driver, String name) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File ("./Screenshot/"+name+".png");
		FileUtils.copyFile(src, trg);

	}
	public static void ScreenshotElement(WebElement we) throws IOException
	{
		File src = we.getScreenshotAs(OutputType.FILE);
		File trg = new File ("./Screenshot/"+we.getText()+".png");
		FileUtils.copyFile(src, trg);
	}

}
