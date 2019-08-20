package sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager
{

    private static final String SHARED_PREF = "My Shared Pref";
    private static PreferenceManager preferenceManager;
    private Context context;

    public PreferenceManager(Context context) {
        this.context = context;
    }

    public static  synchronized PreferenceManager getInstance(Context context){
   if(preferenceManager==null){
   preferenceManager = new PreferenceManager(context);
    }

    return preferenceManager;
}

public void saveUser (String username , String password){

    SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF , context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    editor.putString("sharedUserName",username);
    editor.putString("sharedPassword",password);
    editor.apply();
    }
    public String getemail(){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        return new String(
                sharedPreferences.getString("shared username", null)
        );
    }
 public Boolean isUserLoggedIn(){

   SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
   return sharedPreferences.getString("shared username",null)!=null;
 }
 public void clear(){

SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
     editor.clear();
     editor.apply();
 }
}

