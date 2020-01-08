package testcase;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.CashDeskPage;
import page.ShoppingCart;
import page.TempProduct;
import page.VipPage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestKaiMaiCashier_Android extends UserLoginOrOut{
    private ShoppingCart sc;
    private CashDeskPage cp;
    private TempProduct tp;
    private VipPage vip;

    @BeforeAll
    void initApp()
    {
        sc = new ShoppingCart();
        cp = new CashDeskPage();
        tp = new TempProduct();
        vip = new VipPage();
    }

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("测试购物车")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestShoppingCart{

        @BeforeAll
        void clickCashButton() {
            sc.clickCashButton();
        }

        @Order(1)
        @ParameterizedTest
        @DisplayName("添加商品到购物车")
        @Description("添加商品到购物车")
        @ValueSource(strings = {"红玫瑰苹果", "劲霸汤皇", "积分", "水果"})
        void addGoodsToShoppingCart(String productName) {
            sc.addToCart(productName);
            assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));
        }

        @Order(100)
        @Test
        @DisplayName("挂单成功")
        @Description("挂单成功")
        void pendingOrder() {
            String amountInCart = sc.getAmountOfGoodsInCart();
            sc.pendingOrTakeOrder();
            assertThat("提交挂单", sc.isExistGoodsInCart(), equalTo(false));

            sc.pendingOrTakeOrder();
            String amonutInList = sc.getAmountOfFirstOrder();
            assertThat("挂单成功，查看挂单列表", amountInCart, equalTo(amonutInList));


        }

        @Order(200)
        @Test
        @DisplayName("取单成功")
        @Description("取单成功")
        void takeFirstOrder() {
            sc.takeFirstOrderToCart();
            assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));
        }

        @Order(300)
        @Test
        @DisplayName("清空购物车")
        @Description("清空购物车")
        void deletesGoodsInCart() {
            sc.deletesGoodsInCart();
            assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(false));
        }


        @Order(400)
        @ParameterizedTest
        @CsvSource({
                "水果, 5, 15.5"
        })
        @DisplayName("购物车编辑商品：数量和价格")
        @Description("购物车编辑第一个商品的数量和价格")
        void editGoods(String productName, String quantity, String discountPrice) {
            sc.addToCart(productName);
            assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));

            //新增商品数量
            sc.clickFirstGoodInCart();
            sc.inputQuantityOfTheGood(quantity);
            assertThat("修改商品数量成功", sc.getQuantityOfOneGoodInCart(), greaterThan(1));

            //输入折扣价格
            sc.clickFirstGoodInCart();
            sc.inputDiscountPriceOfTheGood(discountPrice);
            assertThat("调低商品价格成功", sc.getTagForOneGoodInCart(), containsString("折"));

            //恢复商品价格
            sc.clickFirstGoodInCart();
            sc.restoreOriginalPriceOfGood();
            assertThat("恢复商品价格成功", sc.isExistTagForOneGoodInCart(), equalTo(false));
        }

        @Order(500)
        @Test
        @DisplayName("购物车编辑商品：删除商品")
        @Description("购物车编辑商品：删除商品")
        void deleteOneGoodInCart(){
            sc.clickFirstGoodInCart();
            sc.deleteOneGoodInCart();
            assertThat("删除单个商品成功", sc.isExistGoodsInCart(), equalTo(false));
        }
    }

    @DisplayName("测试收款页面")
    @Nested
    class TestCashDesk {
        @BeforeEach
        void addProductsToCart(){
            //添加普通商品
            List<String> products = Arrays.asList("红玫瑰苹果", "劲霸汤皇");
            sc = new ShoppingCart(products);

            // 添加临时商品
            tp = new TempProduct("临时商品-计件", 4, 58);
            tp.addTempProductToCart("临时商品");

            //点击去付款
            sc.clickGoCashButton();
        }

        @AfterEach
        void swipeFirstLevelCategory(){
            sc.swipeAndClickLevelCategory("新鲜水果");
        }


        @ParameterizedTest
        @Description("识别会员，并现金收款成功")
        @DisplayName("现金收款成功")
        @ValueSource(strings = {"18621902561"})
        void payWithCashForVip(String telephone){
            // 搜索会员
            cp.searchVip(telephone);

            //选择现金支付
            cp.selectPayWithCash();

            //选择实收现金金额
            cp.selectAmountReceived();

            //计算找零金额正确
            double changeAmount = 0.00;
            changeAmount = new BigDecimal(cp.getAmountReceived())
                    .subtract(new BigDecimal(cp.getAmountForPendingPay()))
                    .doubleValue();
            assertThat("找零金额计算正确", cp.getAmountForChange(), equalTo(String.format("%.2f", changeAmount)));

            //发起现金收款
            cp.payWithCash();
            assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));
        }

        @DisplayName("会员卡余额收款成功")
        @ParameterizedTest
        @Description("会员卡余额收款成功")
        @ValueSource(strings = {"18621902561"})
        void payWithVipCard(String telephone){
            //选择会员卡支付，搜索会员
            cp.searchVip(telephone);

            //发起会员卡余额收款
            cp.payWithVipCard();
            assertThat("收款成功，购物车清空", sc.isExistGoodsInCart(), equalTo(false));

        }

        @Description("二维码支付失败，现金支付成功")
        @ParameterizedTest
        @DisplayName("二维码支付失败，现金支付成功")
        @CsvSource({
                "5555552329379472897, 没有签约翼支付",
                "2870561546045819218, 重新收款"
        })
        void payWithBarcode(String barcode, String errorMsg){
            //选择二维码支付，支付失败
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

    @DisplayName("测试会员业务")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class TestVipApplication{
        @BeforeAll
        void clickVipButton() {
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

}
