package jay.love.tiantian.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.common.utils.DialogUtils;

import jay.love.tiantian.R;

/**
 * Created by jay on 2017/5/10.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    public Activity mActivity;
    public T mPresenter;
    private Dialog mDialog;

    protected abstract T getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = this.getActivity();
    }

    public void showProgressDialog() {
        mDialog = DialogUtils.showProgressDialog(getContext());
    }

    public void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * NextActivity
     */
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
        getActivity().overridePendingTransition(R.anim.slide_in_right,
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

        getActivity().overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);

    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}

