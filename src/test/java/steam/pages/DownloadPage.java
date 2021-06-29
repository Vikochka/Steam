package steam.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static framework.PropertyReader.getProperty;
import static org.testng.Assert.assertTrue;

public class DownloadPage extends BaseSteamPage {

    int filebyte = 3000;

    private static String pageLocator = "//div[@class='steam_logo']";

    private Label lblSteam = new Label(By.xpath("//div[@class='about_subtitle']"));
    private Button btnInstallSteam = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));

    public DownloadPage() {
        super(By.xpath(pageLocator), "DownloadPage");
    }


    public void clickOnInstallSteam() {
        getHeader().clickOnInstallSteam();
        lblSteam.isDisplayed();
    }
//
//    public void downloadSteam() {
//        String url = getProperty("SetupSteamURL");
//        String filePath = getProperty("filePath");
//        FileOutputStream fileOutputStream = null;
//        BufferedInputStream inputStream = null;
//        try {
//            inputStream = new BufferedInputStream(new URL(url).openStream());
//            fileOutputStream = new FileOutputStream(filePath);
//            byte data[] = new byte[filebyte];
//            int count;
//            while ((count = inputStream.read(data, 0, filebyte)) != -1) {
//                fileOutputStream.write(data, 0, count);
//                fileOutputStream.flush();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public void downloadSteam() throws InterruptedException {
        btnInstallSteam.click();
        Thread.sleep(10000);
        String filePath = getProperty("filePath");
        File folder = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\downloads");
        //список файлов в этой папке
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File file = null;

        for (File fileItem : listOfFiles) {
            if (fileItem.isFile()) {
                String fileName = fileItem.getName();
                System.out.println("File " + fileItem.getName());
                if (fileName.matches(fileName)) {
                    file = new File(fileName);
                    found = true;
                }
            }
        }
        assertTrue(found, "Загруженный документ не найден");
        file.delete();
    }

}
