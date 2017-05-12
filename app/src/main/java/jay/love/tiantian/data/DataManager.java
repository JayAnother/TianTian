package jay.love.tiantian.data;


import jay.love.tiantian.data.preferces.PreferHelper;
import jay.love.tiantian.data.retrofit.service.TuringApi;

public class DataManager {
    private static  volatile  DataManager dataManager;
    private PreferHelper preferHelper;
    private TuringApi mTuringApi;
    private static final Object lock = new Object();
    private DataManager(){
    }
    public static DataManager getInstance(){

        if(dataManager==null){
            synchronized (lock){
                if(dataManager==null) {
                    dataManager = new DataManager();
                }
            }
        }
        return dataManager;
    }

    public PreferHelper getPreferHelper(){
        if(preferHelper==null){
            synchronized (DataManager.class){
                preferHelper=new PreferHelper();
            }
        }
        return preferHelper;
    }

//    public TuringApi getTuringApi(){
//        if(mTuringApi==null){
//            synchronized (DataManager.class){
//                mTuringApi= RetrofitHelper.getDefaultRetrofit().create(mTuringApi.class);
//            }
//        }
//        return mTuringApi;
//    }


}
