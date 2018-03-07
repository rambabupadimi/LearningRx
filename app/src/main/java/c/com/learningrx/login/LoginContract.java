package c.com.learningrx.login;

import android.support.annotation.StringRes;

/**
 * Created by Ramu on 22-10-2017.
 */

public class LoginContract {

    public interface View
    {
        String getUsername();
        String getPassword();
        void setEmptyUsernameErrorMessage(@StringRes  int resId);
        void setEmptyPasswordErrorMessage(@StringRes  int resId);
        void startMainActivity();
        void loginError();
    }

}
