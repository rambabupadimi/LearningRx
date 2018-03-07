package c.com.learningrx.source.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.Map;


import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterDataSource;

/**
 * Created by Ramu on 25-11-2017.
 */

public class RegisterRemoteDatasource implements RegisterDataSource {
    private static RegisterRemoteDatasource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;

    private final static Map<String, Register> REGISTER_SERVICE_DATA;
    static {
        REGISTER_SERVICE_DATA = new LinkedHashMap<>(2);
        addTask("Rambabu",
                "babu.ramu111@gmail.com",
                "ramu123",
                "8341682265",
                "123");

        addTask("Ravi",
                "ravi.ramu111@gmail.com",
                "ravi123",
                "7095362286",
                "124");

    }


    private static void addTask(String name,
                                String email,
                                String password,
                                String phone,
                                String id
                                ) {

       Register register = new Register(name,email,password,phone,id);
       REGISTER_SERVICE_DATA.put(register.getId(), register);
    }



    @Override
    public void registerUser(@NonNull  Register register) {
        REGISTER_SERVICE_DATA.put(register.getId(),register);
    }

    @Override
    public void getAllUsers(@NonNull final LoadRegisterCallback callback) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onRegisterUsersLoaded(Lists.newArrayList(REGISTER_SERVICE_DATA.values()));
            }
        },SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void deleteUsers() {

    }
}
