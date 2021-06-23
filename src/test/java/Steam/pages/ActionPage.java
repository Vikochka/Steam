package Steam.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.*;

public class ActionPage extends BasePage {
    private static By pageLocator = By.xpath("//h2[@class='pageheader'] ");

    private Label pageAgeLocator = new Label(By.xpath("//div[contains(text(),'Please enter your birth date to continue:')]"));
    private Label lblDiscount = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));
    private Label blockGame = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']"));

    public ActionPage() {
        super(pageLocator, "Browsing Action");
    }

    public void selectGameWithMaxDiscount() {

        List<WebElement> discountList = lblDiscount.getElements();
        lblDiscount.getText();

        for (int i = 1; i < discountList.size(); i++) {
            String[] discount;
            discount = lblDiscount.split("%");

            for (int j = 1; j < discount.length; j++) {
                System.out.println(discount[j]);
                // int convert = Integer.parseInt(discount[0]);
                //      System.out.println(convert);
                //  Collections.sort(list);
            }
        }
    }


    public GamePage checkAge(String enterAge) {
        try {
            pageAgeLocator.waitForIsElementPresent();
            AgePage agePage = new AgePage();
            agePage.ageCheck(enterAge);
        } catch (NullPointerException e) {
            return new GamePage();
        }
        return new GamePage();
    }
}