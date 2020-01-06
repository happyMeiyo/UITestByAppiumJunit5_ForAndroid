package page;

import io.qameta.allure.Step;
import util.keyBoard;

import static util.keyBoard.inputValueWithRightKeyboard;

public class CashDeskPage extends BasePage {
    private void inputTelephoneForVip(String telephone) {
        inputValueWithRightKeyboard(telephone);
    }

    @Step("搜索会员")
    public void searchVip(String telephone) {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='会员卡']").get(0).click();
        findElementById("ll_search_container").click();

        inputTelephoneForVip(telephone);
    }

    public String getAmountForPendingPay() {
        return findElementById("tv_pending_amount").getText();
    }

    @Step("选择支付方式：现金支付")
    public void selectPayWithCash() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='现金&记账']").get(0).click();
    }

    public String getAmountReceived() {
        return findElementById("atv_amount").getText();
    }

    public String getAmountForChange() {
        return findElementById("atv_cash_back").getText();
    }

    @Step("选择实收金额")
    public void selectAmountReceived() {
        findElementById("tv_fourth").click();
    }

    @Step("发起现金收款")
    public void payWithCash() {
        keyBoard.inputValueWithKeyboard("RIGHT", "Y");
    }

    @Step("发起会员卡余额收款")
    public void payWithVipCard() {
        findElementById("tv_confirm_pay").click();
    }

    @Step("发起二维支付码收款")
    public void selectPayWithBarCode() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='扫码支付']").get(0).click();
    }

    @Step("输入支付二维码")
    public void inputBarcodeAndPay(String barcode) {
        findElementById("tv_input_code").click();
        findElementById("view_code_cursor").click();

        keyBoard.inputValueWithRightKeyboard(barcode);
    }

    public String getErrorMsgForPayFail() {
        return findElementById("tv_error_msg").getText();
    }

    @Step("选择重新收款")
    public void clickPayTryAgain() {
        findElementById("tv_retry").click();
    }

}
