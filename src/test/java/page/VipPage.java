package page;


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

    public void clickVipButton() {
        findElementByXpath("//android.widget.ScrollView//android.widget.TextView[@text='会员']").click();
    }

    public void clearVipNoForSearch(){
        try {
            findElementById("tv_search_text").clear();
        }catch (Exception ex){
            System.out.println("会员搜索框无内容： " + ex);
        }
    }

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


    public void clickCoupon() {
        findElementById("ll_check_coupon").click();
    }

    public boolean isListOfCouponsExist() {
        int countOfCoupon = findElementsByXpath("//*[@resource-id='com.caibaopay.cashier:id/rl_coupon']" +
                "/android.widget.LinearLayout[@resource-id='com.caibaopay.cashier:id/ll_container']").size();
        return countOfCoupon != 0;

    }

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

    public void inputBalanceForVip(String balance) {
        findElementById("tv_money_unit").click();
        keyBoard.inputValueWithRightKeyboard(balance);
    }

    public void chargeForVipWithCash() {
        findElementByXpath("//*[@resource-id='com.caibaopay.cashier:id/st_title']" +
                "//android.widget.TextView[@text='现金支付']").click();

        findElementById("tv_confirm_pay").click();
    }

    public float getBalanceOfCharge() {
        return Float.parseFloat(findElementById("tv_recharge_amount").getText());
    }

    public void confirmChargeSuccess() {
        findElementById("tv_confirm").click();
    }

    public void bandPhysicalCard(String card){
        findElementById("rl_physical_card").click();
        keyBoard.inputValueWithRightKeyboard(card);
    }

    public String getPhysicalCardNo(){
        return findElementById("tv_input_no").getText();
    }

    public void unbandPhysicalCard(){
        findElementById("tv_unbind").click();
    }

}