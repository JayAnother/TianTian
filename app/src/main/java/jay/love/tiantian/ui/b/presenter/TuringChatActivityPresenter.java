package jay.love.tiantian.ui.b.presenter;

import jay.love.tiantian.ui.b.contact.TuringChatActivityContact;
import jay.love.tiantian.ui.base.RxPresenter;

/**
 * Created by jay on 2017/5/12.
 */

public class TuringChatActivityPresenter extends RxPresenter<TuringChatActivityContact.View> implements TuringChatActivityContact.Presenter {


//    public void getLikes(final int likeId){
//        //不分页,只显示前200位成员
//        Subscription subscription= DataManager.getInstance().getFeedApi().getLikes(postMode.getId(),likeId,200)
//                .compose(RxUtil.<ResponseModel<ListBaseModel<LikesModel>>>rxSchedulerHelper()).
//                        compose(RxUtil.<ListBaseModel<LikesModel>>handleResult()).
//                        subscribe(new Action1<ListBaseModel<LikesModel>>() {
//                            @Override
//                            public void call(ListBaseModel<LikesModel> listBaseModel) {
//                                mView.closeDialog();
//                                if(listBaseModel.getData().size()>0) {
//                                    if (likeId == -1) {
//                                        likes.clear();
//                                    }
//                                    likes.addAll(listBaseModel.getData());
//                                }
//                                postMode.setLike(listBaseModel.getTotal());
//                                mView.setLike(postMode.getLiked(),postMode.getLike());
//
//                                mView.likesLoadSuccess(listBaseModel);
//                            }
//                        }, new ErrorHandlerAction(){
//                            @Override
//                            protected void requestError(ApiFaildException ex) {
//                                mView.closeDialog();
//                                mView.showMessage(ex.getErrorMsg());
//                                mView.likesLoadFail();
//                            }
//                        });
//
//        addSubscrebe(subscription);
//    }
}
