package c.com.learningrx.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import c.com.learningrx.R;
import c.com.learningrx.register.RegisterContract;
import c.com.learningrx.register.RegisterPresenter;
import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterRepository;

/**
 * Created by Ramu on 11-11-2017.
 */

public class RegisterPresenterTest {
        private RegisterContract.View view;
        private RegisterContract.Presenter presenter;
        private RegisterRepository registerRepository;
        @Before
        public void setUp() throws Exception
        {
            view        =   Mockito.mock(RegisterContract.View.class);
            registerRepository = Mockito.mock(RegisterRepository.class);
            presenter   =   new RegisterPresenter(view,registerRepository);

        }

        @Test
        public void whenUsernameIsEmptyShowError() throws Exception
        {
            Mockito.when(view.getRegisterName()).thenReturn("");
            Mockito.when(view.getRegisterEmail()).thenReturn("babu@gmail.com");
            Mockito.when(view.getRegisterPhone()).thenReturn("8342682265");
            Mockito.when(view.getRegisterPassword()).thenReturn("12345");
            presenter.clickOnRegisterButton();
            Mockito.verify(view).setEmptyMessageForRegisterName(R.string.name_is_mandatory);
        }

        @Test
        public void whenEmailIsEmptyShowError() throws Exception
        {
            Mockito.when(view.getRegisterName()).thenReturn("ramu");
            Mockito.when(view.getRegisterEmail()).thenReturn("");
            Mockito.when(view.getRegisterPhone()).thenReturn("8342682265");
            Mockito.when(view.getRegisterPassword()).thenReturn("12345");
            presenter.clickOnRegisterButton();
            Mockito.verify(view).setEmptyMessageForRegisterEmail(R.string.email_is_mandatory);
        }

        @Test
        public void whenPhoneNumberIsEmptyShowError() throws Exception
        {
            Mockito.when(view.getRegisterName()).thenReturn("ramu");
            Mockito.when(view.getRegisterEmail()).thenReturn("babu@gmail.com");
            Mockito.when(view.getRegisterPhone()).thenReturn("");
            Mockito.when(view.getRegisterPassword()).thenReturn("12345");
            presenter.clickOnRegisterButton();
            Mockito.verify(view).setEmptyMessageForReisterPhone(R.string.phone_is_mandatory);
        }

        @Test
        public void whenPasswordIsEmptyShowError() throws Exception
        {
            Mockito.when(view.getRegisterName()).thenReturn("ramu");
            Mockito.when(view.getRegisterEmail()).thenReturn("babu@gmail.com");
            Mockito.when(view.getRegisterPhone()).thenReturn("8342682265");
            Mockito.when(view.getRegisterPassword()).thenReturn("");
            presenter.clickOnRegisterButton();
            Mockito.verify(view).setEmptyMessageForRegisterPassword(R.string.password_is_mandatory);
        }


}

