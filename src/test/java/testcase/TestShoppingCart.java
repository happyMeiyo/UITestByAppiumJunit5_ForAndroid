package testcase;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import page.ShoppingCart;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("测试购物车")
class TestShoppingCart extends UserLoginOrOut {

    private static ShoppingCart sc = new ShoppingCart();

    @Order(1)
    @ParameterizedTest
    @DisplayName("添加商品到购物车")
    @Description("添加商品到购物车")
    @ValueSource(strings = {"果脯", "哈密瓜切果"})
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

    private static Stream<Arguments> getQuantityAndPrice() {
        return Stream.of(
                arguments("果脯", "5", "1.5")
        );
    }

    @Order(400)
    @ParameterizedTest
    @MethodSource("getQuantityAndPrice")
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
