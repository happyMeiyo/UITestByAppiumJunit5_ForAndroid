package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import page.App;

import java.net.MalformedURLException;

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
