package jay.love.tiantian.ui.a;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import jay.love.tiantian.R;

/**
 * Created by moxun on 16/3/11.
 */
public class VectorTagsAdapter extends TagsAdapter {
    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.tag_item_vector, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position="+position, Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
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
        ImageView imageView = (ImageView) view.findViewById(R.id.vector_img);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(themeColor,
                PorterDuff.Mode.SRC_ATOP);
        if (imageView == null) {
            return;
        }
        imageView.getDrawable().setColorFilter(porterDuffColorFilter);
    }
}
