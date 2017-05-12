package jay.love.tiantian.ui.a;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.moxun.tagcloudlib.view.TagCloudView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jay.love.tiantian.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {


    @BindView(R.id.tag_cloud)
    TagCloudView mTagCloud;
    @BindView(R.id.tag_text)
    Button mTagText;
    @BindView(R.id.tag_view)
    Button mTagView;
    @BindView(R.id.tag_vector)
    Button mTagVector;
    @BindView(R.id.main_view)
    RecyclerView mRecyclerView;
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
        mTagCloud.setBackgroundColor(Color.LTGRAY);
        textTagsAdapter = new TextTagsAdapter(new String[20]);
        viewTagsAdapter = new ViewTagsAdapter();
        vectorTagsAdapter = new VectorTagsAdapter();
        mTagCloud.setAdapter(textTagsAdapter);
    }

    public static Fragment newInstance() {
        return new AFragment();
    }

    @OnClick({R.id.tag_text, R.id.tag_view, R.id.tag_vector})
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
