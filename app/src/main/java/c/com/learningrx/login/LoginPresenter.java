package c.com.learningrx.login;

import c.com.learningrx.R;

/**
 * Created by Ramu on 22-10-2017.
 */

public class LoginPresenter {


    loginAPI loginAPI;
    LoginContract.View view;

    public LoginPresenter(LoginContract.View view, loginAPI loginAPI) {
        this.loginAPI = loginAPI;
        this.view  = view;
    }

    public void clickOnLoginButton() {
        String username = view.getUsername();
        if(username.isEmpty())
        {
            view.setEmptyUsernameErrorMessage(R.string.username_mandatory);
            return;
        }
        String password = view.getPassword();

        if(password.isEmpty())
        {
            view.setEmptyPasswordErrorMessage(R.string.password_manadatory);
        }

        if(loginAPI.authenticate(username,password))
        {
            view.startMainActivity();
        }
        view.loginError();
    }
}
