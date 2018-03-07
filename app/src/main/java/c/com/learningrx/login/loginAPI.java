package c.com.learningrx.login;

/**
 * Created by Ramu on 22-10-2017.
 */

public class loginAPI {

    private final String USERNAME   =   "ramu";
    private final String PASSWORD   =   "password";

    public boolean authenticate(String username,String password)
    {
        if(USERNAME.equalsIgnoreCase(username) && PASSWORD.equalsIgnoreCase(password))
            return true;
        return false;
    }
}
