package page;

import util.keyBoard;

import static util.keyBoard.inputValueWithRightKeyboard;

public class CashDeskPage extends BasePage {
    private void inputTelephoneForVip(String telephone) {
        inputValueWithRightKeyboard(telephone);
    }

    public void searchVip(String telephone) {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='会员卡']").get(0).click();
        findElementById("ll_search_container").click();

        inputTelephoneForVip(telephone);
    }

    public String getAmountForPendingPay() {
        return findElementById("tv_pending_amount").getText();
    }

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

    public void selectAmountReceived() {
        findElementById("tv_fourth").click();
    }

    public void payWithCash() {
        keyBoard.inputValueWithKeyboard("RIGHT", "Y");
    }

    public void payWithVipCard() {
        findElementById("tv_confirm_pay").click();
    }

    public void selectPayWithBarCode() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='扫码支付']").get(0).click();
    }

    public void inputBarcodeAndPay(String barcode) {
        findElementById("tv_input_code").click();
        findElementById("view_code_cursor").click();

        keyBoard.inputValueWithRightKeyboard(barcode);
    }

    public String getErrorMsgForPayFail() {
        return findElementById("tv_error_msg").getText();
    }

    public void clickPayTryAgain() {
        findElementById("tv_retry").click();
    }

}
