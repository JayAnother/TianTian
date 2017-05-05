package jay.love.tiantian.ui.base;

/**
 * Created by jay on 2017/5/5.
 */

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V mvpView);
    void detachView();
}
