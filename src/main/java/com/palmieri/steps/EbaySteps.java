package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.models.EbayProduct;
import com.palmieri.models.SelectMenuEbay;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class EbaySteps {

    private SelectMenuEbay selectMenuEbay;
    private WebElement webElement;
    private WebDriver driver = ManagementDriver.getDriver();

    public void search(Properties prop,String q) {
        webElement = driver.findElement(By.name(prop.getProperty("name.input.search")));
        webElement.clear();
        webElement.sendKeys(q);
        webElement.sendKeys(Keys.ENTER);
    }
    public void closeBanner(Properties prop) {
        try{
            webElement = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id(prop.getProperty("id.banner.gdp"))));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.gdp.accept"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
        }
    }

    public void closeBannerFluent(Properties prop) {
        try{
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            if(wait.until(driver -> driver.findElement(By.id("id.banner.gdp")).isDisplayed())) {
                driver.findElement(By.id(prop.getProperty("id.btn.gdp.accept"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
        }
    }
    public List<WebElement> getTabs(Properties prop) {
        return driver.findElement(By.id(prop.getProperty("id.select.category"))).findElements(By.tagName("option"));
    }

    public SelectMenuEbay getTabsAsObject(String propKey) {
       HashMap<String, String> mapOptions = new HashMap<>();
        for(WebElement element : driver.findElement(By.id(propKey)).findElements(By.tagName("option"))) {
            mapOptions.put(element.getAttribute("value"),element.getText());
        }
        return new SelectMenuEbay(mapOptions,
                driver.findElement(By.id(propKey)).getAttribute("name"),
                driver.findElement(By.id(propKey)).getAttribute("class"),
                propKey
                );
    }

    public ArrayList<EbayProduct> getProducts(Properties prop) {
        ArrayList<EbayProduct> ebayProducts = new ArrayList<>();
        for (WebElement e : driver.findElement(By.xpath(prop.getProperty("xpath.div.result"))).findElements(By.className("s-item"))) {
            ebayProducts.add(new EbayProduct(e.findElement(By.tagName("h3")).getText(),
                            e.findElement(By.className(prop.getProperty("class.result.subtitle"))).getText(),
                            e.findElement(By.className(prop.getProperty("class.result.price"))).getText(),
                            e.findElement(By.className(prop.getProperty("class.img.result"))).getAttribute("src")
                            ));
        }
        return  ebayProducts;
    }


    public WebElement  selectCategory(Properties prop, String c) {
        driver.findElement(By.id(prop.getProperty("id.select.category"))).click();
        for(WebElement element : getTabs(prop)) {
            if(element.getText().toLowerCase().contains(c)){
                element.click();
                return element;
            }
        }
        return null;
    }
}
