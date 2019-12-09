package testcase;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.ShoppingCart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestShoppingCart extends TestBasePage {

    private static ShoppingCart sc = new ShoppingCart();

    @BeforeAll
    static void clickCashButton() {
        sc.clickCashButton();
    }

    @Order(1)
    @DisplayName("添加商品到购物车")
    @ParameterizedTest
    @ValueSource(strings = {"红玫瑰苹果", "劲霸汤皇", "积分", "水果"})
    void addGoodsToShoppingCart(String productName) {
        sc.addToCart(productName);
        assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));
    }

    @Order(100)
    @DisplayName("挂单成功")
    @Test
    void pendingOrder() {
        String amountInCart = sc.getAmountOfGoodsInCart();
        sc.pendingOrTakeOrder();
        assertThat("提交挂单", sc.isExistGoodsInCart(), equalTo(false));

        sc.pendingOrTakeOrder();
        String amonutInList = sc.getAmountOfFirstOrder();
        assertThat("挂单成功，查看挂单列表", amountInCart, equalTo(amonutInList));


    }

    @Order(200)
    @DisplayName("取单成功")
    @Test
    void takeFirstOrder() {
        sc.takeFirstOrderToCart();
        assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));
    }


    @Order(400)
    @DisplayName("清空购物车")
    @Test
    void deletesGoodsInCart() {
        sc.deletesGoodsInCart();
        assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(false));
    }

    @DisplayName("编辑商品")
    @ParameterizedTest
    @ValueSource(strings = {"水果"})
    void editGoods(String productName) {
        sc.addToCart(productName);
        assertThat("商品加入购物车成功", sc.isExistGoodsInCart(), equalTo(true));

        sc.clickFirstGoodInCart();
    }



}
