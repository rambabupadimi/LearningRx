package c.com.learningrx.source;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Register> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;


    public static RegisterRepository getInstance(RegisterDataSource tasksRemoteDataSource,
                                                 RegisterDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RegisterRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }



    @Override
    public void getAllUsers(final LoadRegisterCallback callback) {

        // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onRegisterUsersLoaded(new ArrayList<Register>(mCachedTasks.values()));
            return;
        }
        if(mCacheIsDirty)
        {
            // If the cache is dirty we need to fetch new data from the network.
            getRegisterUsersFromRemoteDataSource(callback);
        }
        else
        {
            // Query the local storage if available. If not, query the network.
            registerLocalDataSource.getAllUsers(new LoadRegisterCallback() {
                @Override
                public void onRegisterUsersLoaded(List<Register> users) {
                    refreshCache(users);
                    callback.onRegisterUsersLoaded(new ArrayList<Register>(mCachedTasks.values()));
                    return;
                }

                @Override
                public void onDataNotAvailable() {
                    getRegisterUsersFromRemoteDataSource(callback);
                }
            });

        }
    }



    @Override
    public void registerUser(Register register) {
            checkNotNull(register);
            registerLocalDataSource.registerUser(register);
            registerRemoteDataSource.registerUser(register);
        // Do in memory cache update to keep the app UI up to date
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(register.getId(), register);

    }

    @Override
    public void deleteUsers() {
        registerLocalDataSource.deleteUsers();
        registerRemoteDataSource.deleteUsers();
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
    }


    private void getRegisterUsersFromRemoteDataSource(final LoadRegisterCallback callback){
        registerRemoteDataSource.getAllUsers(new LoadRegisterCallback() {
            @Override
            public void onRegisterUsersLoaded(List<Register> users) {
                refreshCache(users);
                refreshLocalDataSource(users);
                callback.onRegisterUsersLoaded(new ArrayList<>(mCachedTasks.values()));

            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Register> tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (Register task : tasks) {
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Register> tasks) {
        registerLocalDataSource.deleteUsers();
        for (Register task : tasks) {
            registerLocalDataSource.registerUser(task);
        }
    }





}
