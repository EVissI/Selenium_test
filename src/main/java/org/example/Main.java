package org.example;

import math.MathOperation;
import models.Coupon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
          String promo = "flamp";
          Coupon coupon = new Coupon();

          Map<String, Object> prefs = new HashMap<>();
          prefs.put("profile.managed_default_content_settings.images", 2);
          ChromeOptions options = new ChromeOptions();
          options.setExperimentalOption("prefs", prefs);//disable picture
          options.addArguments("--window-size=1920,1080", "--ignore-certificate-errors","--headless","--silent");
          WebDriver driver = new ChromeDriver(options);

          // Set the page load timeout
          driver.manage().timeouts().pageLoadTimeout(3700, TimeUnit.MILLISECONDS);

          try {
                driver.get("https://amournsk.ru/catalog/");
          }catch (org.openqa.selenium.TimeoutException timeoutException_1){
                try {
                      WebElement firstProduct = driver.findElement(By.className("card__btn"));
                      firstProduct.click();
                }catch (org.openqa.selenium.TimeoutException timeoutException_2){
                      try {
                            WebElement buyButton = driver.findElement(By.className("card__btn"));
                            buyButton.click();
                            Thread.sleep(1200);
                            driver.navigate().to("https://amournsk.ru//personal/cart/");
                      }catch (org.openqa.selenium.TimeoutException timeoutException_3){
                            WebElement enterPromClick = driver.findElement(By.className("promo_btn"));
                            enterPromClick.click();
                            WebElement enterPromoValue = driver.findElement(By.className("el-input__inner"));
                            enterPromoValue.sendKeys(promo);
                            WebElement updateBtnClick = driver.findElement(By.className("promo_update_btn"));
                            updateBtnClick.click();
                            TimeUnit.MILLISECONDS.sleep(1000);

                            WebElement promoTextWeb = driver.findElement(By.className("promo_text"));
                            String promoTextStr = promoTextWeb.getText();
                            String promoCheck = "\u041F\u0440\u043E\u043C\u043E\u043A\u043E\u0434 \u043D\u0435\u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0442\u0435\u043B\u044C\u043D\u044B\u0439"; //строка = Промокод недействительный
                            if (promoTextStr.equals(promoCheck)) {
                                  coupon.setStatus(false);
                                  coupon.setPromoName(promo);
                                  coupon.setPromoMassage(promoTextStr);
                            } else {
                                  WebElement oldPriceWeb = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[1]/div[1]/div/div[4]/div/div[1]"));
                                  String oldPriceStr = oldPriceWeb.getText().replaceAll("\\D", "");
                                  WebElement newPriceWeb = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[1]/div[1]/div/div[4]/div/div[2]"));
                                  String newPriceStr = newPriceWeb.getText().replaceAll("\\D", "");
                                  coupon.setStatus(true);
                                  coupon.setPromoMassage(promoTextStr);
                                  coupon.setPromoName(promo);
                                  coupon.setPromoValue(MathOperation.calculatePercentageChange(
                                          Double.parseDouble(oldPriceStr),
                                          Double.parseDouble(newPriceStr)
                                  ));
                            }
                      }
                }
          }finally {
                      System.out.print(coupon);
                      driver.quit();
          }
      }
}