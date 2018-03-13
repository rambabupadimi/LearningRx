package c.com.learningrx.register;

import android.support.annotation.StringRes;

/**
 * Created by Ramu on 27-10-2017.
 */

public class RegisterContract {
   public interface View
    {
        String getRegisterName();
        String getRegisterEmail();
        String getRegisterPhone();
        String getRegisterPassword();

        void setEmptyMessageForRegisterName(@StringRes int resId);
        void setEmptyMessageForRegisterEmail(@StringRes int resId);
        void setEmptyMessageForReisterPhone(@StringRes int resId);
        void setEmptyMessageForRegisterPassword(@StringRes int resId);
        void setRegisterPhoneTenDigits(@StringRes int resId);
        void setRegisterPasswordEightDigits(@StringRes int resId);

        void redirectToHomePage();
    }
  public   interface Presenter
    {
        void clickOnRegisterButton();
    }
}
