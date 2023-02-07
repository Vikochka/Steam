package steam.pageObject.pages;

import framework.elements.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static framework.PropertyReader.getProperty;

@Log4j2
public class ActionPage extends BaseSteamPage {

    private static String pageLocator = "//h2[@class='pageheader'] ";

    private static Label lblDiscount = new Label(By.xpath("//div[@class='discount_block  discount_block_inline']//div[@class='discount_pct']"));

    public ActionPage() {
        super(By.xpath(pageLocator), "Browsing Action");
    }

    public void selectGameWithMaxDiscount() {
        log.info("Starting to choose the game with the maximum discount");
        List<Integer> integers = new ArrayList<>();
        List<WebElement> discountList = lblDiscount.getElements();
        int convert = 0;
        int max;
        int indexOfMax = 0;
        for (int i = 0; i < discountList.size(); i++) {
            String strDiscount = discountList.get(i).getText();
            String[] discount;
            discount = strDiscount.split("%");

            for (int j = 0; j < discount.length; j++) {
                convert = Integer.parseInt(discount[j]);
                convert = convert - convert - convert;
            }
            integers.add(convert);
        }
        max = Collections.max(integers);
        log.info("Max discount of game = " + max);
        int count = Collections.frequency(integers, max);
        if (count != 1) {
            Random random = new Random();
            indexOfMax = random.nextInt(count);
            log.info("Games with the same maximum discount = " + count);
        } else {
            indexOfMax = integers.indexOf(Collections.max(integers));
        }
        discountList.get(indexOfMax).click();
        log.info(getProperty("log.select") + " game with max discount");
    }

    public static int getMaxDiscount() {
        List<Integer> integers = new ArrayList<>();
        List<WebElement> discountList = lblDiscount.getElements();
        int convert = 0;
        for (int i = 0; i < discountList.size(); i++) {
            String strDiscount = discountList.get(i).getText();
            String[] discount;
            discount = strDiscount.split("%");

            for (int j = 0; j < discount.length; j++) {
                convert = Integer.parseInt(discount[j]);
                convert = convert - convert - convert;
            }
            integers.add(convert);
        }
        int max = Collections.max(integers);
        return max;
    }
}