package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import page.App;

import java.net.MalformedURLException;

@DisplayName("启动或停止APP")
class TestApp {

    @BeforeAll
    static void startApp() throws MalformedURLException {
        App.start();
    }

    @AfterAll
    static void tearDown() {
        App.stop();
    }
}
