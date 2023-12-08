package devtools.cookie;

import database.DAO;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class DAOCookie {
    public static void takeCookieFromDB(DAO dao, WebDriver driver, String phoneNumber){
        String cookieString = dao.takeValue(phoneNumber);
        String[] oldCookies = cookieString.split("; ");
        for (String cookie : oldCookies) {
            String[] cookieParts = cookie.trim().split("=");
            String name = cookieParts[0];
            String value = cookieParts[1];
            Cookie c = new Cookie(name, value);
            driver.manage().addCookie(c);
        }
    }

    public static void putCokieInDB(DAO dao, WebDriver driver, String phoneNumber){
        Set<Cookie> newCookies = driver.manage().getCookies();
        StringBuilder newCookieString = new StringBuilder();
        for (Cookie cookie : newCookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            newCookieString.append(name).append("=").append(value).append("; ");
        }
        dao.putValue(phoneNumber, newCookieString.toString());
    }
}
