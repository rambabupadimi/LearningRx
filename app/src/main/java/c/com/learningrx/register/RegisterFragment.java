package c.com.learningrx.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import c.com.learningrx.R;
import c.com.learningrx.addproducts.ProductsActivity;
import c.com.learningrx.source.RegisterRepository;
import c.com.learningrx.source.local.RegisterDatabase;
import c.com.learningrx.source.local.RegisterLocalDatasource;
import c.com.learningrx.source.remote.RegisterRemoteDatasource;
import c.com.learningrx.util.AppExecutors;

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
        RegisterDatabase database = RegisterDatabase.getInstance(getContext());
        RegisterRepository registerRepository = RegisterRepository.getInstance(
                RegisterLocalDatasource.getInstance(new AppExecutors(),
                        database.registerDAO(),
                        database.productDAO()),RegisterRemoteDatasource.getInstance());
        regsiterPresenter   =   new RegisterPresenter(this,registerRepository);

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

    @Override
    public void redirectToHomePage() {
        Intent intent = new Intent(getContext(), ProductsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
