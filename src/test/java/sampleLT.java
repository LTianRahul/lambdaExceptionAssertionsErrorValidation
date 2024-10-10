import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class sampleLT {

    public static void main(String[] args) throws Exception {
        // LambdaTest authentication credentials
        String username = "rahulkumarlambdatest";
        String accessKey = "dboZK7so8koMnIR1tN11aKfMgxyKtDpb90IlyaCj4n6n7tQeK6";
        
//        String username = "rahulkashyap8058";
//        String accessKey = "ZdBfaCldKCl0Sjo0jFcUOGoHhacNDsoJ3mrdrDwmw2v47Id84T"; 

        // Define LambdaTest URL
        String gridURL = "@hub.lambdatest.com/wd/hub";

        // Define LambdaTest capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("visual", true);
//        capabilities.setCapability("tunnel", true);
        capabilities.setCapability("tunnelName", "lambdstest");
        capabilities.setCapability("platform", "win 10");
        capabilities.setCapability("build", "LambdaTest Sample Java Selenium Test");
        capabilities.setCapability("name", "LambdaTest Exception Capture Test");

        // Set LambdaTest credentials
        capabilities.setCapability("user", username);
        capabilities.setCapability("accessKey", accessKey);

        // Initialize WebDriver
        WebDriver driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);

        // List to capture exceptions
        List<String> exceptionCapture = new ArrayList<String>();

        try {
         
            driver.get("https://www.lambdatest.com");
            
            driver.findElement(By.xpath("#abc")).click(); 
            
        } catch (Exception e) {
            // Capture exception stack trace
            StringWriter sw = new StringWriter();
            PrintWriter printWriter = new PrintWriter(sw);
            e.printStackTrace(printWriter);
            String sStackTrace = sw.toString();
            exceptionCapture.add(sStackTrace);

            // Execute JavaScript to send exceptions to LambdaTest
            ((JavascriptExecutor) driver).executeScript("lambda-exceptions", exceptionCapture);
        } finally {
            // Quit WebDriver session
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
