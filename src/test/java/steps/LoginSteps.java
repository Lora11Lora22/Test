package steps;

import lombok.SneakyThrows;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    public RunTestSteps doLogin(String user, String password) {
        inputLoginDate(user, password);
        return new RunTestSteps();
    }

    @SneakyThrows
    private void inputLoginDate(String user, String password) {
        loginPage.fillLoginUser(user);
        loginPage.fillLoginPassword(password);
        Thread.sleep(1000);
        loginPage.clickLoginButton();
    }

}
