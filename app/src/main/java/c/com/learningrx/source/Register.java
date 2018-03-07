package c.com.learningrx.source;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Ramu on 27-10-2017.
 */
@Entity(tableName = "register")
public final class Register {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryId")
    private final String id;

    @Nullable
    @ColumnInfo(name = "name")
    private final String name;

    @Nullable
    @ColumnInfo(name = "email")
    private final String email;

    @NonNull
    @ColumnInfo(name = "password")
    private final String password;

    @Nullable
    @ColumnInfo(name = "phone")
    private final String phone;

    public Register(@Nullable String name, String email, String password, String phone,String id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.id  = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getId()
    {
        return id;
    }
}
