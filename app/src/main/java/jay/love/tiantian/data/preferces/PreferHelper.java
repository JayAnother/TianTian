package jay.love.tiantian.data.preferces;

import android.content.Context;
import android.content.SharedPreferences;

import jay.love.tiantian.application.App;
import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Administrator on 2017/1/3.
 */

public class PreferHelper {
   private  static final String FILE_NAME="appData";
    private static final String FIELD_USER="user";
    private static final String FIELD_USER_GUIDE="user_guide";
    private static final String FIELD_GCM_TOKEN="gcmToken";
    private SharedPreferences getSharedPreferences(){
        return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }
    private SharedPreferences.Editor getEditor(){
        SharedPreferences sharedPreferences=getSharedPreferences();
        return  sharedPreferences.edit();
    }

    public  void saveUserGuide(boolean show){
        getEditor().putBoolean(FIELD_USER_GUIDE, show).apply();
    }
    public Observable<Boolean>  showUserGuide(){
        Observable<Boolean> observable=Observable.fromCallable(new Func0<Boolean>() {
            @Override
            public Boolean call() {
                SharedPreferences sharedPreferences=getSharedPreferences();
                boolean show= sharedPreferences.getBoolean(FIELD_USER_GUIDE,false);
                return show;
            }
        });
        return observable;

    }
}
