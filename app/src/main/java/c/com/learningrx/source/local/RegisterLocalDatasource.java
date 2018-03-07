package c.com.learningrx.source.local;

import android.support.annotation.NonNull;

import java.util.List;

import c.com.learningrx.source.Register;
import c.com.learningrx.source.RegisterDataSource;
import c.com.learningrx.util.AppExecutors;

/**
 * Created by Ramu on 25-11-2017.
 */

public class RegisterLocalDatasource implements RegisterDataSource{

    private static volatile RegisterLocalDatasource INSTANCE;

    private RegisterDAO registerDAO;

    AppExecutors appExecutors;

    // Prevent direct instantiation.
    private RegisterLocalDatasource(@NonNull AppExecutors appExecutors,
                                 @NonNull RegisterDAO registerDAO) {
        appExecutors = appExecutors;
        registerDAO = registerDAO;
    }

    public static RegisterLocalDatasource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull RegisterDAO registerDAO) {
        if (INSTANCE == null) {
            synchronized (RegisterLocalDatasource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RegisterLocalDatasource(appExecutors, registerDAO);
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public void registerUser(final Register register) {

        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                registerDAO.insertUser(register);
            }
        };
        appExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void getAllUsers(@NonNull final LoadRegisterCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Register> registerList = registerDAO.getRegisterUsers();
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(registerList.isEmpty())
                        {
                            callback.onDataNotAvailable();
                        }
                        else {
                            callback.onRegisterUsersLoaded(registerList);
                        }
                    }
                });
            }
        };
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteUsers() {

    }
}
