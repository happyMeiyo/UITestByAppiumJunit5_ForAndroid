import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import testcase.TestCashDesk;
import testcase.TestShoppingCart;

@RunWith(JUnitPlatform.class)
@SelectClasses({TestCashDesk.class, TestShoppingCart.class})
public class runTest {

}
