package jay.love.tiantian.widget.fantasydrawerlayout;

import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by CaoDongping on 9/7/16.
 */

public class SimpleFantasyListener implements FantasyListener {
    @Override
    public boolean onHover(@Nullable View view, int index) {
        return false;
    }

    @Override
    public boolean onSelect(View view, int index) {
        return false;
    }

    @Override
    public void onCancel() {

    }
}
