package steam.pageObject.pages;

import framework.elements.Label;
import org.openqa.selenium.By;

import static framework.PropertyReader.getProperty;

public class GamePage extends BaseSteamPage {

    private static String pageLocator = "appHubAppName";

    private Label lblGameDiscount = new Label(By.xpath("//div[@class='game_area_purchase_game']//div[@class='discount_block game_purchase_discount']//div[@class='discount_pct']"));

    public GamePage() {
        super(By.id(pageLocator), "Game page");
    }

    public boolean checkCurrentGame() {
        System.out.println("Check current game");

        int max = ActionPage.getMaxDiscount();
        String text = lblGameDiscount.getText();
        String[] discount = text.split("%");
        for (int i = 0; i < discount.length; i++) {
            int convert = Integer.parseInt(discount[i]);
            convert = convert - convert - convert;
            if (convert == max) {
                return true;
            } else {
                return false;
            }
        }
        return Boolean.parseBoolean(null);
    }
}