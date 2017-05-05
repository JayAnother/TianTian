package jay.love.tiantian.application;

import android.app.Application;

/**
 * Created by gc on 2017/1/16.
 */
public class App extends Application {

    private static App mInstance;
    static final private Object lock = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (lock) {
            mInstance = this;
        }
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

    public static App getInstance() {
        return mInstance;
    }

}