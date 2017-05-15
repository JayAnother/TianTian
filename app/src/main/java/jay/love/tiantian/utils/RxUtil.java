package jay.love.tiantian.utils;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/16.
 */

public class RxUtil {

    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     *
     * @param noCheckTokens  如果有值并等于true,则不判断code是否是token过期
     * @param <T>
     * @return
     */
//    public static <T> Observable.Transformer<ResponseModel<T>, T> handleResult(final boolean... noCheckTokens) {
//        return new Observable.Transformer<ResponseModel<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<ResponseModel<T>> httpResponseObservable) {
//                return httpResponseObservable.flatMap(new Func1<ResponseModel<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(ResponseModel<T> tGankHttpResponse) {//errorMessage
//                        if(tGankHttpResponse.getCode()==1) {
//                            return createData(tGankHttpResponse.getData());
//                        } else {
//                            //如果没有noCheckTokens=true的标志,则token过期后自动重新登录
//                            if(noCheckTokens.length==0) {
//                                checkUserToken(tGankHttpResponse.getCode());
//                            }
//                            return Observable.error(ExceptionParse.parseException(new ApiException(tGankHttpResponse.getMessage()),tGankHttpResponse.getCode()));
//                        }
//                    }
//                });
//            }
//        };
//    }
//    public static  boolean callSuccess(ResponseModel responseModel,boolean... noCheckTokens) {
//        if(responseModel.getCode()==1) {
//            return true;
//        } else {
//            //如果没有noCheckTokens=true的标志,则token过期后自动重新登录
//            if(noCheckTokens.length==0) {
//                checkUserToken(responseModel.getCode());
//            }
//            return false;
//        }
//    }
//
//    private static void checkUserToken(int responseCode){
////        'FORBIDDEN' => ['CODE' => 403, "MESSAGE" => 'Forbidden'],
////        // 用户不存在
////        'USER_NOT_EXIST' => ['CODE' => 2, "MESSAGE" => 'User not exist.'],
////        // 用户Token过期
////        'USER_TOKEN_EXPIRE' => ['CODE' => 3, "MESSAGE" => 'User token expire.'],
////        // 用户Token无效
////        'USER_TOKEN_INVALID' => ['CODE' => 4, "MESSAGE" => 'User token invalid.'],
////        // 用户Token作废
////        'USER_TOKEN_BLACKLISTED' => ['CODE' => 5, "MESSAGE" => 'User token blacklisted.'],
////        // 用户Token不存在
////        'USER_TOKEN_ABSENT' => ['CODE' => 6, "MESSAGE" => 'User token absent.'],
//        if( isTokenError(responseCode) ){
//            Intent broadcast = new Intent();
//            broadcast.setAction("rework.token_expired");
//            App.getInstance().sendBroadcast(broadcast);
//        }
//    }
//    public static boolean isTokenError(int responseCode){
////        'FORBIDDEN' => ['CODE' => 403, "MESSAGE" => 'Forbidden'],
////        // 用户不存在
////        'USER_NOT_EXIST' => ['CODE' => 2, "MESSAGE" => 'User not exist.'],
////        // 用户Token过期
////        'USER_TOKEN_EXPIRE' => ['CODE' => 3, "MESSAGE" => 'User token expire.'],
////        // 用户Token无效
////        'USER_TOKEN_INVALID' => ['CODE' => 4, "MESSAGE" => 'User token invalid.'],
////        // 用户Token作废
////        'USER_TOKEN_BLACKLISTED' => ['CODE' => 5, "MESSAGE" => 'User token blacklisted.'],
////        // 用户Token不存在
////        'USER_TOKEN_ABSENT' => ['CODE' => 6, "MESSAGE" => 'User token absent.'],
//        if(  (responseCode>=400&&responseCode<=404)||(responseCode>=2&&responseCode<=6) ){
//            return true;
//        }
//        return false;
//
//    }
}
