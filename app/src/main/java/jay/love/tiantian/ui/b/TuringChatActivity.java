package jay.love.tiantian.ui.b;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.GlobalData;
import jay.love.tiantian.R;
import jay.love.tiantian.data.retrofit.service.TuringApi;
import jay.love.tiantian.ui.b.adapter.ChatMessageAdapter;
import jay.love.tiantian.ui.b.contact.TuringChatActivityContact;
import jay.love.tiantian.ui.b.model.TLMessageEntity;
import jay.love.tiantian.ui.b.presenter.TuringChatActivityPresenter;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.utils.TimeUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TuringChatActivity extends BaseActivity<TuringChatActivityContact.Presenter> implements TuringChatActivityContact.View {

    @BindView(R.id.lv_message)
    ListView mLvMessage;
    @BindView(R.id.iv_send_msg)
    ImageView mIvSendMsg;
    @BindView(R.id.et_msg)
    EditText mEtMsg;
    private List<TLMessageEntity> msgList = new ArrayList<>();
    private ChatMessageAdapter msgAdapter;
    private String mCacheMsg;

    @Override
    protected TuringChatActivityContact.Presenter getPresenter() {
        return new TuringChatActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turing_chat);
        ButterKnife.bind(this);
        initToolBar();
        initData();
        initListener();
    }
    private void initToolBar() {
        setTitle("调戏一下");
        setLeftMenu(R.drawable.draw_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initListener() {
//        mLvMessage.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                KeyBoardUtils.hideKeyboard(TuringChatActivity.this);
//            }
//        });
    }

    private void initData() {
        if (msgList.size() == 0) {
            TLMessageEntity entity = new TLMessageEntity(ChatMessageAdapter.TYPE_LEFT, TimeUtil.getCurrentTimeMillis());
            entity.setText("你好！我是JAY！\n咱俩聊点什么呢？\n你有什么要问的么？");
            msgList.add(entity);
        }
        msgAdapter = new ChatMessageAdapter(this, msgList);
        mLvMessage.setAdapter(msgAdapter);
        mLvMessage.setSelection(msgAdapter.getCount());
    }

    // 请求图灵API接口，获得问答信息
    private void requestApiByRetrofit_RxJava(String info) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalData.TURING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        TuringApi api = retrofit.create(TuringApi.class);

        api.getTuringInfo(GlobalData.TURING_KEY, info)
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
                        handleResponseMessage(entity);
                    }
                });
    }
    // 请求图灵API接口，获得问答信息
    private void requestApiByRetrofit(String info) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GlobalData.TURING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TuringApi api = retrofit.create(TuringApi.class);

        Call<TLMessageEntity> call = api.getTuringInfo0(GlobalData.TURING_KEY, info);
        call.enqueue(new Callback<TLMessageEntity>() {
            @Override
            public void onResponse(Call<TLMessageEntity> call, Response<TLMessageEntity> response) {
                handleResponseMessage(response.body());
            }

            @Override
            public void onFailure(Call<TLMessageEntity> call, Throwable t) {

            }
        });
    }

    // 处理获得到的问答信息
    private void handleResponseMessage(TLMessageEntity entity) {
        if (entity == null) return;

        entity.setTime(TimeUtil.getCurrentTimeMillis());
        entity.setType(ChatMessageAdapter.TYPE_LEFT);

        switch (entity.getCode()) {
            case GlobalData.TuringCode.URL:
                entity.setText(entity.getText() + "，点击网址查看：" + entity.getUrl());
                break;
            case GlobalData.TuringCode.NEWS:
                entity.setText(entity.getText() + "，点击查看");
                break;
        }
        switch (mCacheMsg) {
            case "你是谁":
                entity.setText("杰宝");
                break;
            case "我是谁":
                entity.setText("田宝");
                break;
        }

        msgList.add(entity);
        msgAdapter.notifyDataSetChanged();
    }
    // 给Turing发送问题
    public void sendMessage() {
        String msg = mEtMsg.getText().toString().trim();

        if (!TextUtils.isEmpty(msg)) {
            TLMessageEntity entity = new TLMessageEntity(ChatMessageAdapter.TYPE_RIGHT, TimeUtil.getCurrentTimeMillis(), msg);
            msgList.add(entity);
            msgAdapter.notifyDataSetChanged();
            mCacheMsg = msg;
            mEtMsg.setText("");

            // 仅使用 Retrofit 请求接口
//            requestApiByRetrofit(msg);

            // 使用 Retrofit 和 RxJava 请求接口
//            requestApiByRetrofit_RxJava(msg);
            //使用 mvp加Retrofit 和 RxJava 请求接口
            mPresenter.getTuringMessage(msg);
        }
    }
    @OnClick(R.id.iv_send_msg)
    public void onClick() {
        sendMessage();
    }

    @Override
    public void requestSuccess(TLMessageEntity entity) {
        handleResponseMessage(entity);
    }
}
