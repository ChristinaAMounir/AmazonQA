package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class scenario4 {
    public scenario4( WebDriver driver) {


        try {


            // Navigate to Amazon's homepage and perform a search
            driver.get("https://www.amazon.eg/-/en");
            Thread.sleep(3000);
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("Apple");
            searchBox.submit();

            // Wait for the search results to load using WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-component-type='s-search-result']")));

            // Find all product elements using a common CSS selector
            List<WebElement> products = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));

            // Check if there are any products displayed
            if (products.size() > 0) {
                // Generate a random index
                Random random = new Random();
                int randomIndex = random.nextInt(products.size());

                // Select the random product
                WebElement randomProduct = products.get(randomIndex);

                // Try clicking on the product's title
                try {
                    WebElement productTitle = randomProduct.findElement(By.cssSelector("h2 a"));
                    wait.until(ExpectedConditions.elementToBeClickable(productTitle));
                    productTitle.click();
                } catch (Exception e) {
                    // If the title is not clickable, try clicking on the product image
                    WebElement productImage = randomProduct.findElement(By.cssSelector("img"));
                    wait.until(ExpectedConditions.elementToBeClickable(productImage));
                    productImage.click();
                }

                // Wait for the product page to load
                Thread.sleep(3000);

                // Verify if the product is in stock
                WebElement stockStatus = driver.findElement(By.id("availability"));
                String stockText = stockStatus.getText();
                System.out.println("Stock Status: " + stockText);

                // Verify the product price
                WebElement priceWhole = driver.findElement(By.cssSelector(".a-price-whole"));
                WebElement priceFraction = driver.findElement(By.cssSelector(".a-price-fraction"));
                WebElement priceCurrency = driver.findElement(By.cssSelector(".a-price-symbol"));

                String priceText = priceCurrency.getText() + priceWhole.getText() + "." + priceFraction.getText();
                System.out.println("Price: " + priceText);


                // Verify the product rating
                WebElement rating = driver.findElement(By.id("acrCustomerReviewText"));
                String ratingText = rating.getText();
                System.out.println("Rating: " + ratingText);

                // Verify the shipping details
                WebElement shippingDetails = driver.findElement(By.id("mir-layout-DELIVERY_BLOCK-slot-PRIMARY_DELIVERY_MESSAGE_LARGE"));
                String shippingText = shippingDetails.getText();
                System.out.println("Shipping Details: " + shippingText);

                // Add the product to the cart
                WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
                wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
                addToCartButton.click();
                // Assert that the product is in stock before proceeding
                if (stockText.contains("In Stock") || stockText.contains("in stock")) {
                    System.out.println("Product is in stock and added to cart successfully.");
                } else {
                    System.out.println("Product is out of stock. Cannot proceed to add to cart.");
                }
            } else {
                System.out.println("No products found.");
            }
            handleWarrantyPopup(wait, driver);



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }

    }
    public static void handleWarrantyPopup(WebDriverWait wait,WebDriver driver) {
        // wait up to 10 seconds

        try {
            // Wait for the popup to be visible
            WebElement warrantyPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-desktop-sideSheet")));


            // Click the "No Thanks" button
            WebElement noThanksButton = driver.findElement(By.id("attachSiNoCoverage"));
            noThanksButton.click();

            // Optionally, you can add more code here to handle what happens after the popup is dismissed

        } catch (Exception e) {
            System.out.println("Warranty popup did not appear or something went wrong: " );
        }
    }
}
