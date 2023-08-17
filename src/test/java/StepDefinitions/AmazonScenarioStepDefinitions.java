package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class AmazonScenarioStepDefinitions {
    WebDriver driver;

    @Given("the user launches the browser")
    public void launchBrowser() {
        // Set the path to the chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\\\Software\\\\chromedriver_win32\\\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }

    @When("the user searches for {string} on Google")
    public void searchOnGoogle(String keyword) {
        driver.get("http://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(keyword);
        searchBox.submit();
        // Add appropriate waiting logic here
    }

    @Then("the user should see search results")
    public void validateSearchResults() {
        List<WebElement> searchResults = driver.findElements(By.cssSelector(".tF2Cxc"));
        // Add validation logic for search results
    }

    @When("the user clicks on the link to Amazon India")
    public void clickAmazonLink() {
        WebElement amazonLink = driver.findElement(By.partialLinkText("Shop online at Amazon India "));
        amazonLink.click();
        // Add waiting logic for page load
    }    

    @When("the user searches for {string}")
    public void searchOnAmazon(String keyword) {
        WebElement searchBoxAmazon = driver.findElement(By.id("twotabsearchtextbox"));
        searchBoxAmazon.sendKeys(keyword);
        searchBoxAmazon.submit();
        // Add appropriate waiting logic here
    }

    @When("the user applies the price range filter Rs {int} to {int}")
    public void applyPriceRangeFilter(int lowRange, int highRange) {
        WebElement lowPriceElement = driver.findElement(By.id("low-price"));
        lowPriceElement.sendKeys(String.valueOf(lowRange));
        WebElement highPriceElement = driver.findElement(By.id("high-price"));
        highPriceElement.sendKeys(String.valueOf(highRange));
        
        WebElement filterButton = driver.findElement(By.xpath("//input[@class='a-button-input' and @type='submit' and @aria-labelledby='a-autoid-1-announce']"));
        filterButton.click();
        // Add waiting logic for filter to apply
    }

    @Then("the user should see search results within the price range")
    public void validatePriceRangeResults() {
        List<WebElement> searchResults = driver.findElements(By.cssSelector("div[data-asin]"));
        // Add validation logic for price range
    }

    @When("the user validates products on the first {int} pages")
    public void validateProductsOnPages(int pageCount) {
        for (int pageNum = 1; pageNum <= pageCount; pageNum++) {
            // Implement validation logic for each page
        }
    }

    @When("the user adds the first product with 5 out of 5 rating to the wishlist")
    public void addToWishlist() {
    	WebElement starsElement1 = driver.findElement(By.cssSelector("span.a-icon-alt"));
        String starsText1 = starsElement1.getText();
        List<WebElement> itemElements1 = driver.findElements(By.cssSelector("div[data-asin='B09BD4C11N'][data-index='19'][data-uuid='996da17b-058f-41e1-8bf7-a5b3793df009']"));
    
        for (WebElement item1 : itemElements1) {
        String priceText1 = item1.findElement(By.cssSelector("span.a-price span.a-offscreen")).getText();
        String priceString = priceText1.replaceAll("[^0-9]", "");  // Remove non-numeric characters
        int price = Integer.parseInt(priceString);

        if (price >= 30000 && price <= 50000 && starsText1.equals("5.0 out of 5 stars") ) {
        	item1.click();
            System.out.println("Item price is within the range: " + price);
        } else {
            System.out.println("Item price is NOT within the range: " + price);       
        }
        }
    }
     
    @Then("the product should be added to the wishlist")
    public void validateWishlistAddition() {
    	WebElement star2 = driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(star2).click().perform();
        
     // Switch to the new window
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[1]);

        // Wait for the "Add to Cart" button to be clickable
        By addToCartButtonLocator = By.xpath("//input[@type='submit' and @value='Add to Cart']");       
        
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonLocator));
        addToCartButton.click();
    }

    @Then("the user should see the {string} message")
    public void validateAddedToCartMessage(String message) {    
        
        String[] handles2 = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles2[1]);
        
        String elementXPath = "//span[@class='a-size-medium-plus a-color-base sw-atc-text a-text-bold' and contains(text(), 'Added to Cart')]";
    	 WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
         WebElement addedToCartElement = wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPath)));

         // Check if the element is displayed
         if (addedToCartElement.isDisplayed()) {
             System.out.println("Added to Cart message is displayed.");
         } else {
             System.out.println("Added to Cart message is not displayed.");
         }
    }

    @When("the user closes the browser")
    public void closeBrowser() {
        driver.quit();
    }
}
