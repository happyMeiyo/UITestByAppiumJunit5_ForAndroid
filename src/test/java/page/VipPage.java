package page;


import io.qameta.allure.Step;
import util.keyBoard;

public class VipPage extends BasePage {

    private String telephone;

    public VipPage(String telephone) {
        this.telephone = telephone;
    }

    public VipPage() {

    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    @Step("点击会员TAB")
    public void clickVipButton() {
        findElementByXpath("//android.widget.ScrollView//android.widget.TextView[@text='会员']").click();
    }

    public void clearVipNoForSearch() {
        try {
            findElementById("tv_search_text").clear();
        } catch (Exception ex) {
            System.out.println("会员搜索框无内容： " + ex);
        }
    }

    @Step("搜索会员")
    public void searchVipOfTelephone() {
        findElementById("ll_search_container").click();
        keyBoard.inputValueWithLeftKeyboard(telephone);
    }

    public String getTelephoneOfVip() {
        return findElementById("aft_phone_no").getText();
    }

    public boolean searchVipFailure() {
        return isElementPresent("By.Id", "ll_empty");
    }

    @Step("查看券列表")
    public void clickCoupon() {
        findElementById("ll_check_coupon").click();
    }

    public boolean isListOfCouponsExist() {
        int countOfCoupon = findElementsByXpath("//*[@resource-id='com.caibaopay.cashier:id/rl_coupon']" +
                "/android.widget.LinearLayout[@resource-id='com.caibaopay.cashier:id/ll_container']").size();
        return countOfCoupon != 0;

    }

    @Step("查看积分列表")
    public void clickPoints() {
        findElementById("rl_point").click();
    }

    public boolean isListOfPointsExist() {
        int countOfPointList = findElementsByXpath("//*[@resource-id='com.caibaopay.cashier:id/rl_point_list']" +
                "/android.widget.LinearLayout[@resource-id='com.caibaopay.cashier:id/ll_container']").size();
        return countOfPointList != 0;

    }

    public void clickBalance() {
        findElementById("rl_balance").click();
    }

    @Step("输入充值金额")
    public void inputBalanceForVip(String balance) {
        findElementById("tv_money_unit").click();
        keyBoard.inputValueWithRightKeyboard(balance);
    }

    @Step("选择现金充值")
    public void chargeForVipWithCash() {
        findElementByXpath("//*[@resource-id='com.caibaopay.cashier:id/st_title']" +
                "//android.widget.TextView[@text='现金支付']").click();

        findElementById("tv_confirm_pay").click();
    }

    public float getBalanceOfCharge() {
        return Float.parseFloat(findElementById("tv_recharge_amount").getText());
    }

    @Step("确认现金充值成功")
    public void confirmChargeSuccess() {
        findElementById("tv_confirm").click();
    }

    @Step("绑定实体卡")
    public void bandPhysicalCard(String card) {
        findElementById("rl_physical_card").click();
        keyBoard.inputValueWithRightKeyboard(card);
    }

    public String getPhysicalCardNo() {
        return findElementById("tv_input_no").getText();
    }

    @Step("解绑实体卡")
    public void unbandPhysicalCard() {
        findElementById("tv_unbind").click();
    }

}