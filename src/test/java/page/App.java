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

public class App extends BasePage{

    private static class Capability{
        private Map<String, String> capability;
        private String url;

        public void setCapability(Map<String, String> capability) {
            this.capability = capability;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public static void start() throws MalformedURLException {
        ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Capability cp = new Capability();

        try {
            cp = mapper.readValue(App.class.getResourceAsStream("/app.yml"), Capability.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        cp.capability.forEach(desiredCapabilities::setCapability);
        URL remoteUrl = new URL(cp.url);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public static void stop(){
        driver.quit();
    }

}
