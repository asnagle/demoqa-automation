package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class formsPage {

//	======= Using PageFactory of Selenium ======
	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "userEmail")
	WebElement userEmail;

//	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[3]/div[2]/div[1]/label")
//	@FindBy(css = "div.custom-radio:nth-child(1) > label:nth-child(2)")
//	WebElement genderMale;

	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[3]/div[2]/div[2]/label")
	WebElement genderFemale;

	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[3]/div[2]/div[3]/label")
	WebElement genderOther;

	@FindBy(id = "userNumber")
	WebElement mobileNumber;

	@FindBy(id = "dateOfBirthInput")
	WebElement datePicker;

	@FindBy(xpath = "/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div[4]/div[1]")
	WebElement dateofBirth;

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
		driver.findElement(By.id("subjectsInput")).clear();
		driver.findElement(By.id("subjectsInput")).sendKeys("IT");
		driver.findElement(By.xpath("//div[@id='subjectsContainer']/div/div")).click();
		driver.findElement(By.id("subjectsInput")).clear();
		driver.findElement(By.id("subjectsInput")).sendKeys("Software Development");

	}
	
	public void selectHobbies() {
		WebElement element = driver.findElement(By.id("hobbies-checkbox-1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
