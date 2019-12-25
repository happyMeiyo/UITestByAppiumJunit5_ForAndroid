package testcase.vipapplication;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.VipPage;
import testcase.TestBasePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

@DisplayName("测试搜索会员")
class TestSearchVip extends TestBasePage {
    private static VipPage vip = new VipPage();

    @BeforeAll
    static void clickVipButton() {
        vip.clickVipButton();
    }

    @DisplayName("查找会员成功")
    @ParameterizedTest
    @ValueSource(strings = {"18621902561", "2561"})
    void searchVipSuccess(String telephone) {
        vip.setTelephone(telephone);
        vip.clearVipNoForSearch();
        vip.searchVipOfTelephone();
        assertThat("查找会员成功", vip.getTelephoneOfVip(), startsWith("1862190"));
    }

    @DisplayName("查找会员失败")
    @ParameterizedTest
    @ValueSource(strings = {"123456789"})
    void searchVipFailure(String telephone) {
        vip.setTelephone(telephone);
        vip.clearVipNoForSearch();
        vip.searchVipOfTelephone();
        assertThat("查找会员失败", vip.searchVipFailure(), equalTo(true));
    }


}
