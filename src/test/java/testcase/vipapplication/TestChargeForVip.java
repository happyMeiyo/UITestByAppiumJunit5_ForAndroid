package testcase.vipapplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.*;
import page.VipPage;
import testcase.UserLoginOrOut;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("测试会员充值")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestChargeForVip extends UserLoginOrOut {
    private static VipPage vip;

    @BeforeAll
    static void searchVip() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            VipPage vp = mapper.readValue(TestChargeForVip.class.getResourceAsStream("/telephone.yml"), VipPage.class);
            vip = new VipPage(vp.getTelephone());
        } catch (IOException e) {
            e.printStackTrace();
        }

        vip.clickVipButton();
        vip.searchVipOfTelephone();
    }

    @Order(1)
    @Description("会员现金充值成功")
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
    @Test
    void checkCoupon() {
        vip.clickCoupon();
        assertThat("查看券列表", vip.isListOfCouponsExist(), equalTo(true));
    }

    @Order(200)
    @Description("查看积分列表")
    @Test
    void checkPoints() {
        vip.clickPoints();
        assertThat("查看积分列表", vip.isListOfPointsExist(), equalTo(true));
    }

    @Order(200)
    @Description("绑定解绑实体卡成功")
    @Flaky
    @Test
    void bandAndunbandPhysicalCard() {
        String card = "1234567890";
        vip.bandPhysicalCard(card);
        assertThat("绑定实体卡成功", vip.getPhysicalCardNo(), equalTo(card));

        vip.unbandPhysicalCard();

    }

}

