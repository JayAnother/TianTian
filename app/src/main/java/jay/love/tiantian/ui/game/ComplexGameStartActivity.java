package jay.love.tiantian.ui.game;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.ui.base.BasePresenter;

public class ComplexGameStartActivity extends BaseActivity {

    @BindView(R.id.beginButton)
    ImageButton mBeginButton;
    @BindView(R.id.rankButton)
    ImageButton mRankButton;
    @BindView(R.id.settingButton)
    ImageButton mSettingButton;
    @BindView(R.id.exitButton)
    ImageButton mExitButton;
    @BindView(R.id.buttonGroup)
    LinearLayout mButtonGroup;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.beginButton, R.id.rankButton, R.id.settingButton, R.id.exitButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beginButton:
                startNextActivity(this,null,null,ChoosePicActivity.class);
                break;
            case R.id.rankButton:
                startNextActivity(this,null,null,RankActivity.class);
                break;
            case R.id.settingButton:
                startNextActivity(this,null,null,SettingActivity.class);
                break;
            case R.id.exitButton:
                break;
        }
    }
}
