package jay.love.tiantian.ui.b.contact;

import jay.love.tiantian.ui.b.model.MessageEntity;
import jay.love.tiantian.ui.base.BasePresenter;
import jay.love.tiantian.ui.base.BaseView;

/**
 * Created by jay on 2017/5/12.
 */

public class TuringChatActivityContact {
    public interface  View extends BaseView {

        void requestSuccess(MessageEntity entity);

    }
    public interface Presenter extends BasePresenter<View> {
        void getTuringMessage(String info);
    }
}
