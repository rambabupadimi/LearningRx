package c.com.learningrx.source.remote;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import c.com.learningrx.source.Product;
import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterDataSource;

/**
 * Created by Ramu on 25-11-2017.
 */

public class RegisterRemoteDatasource implements RegisterDataSource {
    private static RegisterRemoteDatasource INSTANCE;

    public static RegisterRemoteDatasource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RegisterRemoteDatasource();
        }
        return INSTANCE;
    }

    @Override
    public void registerUser(@NonNull  Register register) {

    }
    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }


}
