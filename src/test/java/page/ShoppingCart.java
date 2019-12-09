package page;

import util.keyBoard;

import java.util.Arrays;
import java.util.List;

public class ShoppingCart extends BasePage {

    public void clickCashButton() {
        findElementByXpath("//android.widget.ScrollView//android.widget.TextView[@text='收银']").click();
    }

    // 加入购物车
    public void addToCart(String[] products) {
        List<String> productNames = Arrays.asList(products);
        productNames.forEach(productName -> findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_product']" +
                "//android.widget.TextView[@text='" + productName + "']").click());
    }
    public void addToCart(String productName) {
        findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_product']" +
                "//android.widget.TextView[@text='" + productName + "']").click();
    }


    // 获取购物车的待收款金额
    public String getAmountOfGoodsInCart(){
        return findElementById("ltv_total_discount_price").getText();
    }

    //清空购物车
    public void deletesGoodsInCart(){
        findElementById("ll_delete_all").click();
        findElementById("tv_confirm").click();
    }

    public boolean isExistGoodsInCart()
    {
        return (findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rv_product']/*").size() > 0);
    }

    //挂单或取单
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
    public void  takeFirstOrderToCart(){
        findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rlv_order_list']//*").get(0).click();
    }

    //点击购物车中的第一个商品
    public void clickFirstGoodInCart(){
        findElementsByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rv_product']" +
                "//android.widget.LinearLayout[@resource-id=com.caibaopay.cashier:id/ll_container']").get(0).click();
    }

    public void inputQuantityOfTheGood(String quantity){
        findElementById("tv_count").click();
        keyBoard.inputValueWithLeftKeyboard(quantity);
    }

    public void inputDiscountPriceOfTheGood(String price){
        findElementById("tv_discount_price").click();
        keyBoard.inputValueWithLeftKeyboard(price);
    }

    public void inputDiscountRateOfTheGood(String rate){
        findElementById("tv_discount_rate").click();
        keyBoard.inputValueWithLeftKeyboard(rate);
    }

    public void deleteOneGoodInCart() {
        findElementById("tv_delete").click();
    }

}
