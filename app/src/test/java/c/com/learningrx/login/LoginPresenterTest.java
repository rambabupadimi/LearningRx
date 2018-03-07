package c.com.learningrx.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import c.com.learningrx.R;

import static org.junit.Assert.*;

/**
 * Created by Ramu on 22-10-2017.
 */
public class LoginPresenterTest {

    private LoginPresenter loginPresenter;
    private LoginContract.View view;
    private loginAPI loginapI;
    @Before
    public void setUp() throws Exception
    {

        view            = Mockito.mock(LoginContract.View.class);
        loginapI        =   Mockito.mock(loginAPI.class);
        loginPresenter  = new LoginPresenter(view,loginapI);
    }

    @Test
    public void whenUserNameIsEmptyShowError() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("");
        loginPresenter.clickOnLoginButton();
        Mockito.verify(view).setEmptyUsernameErrorMessage(R.string.username_mandatory);
    }

    @Test
    public void whenPasswordIsEmptyShowError() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ramu");
        Mockito.when(view.getPassword()).thenReturn("");
        loginPresenter.clickOnLoginButton();
        Mockito.verify(view).setEmptyPasswordErrorMessage(R.string.password_manadatory);
    }

    @Test
    public void whenUsernameAndPasswordNotEmptyWithServerSideWrongCredentials() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ramu");
        Mockito.when(view.getPassword()).thenReturn("123");
        Mockito.when(loginapI.authenticate("ramu","123")).thenReturn(false);
        loginPresenter.clickOnLoginButton();
        Mockito.verify(view).loginError();
    }

    @Test
    public void whenUsernameAndPasswordWithServerSideRightCredentials() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ramu");
        Mockito.when(view.getPassword()).thenReturn("password");
        Mockito.when(loginapI.authenticate("ramu","password")).thenReturn(true);
        loginPresenter.clickOnLoginButton();
        Mockito.verify(view).startMainActivity();
    }
}