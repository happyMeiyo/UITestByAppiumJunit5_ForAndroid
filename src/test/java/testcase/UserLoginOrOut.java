package testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import page.UserLogin;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("用户登录或退出")
public class UserLoginOrOut extends AppStartOrStop {

    private static UserLogin ul;

    @BeforeAll
    static void userLogin() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            UserLogin user = mapper.readValue(UserLoginOrOut.class.getResourceAsStream("/shopManagerInfo.yml"),
                                              UserLogin.class);

            ul = new UserLogin(user.getMerchantCode(), user.getUserCode(), user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ul.userLogin();
        String shopInfo = ul.getShopInfo();

        assertThat(shopInfo, startsWith("您好 ！清"));
        assertThat(shopInfo, endsWith("清雨的门店"));
    }

    @AfterAll
    static void userLogout() {
        String account = ul.userLogout();
        assertThat(account, equalTo("请输入收银员账号或手机号"));
    }
}
