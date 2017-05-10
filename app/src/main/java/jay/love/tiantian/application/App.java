package jay.love.tiantian.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import jay.love.tiantian.GlobalData;
import jay.love.tiantian.ui.game.dish.DishManager;

/**
 * Created by jay on 2017/1/16.
 */
public class App extends Application {

    private static App mInstance;
    static final private Object lock = new Object();
    private static DishManager dm;
    private static int level = 4;


    @Override
    public void onCreate() {
        super.onCreate();

//        synchronized (lock) {
        if (mInstance == null) mInstance = (App) getApplicationContext();
//        }

        SharedPreferences pref = getSharedPreferences(GlobalData.SP_NAME, MODE_PRIVATE);
        setLevel(pref.getInt(GlobalData.SP_LEVEL, 4));
//
//        // 读取配置文件
//        ConfigUtils.getInstance().readConfig();
//        // 依赖注入框架ButterKnife
//        ButterKnife.setDebug(butterknife.BuildConfig.DEBUG);
//
//        // 初始化Bmob
//        if (!ConstantUtils.BMOB_APP_ID.isEmpty()) {
//            BmobConfig config =new BmobConfig.Builder(this)
//                    .setApplicationId(ConstantUtils.BMOB_APP_ID)// 设置appkey
//                    .setConnectTimeout(30)// 请求超时时间（单位为秒）：默认15s
//                    .setUploadBlockSize(1024*1024)// 文件分片上传时每片的大小（单位字节），默认512*1024
//                    .setFileExpiration(2500)// 文件的过期时间(单位为秒)：默认1800s
//                    .build();
//            Bmob.initialize(config);
//        }
    }

    public static Context getInstance() {
        if (mInstance != null) {
            return mInstance;
        }
        return null;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        if (level < 3) return;
        App.level = level;
        dm = new DishManager(level);
    }

    public static DishManager getDishManager() {
        return dm;
    }
}