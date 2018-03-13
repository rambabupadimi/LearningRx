package c.com.learningrx.register;

import c.com.learningrx.R;
import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterDataSource;
import c.com.learningrx.source.RegisterRepository;

/**
 * Created by Ramu on 27-10-2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterContract.View view;


    RegisterRepository registerRepository;
    public RegisterPresenter(RegisterContract.View view,RegisterRepository registerRepository)
    {
        this.view = view;
        this.registerRepository = registerRepository;
    }
    @Override
    public void clickOnRegisterButton() {
        String name = view.getRegisterName();
        if(name.isEmpty())
        {
            view.setEmptyMessageForRegisterName(R.string.name_is_mandatory);
            return;
        }
        String email = view.getRegisterEmail();
        if(email.isEmpty())
        {
            view.setEmptyMessageForRegisterEmail(R.string.email_is_mandatory);
            return;
        }

        String phone = view.getRegisterPhone();
        if(phone.isEmpty())
        {
            view.setEmptyMessageForReisterPhone(R.string.phone_is_mandatory);
            return;
        }
        if( phone.toString().length()<10)
        {
            view.setRegisterPhoneTenDigits(R.string.phone_number_must_be_10_digits);
            return;
        }
        String password = view.getRegisterPassword();
        if(password.isEmpty())
        {
            view.setEmptyMessageForRegisterPassword(R.string.password_is_mandatory);
            return;
        }

        if(password.toString().length()<8)
        {
            view.setRegisterPasswordEightDigits(R.string.password_length);
        }

        register(new Register(name,email,password,phone,getId()) );

    }


    private void register(Register register)
    {
        registerRepository.registerUser(register);
        view.redirectToHomePage();
    }

    private String getId()
    {
        return "11";
    }
}
