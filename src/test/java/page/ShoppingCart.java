package page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import util.keyBoard;

import java.util.List;

public class ShoppingCart extends BasePage {
    public ShoppingCart() {
    }

    public ShoppingCart(List<String> products) {
        clickCashButton();
        addToCart(products);
    }

    public void clickCashButton() {
        findElementByXpath("//android.widget.ScrollView//android.widget.TextView[@text='收银']").click();
    }

    // 加入购物车
    private void addToCart(List<String> productNames) {
        productNames.forEach(productName -> findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_product']" +
                "//android.widget.TextView[@text='" + productName + "']").click());
    }

    //点击添加商品
    @Step("添加商品：{0}到购物车")
    public void addToCart(String productName) {
        findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_product']" +
                "//android.widget.TextView[@text='" + productName + "']").click();
    }

    @Step("购物车页，点击去收款")
    public void clickGoCashButton() {
        findElementById("ll_go_cash").click();

    }

    // 获取购物车的待收款金额
    public String getAmountOfGoodsInCart() {
        return findElementById("ltv_total_discount_price").getText();
    }

    //清空购物车
    @Step("清空购物车的商品")
    public void deletesGoodsInCart() {
        findElementById("ll_delete_all").click();
        findElementById("tv_confirm").click();
    }

    public boolean isExistGoodsInCart() {
        return (findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rv_product']/*").size() > 0);
    }

    //挂单或取单
    @Step("挂单或者取单")
    public void pendingOrTakeOrder() {
        findElementById("rl_take_order").click();
    }

    //获取挂单列表中第一个订单的金额
    public String getAmountOfFirstOrder() {
        return findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rlv_order_list']" +
                "//android.widget.TextView[@resource-id='com.caibaopay.cashier:id/ftv_amount']").get(0).getText();

    }

    //获取挂单列表中第一个订单，到购物车
    @Step("获取挂单列表中第一个订单，到购物车")
    public void takeFirstOrderToCart() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rlv_order_list']//*").get(0).click();
    }

    //点击购物车中的第一个商品
    @Step("点击购物车中的第一个商品")
    public void clickFirstGoodInCart() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rv_product']" +
                "//android.widget.LinearLayout[@resource-id='com.caibaopay.cashier:id/ll_container']").get(0).click();
    }

    @Step("输入商品的数量")
    public void inputQuantityOfTheGood(String quantity) {
        findElementById("ll_count").click();
        keyBoard.inputValueWithMiddleKeyboard(quantity);
    }

    @Step("输入折扣价格")
    public void inputDiscountPriceOfTheGood(String price) {
        findElementById("tv_discount_price").click();
        keyBoard.inputValueWithMiddleKeyboard(price);
    }

    public void inputDiscountRateOfTheGood(String rate) {
        findElementById("tv_discount_rate").click();
        keyBoard.inputValueWithMiddleKeyboard(rate);
    }

    public int getQuantityOfOneGoodInCart() {
        return Integer.parseInt(findElementById("tv_weight_num").getText());
    }

    public String getTagForOneGoodInCart() {
        return findElementById("tv_tag").getText();
    }

    public boolean isExistTagForOneGoodInCart() {
        return isElementPresent("By.Id", "tv_tag");
    }

    @Step("商品恢复原价")
    public void restoreOriginalPriceOfGood() {
        findElementById("tv_restore_price").click();
        keyBoard.inputValueWithKeyboard("MIDDLE", "Y");
    }

    @Step("删除单个商品")
    public void deleteOneGoodInCart() {
        findElementById("tv_delete").click();
    }

    @Step("滑动一级类目，选择类目")
    public void swipeAndClickLevelCategory(String categoryName) {
        //滑动类目
        swipeByCoordinateWithElement(categoryName, 0.73, 0.94, 0.08, 0.08);

        //点击类目
        findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_parent_category']" +
                "//android.widget.TextView[@text='" + categoryName + "']").click();
    }

    public void deleteOneGoodWithLeftSwipe() {
        String element = "//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rv_product']" +
                "//android.widget.LinearLayout[@resource-id='com.caibaopay.cashier:id/ll_container']";

        Point start=getLocationOfTheFirstGoodInCart(element);
        int startX = start.x;
        int startY = start.y;
        Dimension wh=getSizeOfTheFirstGoodInCart(element);
        int widthX=wh.getWidth();
        int heightY=wh.getHeight();
        int endX = widthX + startX;
        int endY = heightY/2 + startY;
        (new TouchAction(driver)).longPress(PointOption.point(endX,endY))
                .moveTo(PointOption.point(startX + (int)(widthX * 0.25),endY))
                .release()
                .perform();
    }

    public Dimension getSizeOfTheFirstGoodInCart(String element) {
        return findElementsByXpath(element).get(0).getSize();
    }

    public Point getLocationOfTheFirstGoodInCart(String element) {
        return findElementsByXpath(element)
                .get(0)
                .getLocation();
    }
}
