package jay.love.tiantian.data.retrofit;

import java.util.concurrent.TimeUnit;

import jay.love.tiantian.GlobalData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/3.
 */

public class RetrofitHelper {



    public static Retrofit getDefaultRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GlobalData.serviceRequestUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
    private static OkHttpClient getOkHttpClient(){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (GlobalData.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
//        builder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                Request.Builder  builder1=original.newBuilder()
//                        .header("API-VERSION", GlobalData.apiVersion)
//                        .header("Content-type","application/json")
//                        .header("API-KEY", GlobalData.apiKey);
//                if(!TextUtils.isEmpty(GlobalData.token)){
//                    LogUtils.d("OkHttp","TOKEN:"+GlobalData.token);
//                    builder1 .header("TOKEN", GlobalData.token);
//                }
//                Request request1=builder1.method(original.method(), original.body()).build();
//                return chain.proceed(request1);
//            }
//        });
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        builder.retryOnConnectionFailure(true);
        OkHttpClient mOkHttpClient=builder.build();
        return mOkHttpClient;
    }

}
