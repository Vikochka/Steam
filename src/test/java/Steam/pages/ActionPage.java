package Steam.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActionPage extends BasePage {
    private static By pageLocator = By.xpath("//h2[@class='pageheader'] ");

    private Label list = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));
    private Label block = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']"));

    public ActionPage() {
        super(pageLocator, "Browsing Action");
    }

    public void selectGameWithMaxDiscount() {
        block.isPresent();
        list.getElements();

        Collections.sort(list);
        for (int i = 1; i <= block.size(); i++) {
            String[] discount;
            discount = list.split("%");
                }
            }

        }
    }

