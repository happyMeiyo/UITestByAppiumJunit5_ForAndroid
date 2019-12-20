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

    public double getAmountForPendingPay() {
        String amount = findElementById("tv_pending_amount").getText();
        return Double.parseDouble(amount);
    }

    public void selectPayWithCash() {
        findElementsByXpath("//android.support.v7.widget.RecyclerView[@resource-id='com.caibaopay.cashier:id/rv_pay_type']" +
                "//android.widget.TextView[@text='现金&记账']").get(0).click();
    }

    public double getAmountReceived() {
        String amount = findElementById("atv_amount").getText();
        return Double.parseDouble(amount);
    }

    public double getAmountForChange() {
        String amount = findElementById("atv_cash_back").getText();
        return Double.parseDouble(amount);
    }

    public void selectAmountReceived() {
        findElementById("tv_fourth").click();
    }

    public void payWithCash() {
        keyBoard.inputKeyValueForRight('Y');
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
