package jay.love.tiantian.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.common.utils.JViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.home.MainActivity;
import jay.love.tiantian.utils.ConstantUtils;
import jay.love.tiantian.utils.SPUtils;
import jay.love.tiantian.utils.UtilTools;
import jay.love.tiantian.widget.heartview.HeartView;
import jay.love.tiantian.widget.heartview.OnDrawHeartListener;

public class SplashActivity extends AppCompatActivity {


    public static final int DELAY_MILLIS = 500;
    @BindView(R.id.heart)
    HeartView mHeart;
    @BindView(R.id.tv_splash)
    TextView mTvSplash;
    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */
    private TextView tv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ConstantUtils.HANDLER_SPLASH:
                    // 判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        JViewUtil.setTranslucentStatus(SplashActivity.this);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

//        if (!ConstantUtils.BMOB_APP_ID.isEmpty()) {
        initView();
//        } else {
//            ToastUtils.showLong(getApplicationContext(), "Replace with your BmobSDK appkey.");
//        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        // 设置字体
        UtilTools.setFont(this, mTvSplash);
//        mHeart.reDraw();
        mHeart.setOnDrawHeartListener(new OnDrawHeartListener() {
            @Override
            public void drawStart() {

            }

            @Override
            public void drawFinish() {
                // 延时2000ms
                handler.sendEmptyMessageDelayed(ConstantUtils.HANDLER_SPLASH, DELAY_MILLIS);
            }
        });
    }

    /**
     * 判断程序是否第一次运行
     *
     * @return
     */
    private boolean isFirst() {
        boolean isFirst = SPUtils.getBoolean(this, ConstantUtils.SHARE_IS_FIRST, true);
        if (isFirst) {
            SPUtils.putBoolean(this, ConstantUtils.SHARE_IS_FIRST, false);
            // 是第一次运行
            return true;
        } else {
            return false;
        }

    }
}
