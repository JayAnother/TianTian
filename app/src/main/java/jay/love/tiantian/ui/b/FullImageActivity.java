package jay.love.tiantian.ui.b;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import jay.love.tiantian.R;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private void activityExitAnim(Runnable runnable) {
//        fullImage.setPivotX(0);
//        fullImage.setPivotY(0);
//        fullImage.animate().scaleX(mScaleX).scaleY(mScaleY).translationX(mLeft).translationY(mTop).
//                withEndAction(runnable).
//                setDuration(500).setInterpolator(new DecelerateInterpolator()).start();
//        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBackground, "alpha", 255, 0);
//        objectAnimator.setInterpolator(new DecelerateInterpolator());
//        objectAnimator.setDuration(500);
//        objectAnimator.start();
//    }
    @Override
    public void onBackPressed() {
//        activityExitAnim(new Runnable() {
//            @Override
//            public void run() {
                finish();
                overridePendingTransition(0, 0);
//            }
//        });
    }
    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
