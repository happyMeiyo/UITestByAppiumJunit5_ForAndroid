package testcase;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import page.CashDeskPage;
import page.ShoppingCart;
import page.TempProduct;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("测试收款页面")
class TestCashDesk extends UserLoginOrOut {
    private static Stream<Arguments> getTelephoneOfVip() {
        return Stream.of(
                arguments("18621902561")
        );
    }

    private ShoppingCart sc = new ShoppingCart();

    @BeforeEach
    void addProductsToCart(){
        //添加普通商品
        List<String> products = Arrays.asList("果脯", "哈密瓜切果");
        sc = new ShoppingCart(products);

        // 添加临时商品
        TempProduct tp = new TempProduct("临时商品", 4, 58);
        tp.addTempProductToCart("临时商品");

        //点击去付款
        sc.clickGoCashButton();
    }

    @AfterEach
    void swipeFirstLevelCategory(){
        sc.swipeAndClickLevelCategory("鲜果");
    }

    @RepeatedTest(value = 100, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @Description("加购商品，现金收款成功")
    @DisplayName("现金收款成功")
    void payWithCash(){
        // 搜索会员
        CashDeskPage cp = new CashDeskPage();

        //选择现金支付
        cp.selectPayWithCash();

        //选择实收现金金额
        cp.selectAmountReceived();

        //计算找零金额正确
        double changeAmount = new BigDecimal(cp.getAmountReceived())
                .subtract(new BigDecimal(cp.getAmountForPendingPay()))
                .doubleValue();
        assertThat("找零金额计算正确", cp.getAmountForChange(), equalTo(String.format("%.2f", changeAmount)));

        //发起现金收款
        cp.payWithCash();
        assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));
    }


//    @ParameterizedTest
    @RepeatedTest(value = 500, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @Description("识别会员，并现金收款成功")
    @DisplayName("会员现金收款成功")
//    @MethodSource("getTelephoneOfVip")
    void payWithCashForVip(){
        // 搜索会员
        String telephone = "18621902561";
        CashDeskPage cp = new CashDeskPage();
        cp.searchVip(telephone);

        //选择现金支付
        cp.selectPayWithCash();

        //选择实收现金金额
        cp.selectAmountReceived();

        //计算找零金额正确
        double changeAmount = new BigDecimal(cp.getAmountReceived())
                .subtract(new BigDecimal(cp.getAmountForPendingPay()))
                .doubleValue();
        assertThat("找零金额计算正确", cp.getAmountForChange(), equalTo(String.format("%.2f", changeAmount)));

        //发起现金收款
        cp.payWithCash();
        assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));
    }

    @RepeatedTest(value = 500, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("会员卡余额收款成功")
//    @ParameterizedTest
    @Description("会员卡余额收款成功")
//    @MethodSource("getTelephoneOfVip")
    void payWithVipCard(){
        //选择会员卡支付，搜索会员
        CashDeskPage cp = new CashDeskPage();
        String telephone = "18621902561";
        cp.searchVip(telephone);

        //发起会员卡余额收款
        cp.payWithVipCard();
        assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));

    }

    @Description("二维码支付失败，现金支付成功")
    @ParameterizedTest
    @DisplayName("二维码支付失败，现金支付成功")
    @CsvSource({
            "5555552329379472897, 支付信息不存在",
            "2870561546045819218, 支付信息不存在"
    })
    void payWithBarcode(String barcode, String errorMsg){
        //选择二维码支付，支付失败
        CashDeskPage cp = new CashDeskPage();
        cp.selectPayWithBarCode();

        //手动输入付款码，支付失败
        cp.inputBarcodeAndPay(barcode);
        assertThat("二维码付款失败", cp.getErrorMsgForPayFail(), containsString(errorMsg));

        //选择重新收款
        cp.clickPayTryAgain();

        //选择现金支付，支付成功
        cp.selectPayWithCash();
        cp.selectAmountReceived();
        cp.payWithCash();
        assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));
    }
}
