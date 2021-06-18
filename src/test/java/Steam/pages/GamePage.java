package Steam.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;


public class GamePage extends BasePage {
    private static By pageLocator = By.id("appHubAppName");
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));

    public GamePage() {
        super(pageLocator, "Game page");
    }

    public void downloadSteam() {

    }
}
