package jay.love.tiantian.ui.b.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.library.bubbleview.BubbleTextVew;

import java.util.List;

import jay.love.tiantian.GlobalData;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.b.model.TLMessageEntity;
import jay.love.tiantian.utils.SpecialViewUtil;
import jay.love.tiantian.utils.TimeUtil;

public class ChatMessageAdapter extends TLBaseListAdapter<TLMessageEntity> {

    private Context mContext;

    public static final int TYPE_LEFT = 0;
    public static final int TYPE_RIGHT = 1;

    public ChatMessageAdapter(Context context, List<TLMessageEntity> list) {
        super(context, list);
        mContext = context;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getType() == TYPE_LEFT) {
            return TYPE_LEFT;
        }
        return TYPE_RIGHT;
    }

    private View createViewByType(int position) {
        if (getItem(position).getType() == TYPE_LEFT) {
            return mInflater.inflate(R.layout.item_conversation_left, null);
        }
        return mInflater.inflate(R.layout.item_conversation_right, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createViewByType(position);
        }

        final TLMessageEntity entity = getItem(position);

        TextView tvTime = ViewHolder.get(convertView, R.id.tv_time);
        BubbleTextVew btvMessage = ViewHolder.get(convertView, R.id.btv_message);

        if (isDisplayTime(position)) {
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(TimeUtil.friendlyTime(mContext, entity.getTime()));
        } else {
            tvTime.setVisibility(View.GONE);
        }

        switch (entity.getCode()) {
            case GlobalData.TuringCode.URL:
                btvMessage.setText(SpecialViewUtil.getSpannableString(entity.getText(), entity.getUrl()));
                break;
            case GlobalData.TuringCode.NEWS:
                btvMessage.setText(SpecialViewUtil.getSpannableString(entity.getText(), "点击查看"));
                break;
            default:
                btvMessage.setText(entity.getText());
                break;
        }

        btvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (entity.getCode()) {
                    case GlobalData.TuringCode.URL:
                        gotoDetailActivity(mContext, entity.getUrl());
                        break;
                    case GlobalData.TuringCode.NEWS:
                        gotoNewsActivity(mContext, entity);
                        break;
                }
            }
        });
        btvMessage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                copyDeleteDialog(mContext, entity);
                return false;
            }
        });
        return convertView;
    }
    public static void gotoNewsActivity(Context context, TLMessageEntity messageEntity) {
//        Intent intent = new Intent(context, NewsActivity.class);
//        intent.putExtra("messageEntity", messageEntity);
//        context.startActivity(intent);
    }

    public static void gotoDetailActivity(Context context, String url) {
//        Intent intent = new Intent(context, DetailActivity.class);
//        intent.putExtra("url", url);
//        context.startActivity(intent);
    }
    //  一分钟内的请求与回复不显示时间
    public boolean isDisplayTime(int position) {
        if (position > 0) {
            if ((getItem(position).getTime() - getItem(position-1).getTime()) > 60 * 1000) {
                return true;
            } else {
                return false;
            }
        } else if (position == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void copyDeleteDialog(final Context context, final TLMessageEntity entity) {
//        new MaterialDialog.Builder(context)
//                .items("复制该文本", "删除这一条")
//                .itemsCallback(new MaterialDialog.ListCallback() {
//                    @Override
//                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
//                        switch (position) {
//                            case 0:
//                                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//                                cm.setText(entity.getText());
//                                Toast.makeText(context, "已复制", Toast.LENGTH_SHORT).show();
//                                break;
//                            case 1:
//                                getData().remove(entity);
//                                notifyDataSetChanged();
//                                break;
//                        }
//                    }
//                })
//                .show();
    }

}
