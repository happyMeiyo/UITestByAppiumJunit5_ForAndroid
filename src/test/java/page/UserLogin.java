package page;

import io.qameta.allure.Step;

public class UserLogin extends BasePage {

    private String merchantCode;
    private String userCode;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String merchantCode, String userCode, String password) {
        this.merchantCode = merchantCode;
        this.userCode = userCode;
        this.password = password;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getPassword() {
        return password;
    }

    @Step("输入企业号")
    private void inputMerchantCode() {
        findElementById("et_company_account").sendKeys(this.merchantCode);
    }

    @Step("输入用户账号")
    private void inputUserCode() {
        findElementById("et_cashier_account").sendKeys(this.userCode);
    }

    @Step("输入密码")
    private void inputPassword() {
        findElementById("et_pw").sendKeys(this.password);
    }

    @Step("用户登录")
    public void userLogin() {
        /* 输入企业号 */
        inputMerchantCode();

        //输入用户名
        inputUserCode();

        //输入密码
        inputPassword();

        //点击登录
        findElementById("tv_login").click();

    }

    public String getShopInfo() {
        /*获取门店信息*/
        return findElementById("tv_shop_info").getText();
    }

    public String getErrorInfo() {
        /*获取登录错误信息*/
        return findElementById("tv_error").getText();
    }

    @Step("用户退出")
    public String userLogout() {
        findElementById("ll_logout").click();
        return findElementById("et_cashier_account").getText();
    }

}
