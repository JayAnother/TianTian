package jay.love.tiantian.ui.b;

import android.os.Bundle;

import jay.love.tiantian.R;
import jay.love.tiantian.ui.b.contact.TuringChatActivityContact;
import jay.love.tiantian.ui.b.presenter.TuringChatActivityPresenter;
import jay.love.tiantian.ui.base.BaseActivity;

public class TuringChatActivity extends BaseActivity<TuringChatActivityContact.Presenter> implements TuringChatActivityContact.View {

    @Override
    protected TuringChatActivityContact.Presenter getPresenter() {
        return new TuringChatActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turing_chat);
    }
}
