package c.com.learningrx.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import c.com.learningrx.source.Register;

/**
 * Created by Ramu on 25-11-2017.
 */
@Database(entities = {Register.class},version = 1)
public abstract class RegisterDatabase extends RoomDatabase {

    private static RegisterDatabase INSTANCE;

    public abstract RegisterDAO registerDAO();

    private static final Object sLock = new Object();

    public static RegisterDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        RegisterDatabase.class, "Register.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
