package com.nykaa.Nykaa.Copy;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

//import selenim.screenshots.ScreenShot;
//import org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.test.demo.sites.IframeTest;

public class Nykaa {
	
	int cnt =0;
	List<WebElement> dyn;
	WebElement search;
	WebDriverWait wait;
	WebDriver driver;
	static List<String> products;
	
	public Nykaa(){}
	
	public Nykaa(WebDriver driver) {this.driver = driver;}
	
	//public Nykaa()
	
	public static void fileCopy(File captured) throws IOException
	{
		String filename = java.time.LocalDateTime.now().toString().replace(':','-');
		
		Path dest = Paths.get("C:\\Users\\Michael\\JavaSelenium-workspace\\JSM\\ScreenShots\\"+filename+".png");
		
		Files.copy( Paths.get(captured.getPath()),dest);
		
	}
	
	public static void getScreenShot(WebDriver driver) throws IOException

	{
		File captured = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	String filename = java.time.LocalDateTime.now().toString().replace(':','-');
	
	Path dest = Paths.get("C:\\Users\\Michael\\JavaSelenium-workspace\\JSM\\ScreenShots\\"+filename+".png");
	
	Files.copy( Paths.get(captured.getPath()),dest);
	


	}

	protected static void robotScreenshot() throws AWTException, IOException, UnsupportedFlavorException
	{
		Robot k = new Robot();
	
		Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage tmp =  k.createScreenCapture(screenSize);
		String filename = java.time.LocalDateTime.now().toString().replace(':','-');
		String dest = "C:\\Users\\Michael\\JavaSelenium-workspace\\JSM\\ScreenShots\\"+filename+".png";
		ImageIO.write(tmp, "png",new File(dest));
		
		}
	
	
public WebDriver login(WebDriver driver) throws InterruptedException, IOException, AWTException, UnsupportedFlavorException
{
			
	
	
	driver.get("http://www.nykaa.com/");
	driver.manage().window().maximize();
	Thread.sleep(1000);
	//System.out.println(" start of execution ...");
	driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	driver.findElement(By.xpath("//button[text()='Sign in with Mobile / Email']")).click();
	Thread.sleep(1000*5);
	driver.findElement(By.cssSelector("input.input.text-center")).sendKeys("9600511201");
	getScreenShot(driver);
	//System.out.println("Phone no entered ...");
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.id("submitVerification")).click();
	//Thread.sleep(1000);
	
	
	WebElement submit = driver.findElement(By.xpath("//div/div/div/div/div/div/div/span"));
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	Pattern p = Pattern.compile("\\S*");
	wait.until(ExpectedConditions.textMatches(By.xpath("//div/div/div/div/div/div/div/span"), p));
	System.out.println(driver.findElement(By.xpath("//div/div/div/div/div/div/div/span")).getText());
	String submitMsg = submit.getText();
	try {
		wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	wait.until(ExpectedConditions.textToBePresentInElement(submit,"OTP sent successfully!"));
	System.out.println("OTP submitted");
	WebElement we = new WebDriverWait(driver,Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.big.fill.full")));
	System.out.println("Proceed click is ready");
	we.click();
	}
	catch(TimeoutException e)
	{
		driver.get("http://www.nykaa.com/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.css-1gzc5zn")).click();
		
		if(!driver.findElements(By.cssSelector("button.css-ejuz3m")).isEmpty())
		{
			driver.findElements(By.cssSelector("button.css-ejuz3m")).get(1).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input#identifierId.whsOnd.zHQkBf")).sendKeys("jeniferisworking@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Next']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='password'][@class='whsOnd zHQkBf']")).sendKeys("AeWmQL&=~3@T2t9");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Next']")).click();
		}
//				
	}
robotScreenshot();
getScreenShot(driver);	
//
//
//System.out.println("Last Line executed ..." );
//
Thread.sleep(1000);
getScreenShot(driver);	
robotScreenshot();
return (driver);

}
	
 protected List<String> getAvgCusRatingProducts(WebDriver driver,String rating) throws InterruptedException
{
	
	 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(rating))));
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath(rating))).click().perform();
	//driver.findElement(By.xpath(rating)).click();
	Thread.sleep(1000);
//	driver.findElement(By.xpath("//span[text()='4 stars & above']")) //"//span[text()='1 star & above']"

List<String> products = new ArrayList<String>();
List <WebElement> li = driver.findElements(By.cssSelector("div.css-xrzmfa"));// take all the sorted items 

//int size = driver.findElements(By.xpath("//div[@class='css-8u7lru']/a")).size();// total number of pages



for(WebElement temp:li)
	{

	products.add(temp.getText());	
	}



while(!driver.findElements(By.cssSelector("a.css-1zi560")).isEmpty())
{
	action.moveToElement(driver.findElements(By.cssSelector("a.css-1zi560")).get(0)).click().perform();;
	Thread.sleep(2000);

li = driver.findElements(By.cssSelector("div.css-xrzmfa"));
for(WebElement temp:li)
{
products.add(temp.getText());	
}
}

return products;
}
	
	protected void updateDetails(WebDriver driver) throws IOException, InterruptedException
	{
		driver.findElement(By.cssSelector("span.css-17ukzrr.euw1lbv4")).click();
		driver.findElement(By.xpath("//a[@href='/myProfile']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("span.css-i680yp")).click();
		Thread.sleep(1000);
		List<WebElement> inputs = driver.findElements(By.cssSelector("input.css-1lbqh0v"));
		inputs.get(1).click();
		Thread.sleep(1000);
		inputs = driver.findElements(By.cssSelector("input.css-1vr4lgq"));	
		inputs.get(0).sendKeys(Keys.chord(Keys.CONTROL,"A"));
		inputs.get(0).sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		getScreenShot(driver);	
		inputs.get(0).sendKeys("Jenifer");
		Thread.sleep(1000);
		getScreenShot(driver);
		
		WebElement mob = driver.findElement(By.cssSelector("input.css-1yl6qir"));
		if(mob.isEnabled())
		{
		mob.sendKeys(Keys.chord(Keys.CONTROL,"A"));
		mob.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		mob.sendKeys("9600511201");
		}
		inputs.get(1).sendKeys(Keys.chord(Keys.CONTROL,"A"));
		inputs.get(1).sendKeys(Keys.DELETE);
		inputs.get(1).clear();
		Thread.sleep(1500);
		getScreenShot(driver);
		inputs.get(1).sendKeys("jeniferisworking@gmail.com");
		
		driver.findElement(By.id("dob")).sendKeys("12121999");
		driver.findElement(By.cssSelector("button.css-15q5a8e")).click();
		Thread.sleep(1000);
		getScreenShot(driver);	
		 dyn = driver.findElements(By.cssSelector("button.css-ejuz3m"));
		if(dyn.size()!=0)
		{
		dyn.get(0).click();
		}

	}


public void automate(boolean isLogged) throws InterruptedException, IOException, AWTException, UnsupportedFlavorException
{
			if(!isLogged) {	
			driver = login(driver);//login initiated.
				
			}		//
			else
				{
				driver.get("http://www.nykaa.com/");
				driver.manage().window().maximize();
				Thread.sleep(1000);
				}	
				driver.findElement(By.cssSelector("a#category.css-1mavm7h")).click();
				Thread.sleep(1000);
				System.out.println("Categories clicked !");
				getScreenShot(driver);	
				Thread.sleep(2000);
				//driver.findElement(By.id("64787ac81b18375cd6ef9233")).click();
				
				WebElement next =driver.findElements(By.xpath ("//div[@class='css-8y7o41']/div[@class='css-1ojspo7']")).get(0);
				
				try
				{
				while (true)
				
				{
				if(!driver.findElements(By.xpath("//a[@href='https://www.nykaa.com/best-of-travel-friendly-appliances/c/32178?transaction_id=06c1da8cbede66bdf2f111891c779019']")).isEmpty())
				{
					search = driver.findElement(By.xpath("//a[@href='https://www.nykaa.com/best-of-travel-friendly-appliances/c/32178?transaction_id=06c1da8cbede66bdf2f111891c779019']"));
					break;
				}
				next.click(); //slide next arrow
				Thread.sleep(500);
				}
				//}
				
				catch(StaleElementReferenceException e)
				{
					Actions action = new Actions(driver);
					//action.moveToElement(driver.findElement(By.id("648ec0faf9c33578e6664dc1"))).build().perform();
					search = driver.findElements(By.cssSelector("[id='648ec0faf9c33578e6664dc1']>div")).get(3);
				}
				Actions action = new Actions(driver);
				action.moveToElement(search).perform();
				wait = new WebDriverWait(driver,Duration.ofSeconds(150));
				//WebElement getStyling = driver.findElement(By.xpath("//a[@href='https://www.nykaa.com/best-of-travel-friendly-appliances/c/32178?transaction_id=06c1da8cbede66bdf2f111891c779019']"));
				wait.until(ExpectedConditions.elementToBeClickable(search));
				action.moveToElement(search).click().perform();
			
				Thread.sleep(1000);
				//driver.findElement(By.xpath("//div[@id='64706a4a237c70b5e0c1b14f']//a//img")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//span[text()='Avg Customer Rating']")).click();
					products = getAvgCusRatingProducts(driver,"//span[text()='1 star & above']");
				
				System.out.println("1 Star & Above"+ products.size());
				for(String str : products)
				{
					System.out.println(str);
				}
				Thread.sleep(1000);
				//driver.findElement(By.xpath("//span[text()='Avg Customer Rating']")).click();
				products = getAvgCusRatingProducts(driver,"//span[text()='4 stars & above']");
				
				System.out.println("4 Stars & Above"+ products.size());
				for(String str : products)
				{
					System.out.println(str);
				}
				
				Thread.sleep(2000);
				wait = new WebDriverWait(driver,Duration.ofSeconds(200));
				
				driver.findElement(By.cssSelector("a.logo.css-1bk0o9p")).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='men']")))).click();
				//Thread.sleep(2000);
				List<String>windows = new ArrayList(driver.getWindowHandles());
				driver.switchTo().window(windows.get(1));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy,(1366, 643)");
				action.moveToElement(driver.findElement(By.xpath("//div[@class ='description'][text()='On combos']"))).click().perform();
				//wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class ='description'][text()='On combos']")))).click();
				//Thread.sleep(1000);
				
				
				//driver.executeScript("window.scrollBy(0,-1300)");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//span[text() = 'Price']")).click();
				
				products = getAvgCusRatingProducts(driver,"//input[@id ='checkbox_Rs. 0 - Rs. 499_0-499']");
				
				for(String str : products)
				{
					System.out.println(str);
				}
				driver.findElement(By.cssSelector("button.filter-clear-all")).click();
				
				products = getAvgCusRatingProducts(driver,"//input[@id ='checkbox_Rs. 500 - Rs. 999_500-999']");
				
				for(String str : products)
				{
					System.out.println(str);
				}
				driver.findElement(By.cssSelector("button.filter-clear-all")).click();
				
				products = getAvgCusRatingProducts(driver,"//input[@id ='checkbox_Rs. 1000 - Rs. 1999_1000-1999']");
				
				for(String str : products)
				{
					System.out.println(str);
				}
						//span[text()='4 stars & above'] button.filter-clear-all
				driver.findElement(By.cssSelector("button.filter-clear-all")).click();// clear the filter
				
				driver.findElement(By.xpath("//span[text()='Category']")).click(); // Select the category
				Thread.sleep(1000);
				driver.findElement(By.xpath("//span[text()=\"Men's Store\"]")).click();//Men's Store 
				
				driver.findElement(By.xpath("//span[text()='Skin Care']")).click();//selecting skin care 
				
				
				
				driver.findElement(By.xpath("//div/span[@class='title'][text()='Facewash']")).click();//Facewash selected
				
				//span[text()='Add to Bag']
				
				//dyn = driver.findElements(By.cssSelector("button.css-1oxhof2"));
				Thread.sleep(3000);
				driver.navigate().refresh();
				dyn = driver.findElements(By.xpath("//span[text()='Add to Bag']"));
				Thread.sleep(1000*5);
				Robot r = new Robot();
				Point p = driver.findElements(By.xpath("//span[text()='Add to Bag']")).get(1).getLocation();
				int x=p.getX();
				int y = p.getY();
				
				
				if(!dyn.isEmpty())
				{ 
					r.mouseMove(x, y);
					//action.moveToElement(dyn.get(1)).click().perform();
				}
				else
				{
					System.out.println("No product is avaiable");
				}
				
				//buy(driver);
				
				driver.findElement(By.cssSelector("div.css-xrzmfa"));
				
				// //a[@href='https://www.nykaa.com/sp/appliance-focus-travel/appliance-focus-travel?transaction_id=d2d753f269670cfe897d2e93fbf16f8c']
				
				
				
				
				

}
	
		public static void main(String[] args) throws InterruptedException, IOException, AWTException, UnsupportedFlavorException {
			// TODO Auto-generated method stub
//			System.out.println(" start executed ...");
			
			System.setProperty("webdriver.chrome.chromedriver",
					"C:\\Users\\Michael\\JavaSelenium-workspace\\JavaSelenium\\Drivers\\chromedriver.exe" );
			
		Nykaa driver = new Nykaa(new ChromeDriver());
				driver.automate(false);
				System.setProperty("webdriver.firefox.driver", "C:\\Users\\Michael\\JavaSelenium-workspace\\JavaSelenium\\Drivers\\geckodriver.exe");		
//			Nykaa driver = new Nykaa(new FirefoxDriver());
//			driver.automate(false);
			
			
			
					}
		
}

	

