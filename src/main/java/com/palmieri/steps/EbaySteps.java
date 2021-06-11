package com.palmieri.steps;

import com.palmieri.ManagementDriver;
import com.palmieri.Utility;
import com.palmieri.models.EbayProduct;
import com.palmieri.models.SelectMenuEbay;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EbaySteps {

    private SelectMenuEbay selectMenuEbay;
    private WebElement webElement;
    private WebDriver driver = ManagementDriver.getChromeDriver();

    public void search(Properties prop,String q) {
        webElement = driver.findElement(By.name(prop.getProperty("name.input.search")));
        webElement.clear();
        webElement.sendKeys(q);
        webElement.sendKeys(Keys.ENTER);
    }

    public void closeBanner(Properties prop) {
        try{
            webElement = new WebDriverWait(driver, (long) 5)
                    .until(driver -> driver.findElement(By.id(prop.getProperty("id.banner.gdp"))));
            if(webElement.isDisplayed()) {
                driver.findElement(By.id(prop.getProperty("id.btn.gdp.accept"))).click();
                System.out.println("Banner Trovato e Chiuso");
            }
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Banner non trovato");
            Utility.Screenshot("closeBannerException");

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
            Utility.Screenshot("closeBannerFluentException");
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

    public void openCart(Properties prop){
        clickOnButtonByClassName(prop.getProperty("class.cart.btn"));
    }

    public ArrayList<EbayProduct> elementToProduct(Properties prop, List<WebElement> list) {
        ArrayList<EbayProduct> ebayProducts = new ArrayList<>();
        for (WebElement e : list) {
            ebayProducts.add(new EbayProduct(e.findElement(By.tagName("h3")).getText(),
                    e.findElement(By.className(prop.getProperty("class.result.subtitle"))).getText(),
                    e.findElement(By.className(prop.getProperty("class.result.price"))).getText(),
                    e.findElement(By.className(prop.getProperty("class.img.result"))).getAttribute("src")
            ));
        }
        return  ebayProducts;
    }

    public List<WebElement> getWebProducts(Properties prop) {
       return driver.findElement(By.xpath(prop.getProperty("xpath.div.result"))).findElements(By.className("s-item"));


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

    public void register(Properties prop, String email) throws InterruptedException{
        clickOnButtonByXpath(prop.getProperty("xpath.icon.a"));
        Thread.sleep(30000);
        clickOnButtonByXpath(prop.getProperty("xpath.sign.a"));
        clickOnButtonById(prop.getProperty("id.input.firstname")).sendKeys("Prancesco");
        clickOnButtonById(prop.getProperty("id.input.lastname")).sendKeys("Andonelli");
        clickOnButtonById(prop.getProperty("id.input.email")).sendKeys(email);
        clickOnButtonById(prop.getProperty("id.input.password")).sendKeys("Able1234.");
        clickOnButtonById(prop.getProperty("id.btn.submit"));
        driver.findElement(By.name(prop.getProperty("name.btn.replace")));
        Utility.Screenshot("Register");
    }

    public ArrayList<WebElement> getCartList(Properties prop, int i) {
        ArrayList<WebElement> cartElements = new ArrayList<>();
        cartElements.addAll(getWebProducts(prop).subList(0,i));
        return cartElements;
    }

    public void addToCart(Properties prop,String href, String handle) {
        driver.switchTo().window(handle);
        driver.get(href);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement aggiungi = driver.findElement(By.id(prop.getProperty("id.addcart.btn")));
        js.executeScript("arguments[0].scrollIntoView();", aggiungi);
        aggiungi.click();
        driver.close();
        driver.switchTo().window(handle);

    }

    public double getPrice(Properties prop) {
        for(WebElement e : driver.findElements(By.className("row"))) {
            if (e.findElement(By.className("val-col")).getAttribute("data-test-id").contains("ITEM_TOTAL"))
                return Double.valueOf(e.findElement(By.className("val-col")).getText().substring(1,6));
        }
        return 0;
    }

    public void modQuantity(Properties prop, int n) {
        WebElement e = driver.findElements(By.cssSelector("input[data-test-id=\"qty-textbox\"]")).get(n);
        e.clear();
        e.sendKeys("2" + Keys.ENTER);
    }

    public boolean forward(Properties prop, int n) {
        for(int i = 1; i < n; i++) {
            clickOnButtonByClassName(prop.getProperty("class.next.btn"));
            for(EbayProduct e : getProducts(prop)) e.print();
            Utility.Screenshot("forward"+i);
            if(i>1 && !driver.getCurrentUrl().contains("pgn=" + (i+1) )) return false;
        }
        return true;
    }

    public boolean backward (Properties prop, int n) {
        for(int i = n; i > 1; i--) {
            clickOnButtonByClassName(prop.getProperty("class.previous.btn"));
            //for(EbayProduct e : getProducts(prop)) e.print();
            //Utility.Screenshot("backward"+i);
            if(i>1 && !driver.getCurrentUrl().contains("pgn=" + (i - 1) )) return false;
        }
        return true;
    }

    public void clickOnButtonByClassName(String nameClass){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);


            WebElement webElement = wait.until(driver -> driver.findElement(By.className(nameClass)));
            if (webElement.isDisplayed()) {
                webElement.click();
            }
        } catch (TimeoutException e) {
            System.out.println("Banner non trovato");
            Utility.Screenshot("clickOnButtonByClassNameException");
        }
    }

    public WebElement clickOnButtonById(String id){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);


            WebElement webElement = wait.until(driver -> driver.findElement(By.id(id)));
            if (webElement.isDisplayed()) {
                webElement.click();
                return webElement;
            }
        } catch (TimeoutException e) {
            Utility.Screenshot("clickOnButtonByClassNameException");
        }
        return null;
    }

    public void removeItem(Properties prop, int n) {
      List<WebElement> cartItems = driver.findElements(By.className(prop.getProperty("class.item.cart")));
       for (WebElement e :cartItems.get(n).findElements(By.className("faux-link"))){
           if(e.getAttribute("data-test-id").equals(prop.getProperty("data-test-id"))) {
               e.click();
               break;
           }
       }
    }
    
    public void clickOnButtonByXpath(String xpath){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(9))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoring(NoSuchElementException.class);


            WebElement webElement = wait.until(driver -> driver.findElement(By.xpath(xpath)));
            if (webElement.isDisplayed()) {
                webElement.click();
            }
        } catch (TimeoutException e) {
            System.out.println("Pulsante non trovato");
            Utility.Screenshot("clickOnButtonByClassNameException");
        }
    }
}
