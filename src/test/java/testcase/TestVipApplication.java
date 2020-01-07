package testcase;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.VipPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

@DisplayName("测试会员业务")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestVipApplication extends UserLoginOrOut {
    private static VipPage vip = new VipPage();

    @BeforeAll
    static void clickVipButton() {
        vip.clickVipButton();
    }

    @Order(1)
    @Description("查找会员成功")
    @DisplayName("查找会员成功")
    @ParameterizedTest
    @ValueSource(strings = {"18621902561", "2561"})
    void searchVipSuccess(String telephone) {
        vip.setTelephone(telephone);
        vip.clearVipNoForSearch();
        vip.searchVipOfTelephone();
        assertThat("查找会员成功", vip.getTelephoneOfVip(), startsWith("1862190"));
    }

    @Order(50)
    @Description("会员现金充值成功")
    @DisplayName("会员现金充值成功")
    @Test
    void chargeForVipWithCash() {
        vip.clickBalance();

        // 输入充值金额 5
        vip.inputBalanceForVip("5");
        vip.chargeForVipWithCash();
        assertThat("充值成功", vip.getBalanceOfCharge(), equalTo(5.0F));

        vip.confirmChargeSuccess();
    }

    @Order(100)
    @Description("查看券列表")
    @DisplayName("查看券列表")
    @Test
    void checkCoupon() {
        vip.clickCoupon();
        assertThat("查看券列表", vip.isListOfCouponsExist(), equalTo(true));
    }

    @Order(200)
    @Description("查看积分列表")
    @DisplayName("查看积分列表")
    @Test
    void checkPoints() {
        vip.clickPoints();
        assertThat("查看积分列表", vip.isListOfPointsExist(), equalTo(true));
    }

    @Order(200)
    @Description("绑定实体卡成功之后，解绑实体卡")
    @DisplayName("绑定解绑实体卡成功")
    @Flaky
    @Test
    void bandAndunbandPhysicalCard() {
        String card = "1234567890";
        vip.bandPhysicalCard(card);
        assertThat("绑定实体卡成功", vip.getPhysicalCardNo(), equalTo(card));

        vip.unbandPhysicalCard();

    }

    @Order(300)
    @Description("查找会员失败")
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

