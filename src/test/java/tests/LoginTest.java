package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login("admin", "password");
    }
}