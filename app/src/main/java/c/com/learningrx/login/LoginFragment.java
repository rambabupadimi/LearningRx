package c.com.learningrx.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import c.com.learningrx.MainActivity;
import c.com.learningrx.R;

/**
 * Created by Ramu on 22-10-2017.
 */

public class LoginFragment extends Fragment implements LoginContract.View{

    EditText loginUsernameEditText,
            loginPasswordEditText;

    Button loginButton;
    LoginPresenter loginPresenter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        loginUsernameEditText   =   (EditText) view.findViewById(R.id.login_username);
        loginPasswordEditText   =   (EditText) view.findViewById(R.id.login_password);
        loginButton             =   (Button) view.findViewById(R.id.login);
        loginButton.setOnClickListener(loginClick);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginPresenter  =   new LoginPresenter(this,new loginAPI());
    }

    private View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loginPresenter.clickOnLoginButton();
        }
    };

    @Override
    public String getUsername() {
        return loginUsernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return loginPasswordEditText.getText().toString();
    }

    @Override
    public void setEmptyUsernameErrorMessage(int resId) {
        loginUsernameEditText.setText(getString(resId));
    }

    @Override
    public void setEmptyPasswordErrorMessage(int resId) {
        loginPasswordEditText.setText(getString(resId));
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(getContext(), "username or password in correct",Toast.LENGTH_SHORT).show();
    }
}
