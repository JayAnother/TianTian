package jay.love.tiantian.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.utils.DialogUtils;
import com.common.utils.LogUtils;

import jay.love.tiantian.R;

/**
 * Created by jay on 2017/5/5.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    public T mPresenter;
    private Toolbar mToolbar;
    private Dialog mDialog;


    public void showProgressDialog() {
        mDialog = DialogUtils.showProgressDialog(this);
    }

    public void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected abstract T getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set status bar color
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black000000));
//        }
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        LogUtils.e("class", "run:--------->当前类名: " + getClass().getSimpleName());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            //JViewUtil.setStatuBarColorId(this,R.color.transparent_status);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //所有activity退出都是左进右出
        overridePendingTransition(R.anim.return_slide_in_left, R.anim.return_slide_out_right);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);//默认标题栏不可用
            getToolbar().findViewById(R.id.tv_toolbar_title).setVisibility(View.GONE);
        } else {
            //默认title
//            getToolbar().setTitle(title);
//            getToolbar().setTitleTextColor(JToolUtil.getColor(R.color.black221e1f));
            TextView tvTitle = (TextView) getToolbar().findViewById(R.id.tv_toolbar_title);
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
    }

    public void setLeftMenu(int icon, View.OnClickListener onClickListener) {
        ImageButton imageButton = (ImageButton) getToolbar().findViewById(R.id.button_left_menu);
        imageButton.setImageDrawable(ContextCompat.getDrawable(this, icon));
        if (onClickListener != null) {
            imageButton.setOnClickListener(onClickListener);
        }
    }

    public void startNextActivity(Activity activity, Fragment fragemnt, Bundle bundle, Class<?> pClass) {
        Intent intent = new Intent(activity, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (fragemnt != null) {
            fragemnt.startActivity(intent);
        } else {
            activity.startActivity(intent);
        }
        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }

    public void startNextActivityForResult(Activity activity, Fragment fragemnt, Bundle bundle, Class<?> pClass, int requestCode) {
        Intent intent = new Intent(activity, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (fragemnt != null) {
            fragemnt.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }

        overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
