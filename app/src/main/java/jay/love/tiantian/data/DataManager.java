package jay.love.tiantian.data;


import jay.love.tiantian.data.preferces.PreferHelper;

public class DataManager {
    private static  volatile  DataManager dataManager;
    private PreferHelper preferHelper;
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

//    public MemberApi  getMemberApi(){
//        if(memberApi==null){
//            synchronized (DataManager.class){
//                memberApi= RetrofitHelper.getDefaultRetrofit().create(MemberApi.class);
//            }
//        }
//        return memberApi;
//    }


}
