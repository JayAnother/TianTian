package jay.love.tiantian.ui.a;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.listener.OnItemClickListener;

/**
 * Created by jay on 2017/5/15.
 */

public class CommunityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public CommunityListAdapter(List<String> list, Context context, OnItemClickListener onItemClickListener) {
        mList = list;
        mContext = context;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_conmmunity_list_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if(position%2==0){
//            holder.mImageView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorAccent));
//        }else{
//            holder.mImageView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.themeBlue));
//        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_timeline_date)
        AppCompatTextView mTextTimelineDate;
        @BindView(R.id.text_timeline_title)
        AppCompatTextView mTextTimelineTitle;
        @BindView(R.id.image)
        ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
