package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
//	@FindBy(css = "#gender-radio-1")
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

//	@FindBy(xpath = "//input[@id='subjectsInput']")
	@FindBy(id = "subjectsInput")
	WebElement subject;

//	@FindBy(xpath = "//div[@id='state']/div/div")
//	@FindBy(xpath = "//*[@id=\\\"state\\\"]/div")
//	WebElement state;

	@FindBy(xpath = "//div[@id='city']/div/div/div")
	WebElement city;

//	@FindBy(xpath = "//button[@id='submit']")
//	WebElement submitBtn;

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
//		=====Following is working code commented for testing===
		WebElement element = driver.findElement(By.id("gender-radio-1"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(genderFemale));
//		element.click();
//		genderFemale.click();
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

//		WebElement target = driver.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]/div[1]"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);
//		driver.findElement(By.xpath("//div[@id='subjectsContainer']/div/div")).click();
//		driver.findElement(By.xpath("//div[@class='subjects-auto-complete__value-container subjects-auto-complete__value-container--is-multi css-1hwfws3']")).sendKeys("C");
//		driver.findElement(By.id("subjectsContainer")).sendKeys("E");
		subject.sendKeys("E");
		List<WebElement> courses = driver.findElements(By.cssSelector(".subjects-auto-complete__menu.css-26l3qy-menu"));
		for(WebElement crs:courses) {
//			String option = crs.getText();
			System.out.println(crs.getText());
//			System.out.println(option);
			driver.findElement(By.cssSelector("#react-select-2-option-2")).click();
			break;
		}
		
		
//		driver.findElement(By.cssSelector("#react-select-2-option-2")).click();
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
		driver.findElement(By.id("currentAddress")).sendKeys("1, abcdd\nsdfslkj\nfsjdfper erwruwor\nsdfhskfsdf");

	}

	public void selectState() {

		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forms'])[1]/following::span[1]"))
				.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'css-yk16xz-control')]")));
		element.click();
//		driver.findElement(By.xpath("//div[contains(@class,'css-yk16xz-control')]")).click();
		List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class,'-option')]"));
		for (WebElement option : options) {
		    System.out.println("Option: " + option.getText());
		    option.click(); // Click or select as needed
		}

		
//		for (WebElement ls:listStates) {
//			System.out.println(ls.getText());
//		}
		}
	
	
	public void selectCity() {

		WebElement cityOption = driver.findElement(By.xpath("//*[@id=\"city\"]/div/div[1]"));
		WebElement citySelected = cityOption.findElement(By.xpath("//*[@id=\"city\"]/div/div[1]/div[1]"));
		citySelected.click();

	}

	public void submitButton() {

//		submitBtn.click();
		WebElement element = driver.findElement(By.xpath("//button[@id='submit']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		
	}
}
