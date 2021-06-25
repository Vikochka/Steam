package Steam.pages;

import framework.BasePage;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static framework.PropertyReader.getProperty;


public class GamePage extends BasePage {
    HeaderPage headerPage;
    int filebyte = 3000;

    private static By pageLocator = By.id("appHubAppName");
    private Label lblSteam = new Label(By.xpath("//div[@class='about_subtitle']"));
    private Label lblGameDiscount = new Label(By.xpath("//div[@class='game_area_purchase_game']//div[@class='discount_block game_purchase_discount']//div[@class='discount_pct']"));

    public GamePage() {
        super(pageLocator, "Game page");
        headerPage = new HeaderPage();
    }

    public void clickOnInstallSteam() {
        headerPage.clickOnInstallSteam();
        lblSteam.isDisplayed();
    }

    public boolean checkCurrentGame() {
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

    public void downloadSteam() {
        String url = getProperty("SetupSteamURL");
        String filePath = getProperty("filePath");
        FileOutputStream fileOutputStream = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new URL(url).openStream());
            fileOutputStream = new FileOutputStream(filePath);
            byte data[] = new byte[filebyte];
            int count;
            while ((count = inputStream.read(data, 0, filebyte)) != -1) {
                fileOutputStream.write(data, 0, count);
                fileOutputStream.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}