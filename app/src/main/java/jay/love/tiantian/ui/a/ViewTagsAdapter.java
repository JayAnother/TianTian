package jay.love.tiantian.ui.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import jay.love.tiantian.R;

/**
 * Created by moxun on 16/3/4.
 */
public class ViewTagsAdapter extends TagsAdapter {
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(final Context context, int position, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.tag_item_view, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"haha",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getPopularity(int position) {
        return position % 5;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.findViewById(R.id.android_eye).setBackgroundColor(themeColor);
    }
}
