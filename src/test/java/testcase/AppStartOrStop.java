package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import page.App;

@DisplayName("启动或停止APP")
class AppStartOrStop {

    @BeforeAll
    static void startApp() {
        App.start();
    }

    @AfterAll
    static void tearDown() {
        App.stop();
    }
}
