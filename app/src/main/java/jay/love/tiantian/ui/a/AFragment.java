package jay.love.tiantian.ui.a;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.listener.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.main_view)
    IRecyclerView mRecyclerView;
    TagCloudView mTagCloud;
    Button mTagText;
    Button mTagView;
    Button mTagVector;
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
        mRecyclerView.setAdapter(adapter);
    }

    private void addHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_conmunity_list, null);
        mTagCloud = (TagCloudView) headerView.findViewById(R.id.tag_cloud);
        mTagText = (Button) headerView.findViewById(R.id.tag_text);
        mTagView = (Button) headerView.findViewById(R.id.tag_text);
        mTagVector = (Button) headerView.findViewById(R.id.tag_text);
        mTagText.setOnClickListener(this);
        mTagView.setOnClickListener(this);
        mTagVector.setOnClickListener(this);
        mTagCloud.setAdapter(textTagsAdapter);
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
        switch (view.getId()) {
            case R.id.tag_text:
                mTagCloud.setAdapter(textTagsAdapter);
                break;
            case R.id.tag_view:
                mTagCloud.setAdapter(viewTagsAdapter);
                break;
            case R.id.tag_vector:
                mTagCloud.setAdapter(vectorTagsAdapter);
                break;
        }
    }
}
