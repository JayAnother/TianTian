package jay.love.tiantian.ui.game;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.ui.base.BasePresenter;
import jay.love.tiantian.utils.BitmapUtils;

public class ChoosePicActivity extends BaseActivity {

    public static final int[] icons = {
            R.drawable.t_01,
            R.drawable.t_01,
            R.drawable.t_01,
            R.drawable.t_01,
            R.drawable.t_01,
            R.drawable.t_01
    };
    public static final String INTENT_PIC_INDEX = "pic_index";
    @BindView(R.id.picContainer)
    GridView mPicContainer;

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initView() {
        mPicContainer.setAdapter(new ChoosePicGridViewAdapter(ChoosePicActivity.this, R.layout.choose_pic_gridview_item));
        mPicContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt(INTENT_PIC_INDEX,position);
                startNextActivity(ChoosePicActivity.this,null,bundle,ComplexGameActivity.class);
            }
        });
    }

    private void initToolBar() {
        setTitle("选择关卡");
        setLeftMenu(R.drawable.draw_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    class ChoosePicGridViewAdapter extends ArrayAdapter<Integer> {

        int resourceId;

        public ChoosePicGridViewAdapter(Context context, int resourceId) {
            super(context, resourceId);
            this.resourceId = resourceId;
        }

        @Override
        public int getCount() {
            return ChoosePicActivity.icons.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, null);
                holder = new ViewHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.imageItem);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            holder.imageView.setImageBitmap(BitmapUtils.decodeSampledBitmapFromResources(
                    getContext().getResources(),
                    ChoosePicActivity.icons[position],
                    300,
                    300
            ));

            return view;
        }

        class ViewHolder {
            ImageView imageView;
        }
    }
}
