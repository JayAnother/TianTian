package jay.love.tiantian.ui.home;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private TextView mTextMessage;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initView();
        initDrawerLayout();

    }

    private void initDrawerLayout() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//                mDrawerLayout.closeDrawer(GravityCompat.START);
//            } else {
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        }
        return true;
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
        indicator.setColor(Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(indicator);
//        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
//        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                if (((ViewGroup) drawerView).getChildAt(1).getId() == R.id.leftSideBar) {
//                    indicator.setProgress(slideOffset);
//                }
//            }
//        });


        mTextMessage = (TextView) findViewById(R.id.message);
        //vector animation view
        mImageView = (ImageView) findViewById(R.id.anim_path);
        if (mImageView.getDrawable() instanceof Animatable) {
            ((Animatable) mImageView.getDrawable()).start();
        }
        //bottom bar
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                mTextMessage.setText(TabMessage.get(tabId, false));
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });
    }

}
