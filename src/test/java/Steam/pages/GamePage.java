package Steam.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static framework.PropertyReader.getProperty;


public class GamePage extends BasePage {
    private static By pageLocator = By.id("appHubAppName");
    private Button btnInstallSteam = new Button(By.xpath("//a[@class='header_installsteam_btn_content']"));

    private Label lblSteam = new Label(By.xpath("//div[@class='about_subtitle']"));

    public GamePage() {
        super(pageLocator, "Game page");
    }

    public void downloadSteam() {
        btnInstallSteam.click();
        lblSteam.isDisplayed();
        String url = getProperty("SetupSteamURL");
        String filePath = getProperty("filePath");
        FileOutputStream fileOutputStream = null;
        BufferedInputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new URL(url).openStream());
            fileOutputStream = new FileOutputStream(filePath);
            byte data[] = new byte[3000];
            int count;
            while ((count = inputStream.read(data, 0, 3000)) != -1) {
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