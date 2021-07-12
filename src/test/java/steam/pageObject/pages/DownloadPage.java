package steam.pageObject.pages;

import framework.elements.Button;
import org.openqa.selenium.By;

import java.io.File;

import static framework.PropertyReader.getProperty;
import static org.testng.Assert.assertTrue;

public class DownloadPage extends BaseSteamPage {
    private static String pageLocator = "//div[@class='steam_logo']";

    private Button btnInstallSteam = new Button(By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']"));

    public DownloadPage() {
        super(By.xpath(pageLocator), "Download page");
    }

    public void downloadSteam() {
        System.out.println("Start install steam");
        btnInstallSteam.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish install steam");

        String filePath = System.getProperty("user.dir") + getProperty("filePath");
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File file = null;

        System.out.println("Check that download file was appeared in the directory ");

        for (File fileItem : listOfFiles) {
            if (fileItem.isFile()) {
                String fileName = fileItem.getName();
                System.out.println("File " + fileItem.getName());
                if (fileName.matches(fileName)) {
                    file = new File(fileName);
                    found = true;
                    file.deleteOnExit();
                }
            }
        }
        assertTrue(found, "Download file does not find");

    }

    public void deleteFile() {
        File file = new File("src/test/resources/downloads/SteamSetup.exe");
        String filepath = file.getAbsolutePath();
        System.out.println(filepath);
        if (file.delete()) {
            System.out.println("file delete");
        } else {
            System.out.println("file didn't delete");
        }
    }
}
