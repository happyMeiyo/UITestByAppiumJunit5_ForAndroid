package testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.UserLogin;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TestUserLogin extends TestApp {

    @Test
    @DisplayName("用户登录成功")
    void userLoginSuccess() {
        UserLogin ul = new UserLogin();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        //mapper.findAndRegisterModules();

        try {
            UserLogin user = mapper.readValue(TestBasePage.class.getResourceAsStream("/cashierInfo.yml"),
                    UserLogin.class);

            ul = new UserLogin(user.getMerchantCode(), user.getUserCode(), user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ul.userLogin();
        String shopInfo = ul.getShopInfo();

        assertThat(shopInfo, startsWith("您好 ！雨"));
        assertThat(shopInfo, endsWith("清雨的门店"));

        ul.userLogout();
    }

    @Test
    @DisplayName("用户登录失败")
    void userLoginFailure() {
        UserLogin ul = new UserLogin("201990", "qing", "a123456");
        ul.userLogin();
        String errorInfo = ul.getErrorInfo();
        assertThat("不存在的用户登录", errorInfo, equalTo("企业号或账号不正确"));
    }

}
