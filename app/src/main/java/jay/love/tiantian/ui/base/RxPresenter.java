package jay.love.tiantian.ui.base;

import jay.love.tiantian.data.retrofit.ApiFailedException;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jay on 2017/5/5.
 */

public class RxPresenter<T extends  BaseView> implements BasePresenter<T> {
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(T mvpView) {
        mView = mvpView;
    }


    public String getErrorMsg(Throwable throwable) {
        String errorMsg = "";
        if (throwable instanceof ApiFailedException) {
            ApiFailedException apiException = (ApiFailedException) throwable;
            errorMsg = apiException.getMessage();
        } else {
            errorMsg = throwable.getMessage();
        }
        return errorMsg;
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }


    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
