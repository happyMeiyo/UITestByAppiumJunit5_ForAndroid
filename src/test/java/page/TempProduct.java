package page;

import util.keyBoard;

public class TempProduct extends BasePage{
    private String productName;
    private int quantity;
    private double price;

    public TempProduct(String productName, int quantity, double price){
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    private void inputPriceAndQuantityOfTempPrd(String price, String quantity){
        findElementById("tv_sale_price").click();
        keyBoard.inputValueWithTempKeyboard(price);

        findElementById("tv_number").click();
        keyBoard.inputValueWithTempKeyboard(quantity);

    }

    private void inputPriceAndQuantityOfTempPrd(String categoryName){
        //一级类目滑动到“临时商品”
        swipeByCoordinate(categoryName, 0.94, 0.73, 0.08, 0.08);

        //点击临时商品类目
        findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_parent_category']" +
                "//android.widget.TextView[@text='" + categoryName + "']").click();

        //点击临时商品
        findElementByXpath("//android.support.v7.widget.RecyclerView" +
                "[@resource-id='com.caibaopay.cashier:id/rl_product']" +
                "//android.widget.TextView[@text='" + productName + "']").click();

        //输入售价和数量
        inputPriceAndQuantityOfTempPrd(Double.toString(price), Integer.toString(quantity));
    }

    // 获取临时商品的价格
    public String getAmountOfTempPrpduct(){
        return findElementById("tv_total_price").getText();
    }

    public void addTempProductToCart(String categoryName){
        inputPriceAndQuantityOfTempPrd(categoryName);
        findElementById("tv_confirm").click();
    }
}
