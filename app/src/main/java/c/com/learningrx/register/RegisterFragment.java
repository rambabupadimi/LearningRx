package c.com.learningrx.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import c.com.learningrx.R;

/**
 * Created by Ramu on 27-10-2017.
 */

public class RegisterFragment extends Fragment implements
        RegisterContract.View{

    EditText registerName,registerEmail,registerPhone,registerPassword;
    Button registerButton;
    RegisterPresenter regsiterPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);

        registerName    = (EditText) view.findViewById(R.id.name);
        registerEmail   = (EditText) view.findViewById(R.id.email);
        registerPhone   = (EditText) view.findViewById(R.id.phone);
        registerPassword    = (EditText) view.findViewById(R.id.password);
        registerButton  = (Button) view.findViewById(R.id.register);
        return view;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        regsiterPresenter   =   new RegisterPresenter(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regsiterPresenter.clickOnRegisterButton();
            }
        });
    }

    public String getRegisterName() {
        return registerName.getText().toString();
    }


    public String getRegisterEmail() {
        return registerEmail.getText().toString();
    }

    public String getRegisterPhone() {
        return registerPhone.getText().toString();
    }


    public String getRegisterPassword() {
        return registerPassword.getText().toString();
    }

    @Override
    public void setEmptyMessageForRegisterName(int resId) {
        registerName.setText(getString(resId));
    }

    @Override
    public void setEmptyMessageForRegisterEmail(int resId) {
        registerEmail.setText(getString(resId));
    }

    @Override
    public void setEmptyMessageForReisterPhone(int resId) {
        registerPhone.setText(getString(resId));
    }

    @Override
    public void setEmptyMessageForRegisterPassword(int resId) {
        registerPassword.setText(getString(resId));
    }

    @Override
    public void setRegisterPhoneTenDigits(int resId) {
        registerPhone.setText(getString(resId));
    }

    @Override
    public void setRegisterPasswordEightDigits(int resId) {
        registerPassword.setText(getString(resId));
    }


}
