package jay.love.tiantian.ui.b.presenter;

import java.util.HashMap;

import jay.love.tiantian.GlobalData;
import jay.love.tiantian.data.DataManager;
import jay.love.tiantian.ui.b.contact.TuringChatActivityContact;
import jay.love.tiantian.ui.b.model.TLMessageEntity;
import jay.love.tiantian.ui.base.RxPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jay on 2017/5/12.
 */

public class TuringChatActivityPresenter extends RxPresenter<TuringChatActivityContact.View> implements TuringChatActivityContact.Presenter {


//    public void getLikes(String info){
//        //不分页,只显示前200位成员
//        Subscription subscription= DataManager.getInstance().getTuringApi().getTuringInfo(GlobalData.TURING_KEY, info)
//                .compose(RxUtil.<MessageEntity>rxSchedulerHelper()).
//                        compose(RxUtil.<MessageEntity>handleResult()).
//                        subscribe(new Action1<ListBaseModel<LikesModel>>() {
//                            @Override
//                            public void call(ListBaseModel<LikesModel> listBaseModel) {
//
//                            }
//                        }, new ErrorHandlerAction(){
//                            @Override
//                            protected void requestError(ApiFailedException ex) {
//
//                            }
//                        });
//
//        addSubscrebe(subscription);
//    }
    public void getTuringMessage(String info){
        HashMap<String,String> map=new HashMap<>();
        map.put("","");
       DataManager.getInstance().getTuringApi().getTuringInfo(GlobalData.TURING_KEY, info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TLMessageEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TLMessageEntity entity) {
                        mView.requestSuccess(entity);
                    }
                });
    }
}
