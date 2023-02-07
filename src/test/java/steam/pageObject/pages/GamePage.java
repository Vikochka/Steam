package steam.pageObject.pages;

import framework.elements.Label;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class GamePage extends BaseSteamPage {

    private static String pageLocator = "appHubAppName";

    private Label lblGameDiscount = new Label(By.xpath("//div[@class='game_area_purchase_game']//div[@class='discount_block game_purchase_discount']//div[@class='discount_pct']"));

    public GamePage() {
        super(By.id(pageLocator), "Game page");
    }

    public boolean checkCurrentGame() {
        log.info("Check current game");

        int max = ActionPage.getMaxDiscount();
        String text = lblGameDiscount.getText();
        String[] discount = text.split("%");
        for (int i = 0; i < discount.length; i++) {
            int convert = Integer.parseInt(discount[i]);
            convert = convert - convert - convert;
            if (convert == max) {
                log.info("Discounts are equal");
                return true;
            } else {
                log.info("Discounts are not equal");
                return false;
            }
        }
        return Boolean.parseBoolean(null);
    }
}