package jay.love.tiantian.ui.c;

import android.os.Bundle;
import android.view.View;

import jay.love.tiantian.R;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.ui.base.BasePresenter;

public class RankActivity extends BaseActivity {

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initToolBar();
    }
    private void initToolBar() {
        setTitle("发现");
        setLeftMenu(R.drawable.draw_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
