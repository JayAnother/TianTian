package jay.love.tiantian.ui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.GlobalData;
import jay.love.tiantian.R;
import jay.love.tiantian.application.App;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.ui.base.BasePresenter;

import static jay.love.tiantian.R.id.levelChooseButton;

public class SettingActivity extends BaseActivity {
    @BindView(levelChooseButton)
    Button mLevelChooseButton;
    @BindView(R.id.buttonShare)
    ImageButton mButtonShare;

    private int choosedLevel = 0;

    final int[] levels = {
            R.string.setting_level_3,
            R.string.setting_level_4,
            R.string.setting_level_5,
            R.string.setting_level_6};

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolBar();
        mLevelChooseButton.setText(getString(levels[App.getLevel() - 3]));

    }

    private void initToolBar() {
        setTitle("设置");
        setLeftMenu(R.drawable.draw_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void chooseLevel() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

        final String[] levels = {
                getString(R.string.setting_level_3),
                getString(R.string.setting_level_4),
                getString(R.string.setting_level_5),
                getString(R.string.setting_level_6)};

        builder.setTitle("请选择难度");
        int defaultWhich = 0;

        for (int i = 0; i < levels.length; i++) {
            if (mLevelChooseButton.getText().toString().equals(levels[i])) {
                defaultWhich = i;
            }
        }

        builder.setSingleChoiceItems(levels, defaultWhich,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choosedLevel = which;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mLevelChooseButton.setText(levels[choosedLevel]);
                changeLevel();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void changeLevel() {

        int level = choosedLevel + 3;
        SharedPreferences.Editor editor = getSharedPreferences(GlobalData.SP_NAME, MODE_PRIVATE).edit();
        editor.putInt(GlobalData.SP_LEVEL, level);
        editor.apply();
        App.setLevel(level);
    }

    private void shareAction() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.setting_share));
        shareIntent.setType("text/plain");

        //设置分享列表的标题，并且每次都显示分享列表
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }


    @OnClick({levelChooseButton, R.id.buttonShare})
    public void onClick(View view) {
        switch (view.getId()) {
            case levelChooseButton:
                chooseLevel();
                break;
            case R.id.buttonShare:
                shareAction();
                break;
        }
    }
}
