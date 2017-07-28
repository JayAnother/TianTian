package jay.love.tiantian.ui.a;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.listener.OnItemClickListener;
import jay.love.tiantian.ui.a.contact.AContact;
import jay.love.tiantian.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment<AContact.Presenter> implements AContact.View,View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.main_view)
    IRecyclerView mRecyclerView;
    TagCloudView mTagCloud;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeContainer;
    private TextTagsAdapter textTagsAdapter;
    private ViewTagsAdapter viewTagsAdapter;
    private VectorTagsAdapter vectorTagsAdapter;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mSwipeContainer.setOnRefreshListener(this);
        textTagsAdapter = new TextTagsAdapter(new String[20]);
        viewTagsAdapter = new ViewTagsAdapter();
        vectorTagsAdapter = new VectorTagsAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        CommunityListAdapter adapter = new CommunityListAdapter(getList(), getActivity(), new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        addHeaderView();
        mRecyclerView.setIAdapter(adapter);
    }

    private void addHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_conmunity_list, null);
        mTagCloud = (TagCloudView) headerView.findViewById(R.id.tag_cloud);
        mTagCloud.setAdapter(vectorTagsAdapter);
        mRecyclerView.addHeaderView(headerView);
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        return list;
    }

    public static Fragment newInstance() {
        return new AFragment();
    }

    public void onClick(View view) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        mSwipeContainer.setRefreshing(true);
        mSwipeContainer.setRefreshing(false);
    }

    @Override
    protected APresenter getPresenter() {
        return new APresenter();
    }
}

