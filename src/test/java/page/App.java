package page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class App {

    static AndroidDriver driver;

//    static AndroidDriver getDriver() {
//        return driver;
//    }

    private static class Capability {
        private Map<String, String> capability;
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setCapability(Map<String, String> capability) {
            this.capability = capability;
        }

    }

    public static void start() throws MalformedURLException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        try {
            Capability cp = mapper.readValue(App.class.getResourceAsStream("/app.yml"), Capability.class);
            cp.capability.forEach(desiredCapabilities::setCapability);
            URL remoteUrl = new URL(cp.url);
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void stop() {
        driver.quit();
    }

}
