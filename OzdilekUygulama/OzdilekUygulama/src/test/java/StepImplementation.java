import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest{

    Logger logger = LogManager.getLogger(StepImplementation.class);

    @Step("<time> saniye kadar bekle")
    public void waitForthird(int time) throws InterruptedException {
        Thread.sleep(time*1000);
    }

    @Step("id <id> li elemente tıkla")
    public void clickByid(String id){
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("xpath <xpath> li elemente tıkla")
    public void clickByxpath(String xpath){
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    @Step("id <id> li elementi bul ve <text> değerini yaz")
    public void sendkeysByid(String id,String text){
        appiumDriver.findElement(By.id(id)).sendKeys(text);
    }

    @Step("id <id> li elementi bul ve <text> ile kontrol et")
    public void textIdControl(String id,String text){
        Assert.assertTrue("Element text değerini içeriyor",appiumDriver.findElement(By.id(id)).getText().contains(text));
    }

    @Step("xpath <xpath> li elementi bul ve <text> ile kontrol et")
    public void textxpathControl(String xpath, String text){
        Assert.assertTrue("Element text değerini içeriyor", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
    }

    @Step("Sayfayı yukarı kaydır")
    public void swipeVertical(){
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width/2, edgeBorder);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();

        logger.info("Sayfayı kaydırma başarılı");
    }

    @Step("Ürünler <xpath> arasından rastgele bir ürünü seç ve tıkla")
    public void clickRandomeProduct(String xpath){
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
        logger.info("Rastgele bir ürün seçme işlemi başarılı");
    }

    @Step("Ürün için <xpath> rastgele bir beden seç ve tıkla")
    public void selectSizeProduct(String xpath){
        Random rndm = new Random();
        List<MobileElement> sizeProduct = appiumDriver.findElements(By.xpath(xpath));
        System.out.println(sizeProduct.size());
        int index = rndm.nextInt(sizeProduct.size());
        sizeProduct.get(index).click();
        logger.info("Rastgele beden seçme işlemi başarılı");
    }




}
