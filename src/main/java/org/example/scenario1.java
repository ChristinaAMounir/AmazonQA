package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class scenario1 {
    public scenario1(WebDriver driver) {


        try {
            // Navigate to Amazon's homepage
            driver.get("https://www.amazon.com/");
            Thread.sleep(20000);
            // Navigate through categories: Smart Home -> Home Entertainment -> Smart Televisions
            driver.findElement(By.linkText("All")).click(); // Open the categories menu
            Thread.sleep(3000);
            driver.findElement(By.linkText("Smart Home")).click(); // Click on Smart Home category
            Thread.sleep(3000);
            driver.findElement(By.linkText("Home Entertainment")).click(); // Click on Home Entertainment sub-category

            // Assuming the link for Smart Televisions is correctly identified
            WebElement smartTelevisionsLink = driver.findElement(By.xpath("//img[@alt='Smart Televisions']"));
            smartTelevisionsLink.click(); // Click on the "Smart Televisions" image

            // Get the total number of results from the span element
            WebElement resultsText = driver.findElement(By.cssSelector("span.a-size-base.a-color-base.a-text-normal"));
            String totalResultsText = resultsText.getText().split(" ")[2].replace(",", ""); // Extract the total number
            int totalResults = Integer.parseInt(totalResultsText);

            // Get the number of displayed products
            List<WebElement> products = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
            int displayedProductsCount = products.size();

            // Verify that the total number of results matches the displayed number of products
            if (totalResults == displayedProductsCount) {
                System.out.println("Test Passed: The total number of results matches the displayed number of products.");
            } else {
                System.out.println("Test Failed: The total number of results does not match the displayed number of products.");
                System.out.println("Total results: " + totalResults);
                System.out.println("Displayed products: " + displayedProductsCount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
