package jay.love.tiantian.ui.c.generalgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.c.generalgame.Utils.Utils;
import jay.love.tiantian.ui.c.generalgame.dialog.SelectImageDialog;
import jay.love.tiantian.ui.c.generalgame.dialog.SuccessDialog;
import jay.love.tiantian.ui.c.generalgame.game.PuzzleGame;
import jay.love.tiantian.ui.c.generalgame.ui.PuzzleLayout;

public class GeneralGameActivity extends AppCompatActivity implements PuzzleGame.GameStateListener {


    @BindView(R.id.ivSrcImg)
    ImageView mIvSrcImg;
    @BindView(R.id.modeSpinner)
    Spinner mModeSpinner;
    @BindView(R.id.tvLevel)
    TextView mTvLevel;
    @BindView(R.id.btnAddLevel)
    Button mBtnAddLevel;
    @BindView(R.id.btnReduceLevel)
    Button mBtnReduceLevel;

    private PuzzleGame puzzleGame;
    private SelectImageDialog selectImageDialog;
    private PuzzleLayout mPuzzleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_game);
        ButterKnife.bind(this);
        initView();
        initListener();
    }
    private void initView() {
        mPuzzleLayout = (PuzzleLayout) findViewById(R.id.puzzleLayout);
        puzzleGame = new PuzzleGame(GeneralGameActivity.this, mPuzzleLayout);
        mTvLevel.setText("难度等级：" + puzzleGame.getLevel());
        mIvSrcImg.setImageBitmap(Utils.readBitmap(getApplicationContext(), mPuzzleLayout.getRes(), 4));
    }

    private void initListener() {
        puzzleGame.addGameStateListener(this);

        mModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mPuzzleLayout.changeMode(PuzzleLayout.GAME_MODE_NORMAL);
                } else {
                    mPuzzleLayout.changeMode(PuzzleLayout.GAME_MODE_EXCHANGE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (selectImageDialog == null) {
            selectImageDialog = new SelectImageDialog();
            selectImageDialog.addItemClickListener(new SelectImageDialog.OnItemClickListener() {
                @Override
                public void itemClick(int postion, int res) {
                    //更新布局
                    puzzleGame.changeRes(res);
                    mIvSrcImg.setImageBitmap(Utils.readBitmap(getApplicationContext(), res, 4));
                }
            });
        }

    }

    public void addLevel() {
        puzzleGame.addLevel();
    }

    public void reduceLevel() {
        puzzleGame.reduceLevel();
    }

    public void changeImage() {
        selectImageDialog.showDialog(getFragmentManager(), "dialog", 0);
    }

    @OnClick({R.id.ivSrcImg,R.id.btnAddLevel, R.id.btnReduceLevel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivSrcImg:
                changeImage();
                break;
            case R.id.btnAddLevel:
                addLevel();
                break;
            case R.id.btnReduceLevel:
                reduceLevel();
                break;
        }
    }

    @Override
    public void setLevel(int level) {
        mTvLevel.setText("难度等级：" + level);
    }

    @Override
    public void gameSuccess(int level) {
        final SuccessDialog successDialog = new SuccessDialog();
        successDialog.show(getFragmentManager(), "successDialog");
        successDialog.addButtonClickListener(new SuccessDialog.OnButtonClickListener() {
            @Override
            public void nextLevelClick() {
                puzzleGame.addLevel();
                successDialog.dismiss();
            }

            @Override
            public void cancelClick() {
                successDialog.dismiss();
            }
        });
    }
}
