package c.com.learningrx.source;

import java.util.List;

/**
 * Created by Ramu on 27-10-2017.
 */

public interface RegisterDataSource {

    interface  LoadRegisterCallback
    {
        void onRegisterUsersLoaded(List<Register> users);
        void onDataNotAvailable();
    }

    void registerUser(Register register);
    void getAllUsers(LoadRegisterCallback callback);
    void deleteUsers();
}
