package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.closeAds;
import utils.waitForElement;
//import org.testng.Assert;

public class formsPage {

//	======= Using PageFactory of Selenium ======
	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")
	WebElement genderMale;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[2]/label")
	WebElement genderFemale;

	@FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[3]/label")
	WebElement genderOther;

	@FindBy(id = "userNumber")
	WebElement mobileNumber;

	@FindBy(id = "dateOfBirthInput")
	WebElement datePicker;

	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[1]")
	WebElement dateofBirth;

	@FindBy(id = "subjectsInput")
	WebElement subject;

	@FindBy(xpath = "//div[@id='city']/div/div/div")
	WebElement city;

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	

	public formsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void accessForms() {
		homePage homePage = new homePage(driver);
		homePage.clickFormCard();
		homePage.clickPracticeForm();
	}

	public void entFirstName() {
		firstName.clear();
		firstName.sendKeys("Johnny");
	}

	public void entLastName() {
		lastName.clear();
		lastName.sendKeys("Bravo");
	}

	public void entEmail() {
		userEmail.clear();
		userEmail.sendKeys("JBravo@demoqa.com");
	}

	public void selectGender() {
		WebElement element = driver.findElement(By.id("gender-radio-1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void entmobileNo() {
		mobileNumber.clear();
		mobileNumber.sendKeys("1234456677");
	}

	public void entDob() {

		WebElement dobInput = driver.findElement(By.id("dateOfBirthInput"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", dobInput);
		driver.findElement(By.cssSelector("select.react-datepicker__month-select")).click();
		new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("September");
		driver.findElement(By.xpath("//div[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div/div[2]/div[2]/select"))
				.click();
		new Select(driver.findElement(
				By.xpath("//div[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div/div[2]/div[2]/select")))
				.selectByVisibleText("1975");
		WebElement dob = driver.findElement(
				By.cssSelector("div.react-datepicker__day.react-datepicker__day--021.react-datepicker__day--weekend"));
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", dob);

	}

	public void enterSubject() {

		subject.sendKeys("E");
		List<WebElement> courses = driver.findElements(By.cssSelector(".subjects-auto-complete__menu.css-26l3qy-menu"));
		for (WebElement crs : courses) {
			System.out.println(crs.getText());
			driver.findElement(By.cssSelector("#react-select-2-option-2")).click();
			break;
		}

	}

	public void selectHobbies() {

		WebElement element = driver.findElement(By.id("hobbies-checkbox-1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void uploadPicture() {

		String srcFile = System.getProperty("user.dir") + "/TestData/1photo.jpg";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#uploadPicture")));
		fileInput.sendKeys(srcFile);

	}

	public void enterAddress() {

		driver.findElement(By.id("currentAddress")).clear();
		driver.findElement(By.id("currentAddress"))
				.sendKeys("511 Grant 481, Prattsville\n Arkansas 72129\n United States");

	}

	public void selectState() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

//		 1. Scroll into view first
		WebElement stateContainer = driver.findElement(By.id("state"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateContainer);

//		 2. Click to open dropdown
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#state .css-1wa3eu0-placeholder")));
		dropdown.click();
		List<WebElement> options = driver.findElements(By.xpath("//div[contains(@id,'react-select-3-option-')]"));
		for (WebElement el : options) {
		    System.out.println(el.getText());
			 
		}
//		3. Select “City”
		WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='react-select-3-option-1']")));
		stateOption.click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#city .css-1wa3eu0-placeholder"), "Select City"));

	}

//	=========== Select City In-Progress ==============
	public void selectCity() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.findElement(By.id("city")).click();
		
//		2. Click to open dropdown
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#city .css-1wa3eu0-placeholder"), "Select City"));
		WebElement cityContainer = driver.findElement(By.cssSelector("div[class=' css-1wa3eu0-placeholder']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityContainer);
	
		List<WebElement> options = driver.findElements(By.xpath("//div[contains(@id,'react-select-4-option-')]"));
		for (WebElement cities : options) {
		    System.out.println(cities.getText());
			 if (cities.getText().equalsIgnoreCase("Agra")) {
				 System.out.println("You selected following City: " + cities.getText());
				 cities.click();
				 break;
			 }
		}
	}

	public void submitButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement btn = driver.findElement(By.xpath("//button[@id='submit']"));
		System.out.println("Button text: " + btn.getText());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('#submit').click();");
	}
	
}
