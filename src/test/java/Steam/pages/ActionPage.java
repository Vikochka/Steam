package Steam.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Collections;

public class ActionPage extends BasePage {
    private static By pageLocator = By.xpath("//h2[@class='pageheader'] ");

    private Label pageAgeLocator = new Label(By.xpath("//div[contains(text(),'Please enter your birth date to continue:')]"));
    private Label listDiscount = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));
    private Label blockGame = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']"));

    public ActionPage() {
        super(pageLocator, "Browsing Action");
    }

    public void selectGameWithMaxDiscount(String enterAge) {
        blockGame.waitForIsElementPresent();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= blockGame.size(); i++) {
//            String[] discount;
//            discount = listDiscount.split("%");
//            int count = Integer.parseInt(discount[blockGame.size()]);
            arrayList.add(listDiscount.size());
            Collections.sort(arrayList);
            Collections.max(arrayList);
            listDiscount.click();
        }
        if (pageAgeLocator.waitForIsElementPresent()) {
            AgePage agePage = new AgePage();
            agePage.ageCheck(enterAge);
        }else {

        }


    }
}
