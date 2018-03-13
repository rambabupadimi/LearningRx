package c.com.learningrx.source;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Ramu on 28-10-2017.
 */

public class RegisterRepository implements RegisterDataSource {

    private static RegisterRepository INSTANCE = null;
    private final RegisterDataSource registerLocalDataSource;
    private final RegisterDataSource registerRemoteDataSource;

    private RegisterRepository(RegisterDataSource registerLocalDataSource1,
                               RegisterDataSource registerRemoteDataSource1)
    {
        registerLocalDataSource = checkNotNull(registerLocalDataSource1);
        registerRemoteDataSource = checkNotNull(registerRemoteDataSource1);
    }


    public static RegisterRepository getInstance(RegisterDataSource registerLocalDataSource,
                                                 RegisterDataSource registerRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RegisterRepository(registerLocalDataSource, registerRemoteDataSource);
        }
        return INSTANCE;
    }



    @Override
    public void registerUser(Register register) {
            checkNotNull(register);
            registerLocalDataSource.registerUser(register);
            registerRemoteDataSource.registerUser(register);

    }

    @Override
    public void saveProduct(Product product) {
        checkNotNull(product);
        registerLocalDataSource.saveProduct(product);
        registerRemoteDataSource.saveProduct(product);
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
    registerLocalDataSource.getTasks(new LoadTasksCallback() {
        @Override
        public void onTasksLoaded(List<Product> tasks) {

            callback.onTasksLoaded(tasks);
        }

        @Override
        public void onDataNotAvailable() {

        }
    });
    }

}
