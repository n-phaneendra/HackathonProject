package worldclock1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Becognizantpage 
{
	WebDriver driver;
	//CONSTRUCTOR
	Becognizantpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElements storing
	@FindBy(id = "meInitialsButton")WebElement user_loc;
	@FindBy(id="mectrl_currentAccount_primary")WebElement user_name_loc;
	@FindBy(id="mectrl_currentAccount_secondary")WebElement user_email;
	@FindBy(xpath = "//*[@id=\\\"spPageCanvasContent\\\"]/div/div/div/div/div/div/div[5]/div")WebElement WorldClock_loc;
	@FindBy(xpath = "//div[@class=\"n_b_816e1fa6\"]/div[2]") WebElement Date_time_loc;
	@FindBy(xpath = "//div[@class=\"m_b_816e1fa6\"]/div/span[1]") List<WebElement> time_digit_loc;
	@FindBy(xpath = "//div[@class=\"m_b_816e1fa6\"]/div/span[2]") List<WebElement> am_pm_loc;
	@FindBy(xpath = "//div[@data-automation-id=\"clock-card-location\"]") List<WebElement> timezone_loc;
	@FindBy(xpath = "//div[@data-automation-id=\"clock-card-time-offset\"]") List<WebElement> timeZon_dif_loc;
	@FindBy(xpath = "//*[@id=\"vpc_WebPart.CustomTextWebPart.external.9ef2fab6-9f8b-43e8-9e82-c041c1b496e0\"]") WebElement one_cog_div_loc;
	@FindBy(xpath = "//a[@href=\"https://onecognizant.cognizant.com/Home\"]") WebElement onecog_loc;
	
	//ACTION METHODS
	//user_deatils
	public String user() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"O365_MainLink_Settings\"]/div/span")));
		Thread.sleep(1000);
		WebElement userIcon = driver.findElement(By.id("meInitialsButton"));
		Actions actions = new Actions(driver);
		actions.moveToElement(userIcon).build().perform();
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", userIcon);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mectrl_currentAccount_primary")));
		String name  = user_name_loc.getText().toString();
		String mail = user_email.getText().toString();
		String details = name+"\n"+mail;
		return details;
	}
	//validate worldclock section
	public boolean world()
	{
		WebElement worlddiv=driver.findElement(By.xpath("//*[@id=\"spPageCanvasContent\"]/div/div/div/div/div/div/div[5]/div"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",worlddiv);
		Boolean status = worlddiv.isDisplayed();
		return status;
	}
	//validate current week 
	public boolean weekdayvalidate()
	{
		
		String date_time = Date_time_loc.getText();
		String[] dates = date_time.split(",");
		String week = dates[0].toLowerCase();
		String Weekday = LocalDateTime.now().getDayOfWeek().toString().toLowerCase();
		System.out.println("WebPage day :"+week+"\nAcc day :"+Weekday);
		boolean status = week.equals(Weekday);
		return status;
	}
	
	//validate current date
	public boolean datevalidate()
	{
		String date_time = Date_time_loc.getText();
		String[] full_date = date_time.split(",");
		String date = full_date[1].trim();
		String[] dates = date.split("/");
		int acc_month = Integer.parseInt(dates[0]);
		int acc_day = Integer.parseInt(dates[1]);
		int acc_year = Integer.parseInt(dates[2]);
		int exp_month = LocalDateTime.now().getMonthValue();
		int exp_day = LocalDateTime.now().getDayOfMonth();
		int exp_year = LocalDateTime.now().getYear();
		System.out.println("Acctual date :"+acc_day+"/"+acc_month+"/"+acc_year);
		System.out.println("System date :"+exp_day+"/"+exp_month+"/"+exp_year);
		boolean status = false; // Initialize status to false

	    if (acc_month == exp_month) 
	    {
	        if (acc_day == exp_day) 
	        {
	            if (acc_year == exp_year) 
	            {
	                status = true; // All conditions met, set status to true
	            }
	        }
	    }

	    return status;
	}
	
	//validate bangalore timezone
	public boolean bangtimezonevalidate()
	{
		boolean status  = false;
		String bang = timezone_loc.get(0).getText().toString();
		System.out.println("Bangalore timezone displayed in website: "+bang);
		if(bang.equalsIgnoreCase("Bangalore, India (IST)"))
		{
			status = true;
		}
		return status;
		
	}
	
	//validate bangalore time
	public boolean bengtimevalidates()
	{
		String beng_time = time_digit_loc.get(0).getText().toString();
		String[] times = beng_time.split(":");
		int acc_hour = Integer.parseInt(times[0]);
		int acc_min = Integer.parseInt(times[1]);
		System.out.println("Time showed in webpage : "+acc_hour+":"+acc_min);
		String bang_ampm = am_pm_loc.get(0).getText().toString();
		int exp_hour = LocalDateTime.now().getHour();
		int exp_min = LocalDateTime.now().getMinute();
		if(bang_ampm.equalsIgnoreCase("pm"))
		{
			acc_hour = acc_hour+12;
		}
		System.out.println("Local Time : "+exp_hour+":"+exp_min);
		boolean status = false;
		if(acc_hour==exp_hour)
		{
			if(acc_min==exp_min)
			{
				status =true;
			}
		}
		return status;
		
	}
	
	
	//validate london and newyorktimezone
	public boolean londontimezonevalidate()
	{
		boolean status  = false;
		String bang = timezone_loc.get(1).getText().toString();
		if(bang.equalsIgnoreCase("London, UK (BST)"))
		{
			status = true;
		}
		return status;
	}
	//validate london time 
	public boolean londontimevalidate()
	{
		boolean status = false;
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/London"));

        // Get the current time
        Date currentTime = new Date();

        // Format the time using SimpleDateFormat
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");
        String formattedTime = timeFormat.format(currentTime);
        
        String acc_time = time_digit_loc.get(1).getText().toString();
        if(acc_time.equals(formattedTime))
        {
        	status = true;
        }

        System.out.println("Current time in Europe/London: " + formattedTime);

		System.out.println("London System time:"+acc_time);
		
		return status;
	}
	
	//validate london timezone difference
	public boolean londonTimezonedifvalidate()
	{
		boolean status = false;
		TimeZone bangloreTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
		TimeZone LondonTimeZone = TimeZone.getTimeZone("Europe/London");
		int hoursDifference = ((bangloreTimeZone.getRawOffset()-LondonTimeZone.getRawOffset()) / (60 * 60 * 1000))-1;
		int minutesDifference = (bangloreTimeZone.getRawOffset()-LondonTimeZone.getRawOffset()) / (60 * 1000) % 60;
		String lonbanggap = hoursDifference + "h " + minutesDifference + "m "+"behind";
		String acc_gap = timeZon_dif_loc.get(1).getText().toString();
		System.out.println("TimeZone diff shown in webpage : "+acc_gap);
		System.out.println("TimeZone diff Actual : "+lonbanggap);
		if(acc_gap.equalsIgnoreCase(lonbanggap))
		{
			status = true;
		}
		
		return status;
	}
	
	//validate newyork timezone
	public boolean NYtimezonevalidate()
	{
		boolean status  = false;
		String bang = timezone_loc.get(2).getText().toString();
		if(bang.equalsIgnoreCase("New York, NY (EST)"))
		{
			status = true;
		}
		return status;
	}
	//validate newYork time
	public boolean NYtimevalidate()
	{
		boolean status = false;
		TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));

        // Get the current time
        Date currentTime = new Date();

        // Format the time using SimpleDateFormat
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm");
        String formattedTime = timeFormat.format(currentTime);
        
        String acc_time = time_digit_loc.get(2).getText().toString();
        if(acc_time.equals(formattedTime))
        {
        	status = true;
        }

        System.out.println("Current time in America/New_York: " + formattedTime);

		System.out.println("Current time of America/New_York As shown in webpage : "+acc_time);
		
		return status;
	}
	
	//validate newyork time diff
	public boolean NYTimezonedifvalidate()
	{
		boolean status = false;
		TimeZone bangloreTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
		TimeZone NYTimeZone = TimeZone.getTimeZone("America/New_York");
		int hoursDifference = ((bangloreTimeZone.getRawOffset()-NYTimeZone.getRawOffset()) / (60 * 60 * 1000))-1;
		int minutesDifference = (bangloreTimeZone.getRawOffset()-NYTimeZone.getRawOffset()) / (60 * 1000) % 60;
		String nybanggap = hoursDifference + "h " + minutesDifference + "m "+"behind";
		System.out.println("NewYork timezone diff acctual: "+nybanggap);
		String acc_gap = timeZon_dif_loc.get(2).getText().toString();
		System.out.println("NewYork timezone diff shown in the webpage: "+acc_gap);
		
		if(acc_gap.equalsIgnoreCase(nybanggap))
		{
			status = true;
		}

		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		
		return status;
	}
	
	//click on one_cognizant
	public void clickonecog()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",one_cog_div_loc);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='OneCognizant']")));
		driver.findElement(By.xpath("//a[@title='OneCognizant']")).click();
	}
	
	//switch the driver into onecognizant window
	
	
	
	
}
