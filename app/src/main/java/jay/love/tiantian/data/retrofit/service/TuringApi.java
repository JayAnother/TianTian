package jay.love.tiantian.data.retrofit.service;

import jay.love.tiantian.ui.b.model.MessageEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jay on 2017/5/12.
 */

public interface TuringApi {

    // 请求图灵API接口，获得问答信息
    @GET("openapi/api")
    Call<MessageEntity> getTuringInfo0(@Query("key") String key, @Query("info") String info);

    // 请求图灵API接口，获得问答信息
    @GET("openapi/api")
    Observable<MessageEntity> getTuringInfo(@Query("key") String key, @Query("info") String info);

}
