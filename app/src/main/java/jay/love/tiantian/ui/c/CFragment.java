package jay.love.tiantian.ui.c;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.base.BaseFragment;
import jay.love.tiantian.ui.base.BasePresenter;
import jay.love.tiantian.ui.c.generalgame.GeneralGameActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends BaseFragment {


    @BindView(R.id.btn01)
    Button mBtn01;
    @BindView(R.id.btn02)
    Button mBtn02;
    @BindView(R.id.btn03)
    Button mBtn03;

    public CFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static Fragment newInstance() {
        return new CFragment();
    }

    @OnClick({R.id.btn01, R.id.btn02, R.id.btn03})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn01:
                startNextActivity(getActivity(),null,null,SimpleGameActivity.class);
                break;
            case R.id.btn02:
                startNextActivity(getActivity(),null,null,GeneralGameActivity.class);
                break;
            case R.id.btn03:
                startNextActivity(getActivity(),null,null,ComplexGameStartActivity.class);
                break;
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
