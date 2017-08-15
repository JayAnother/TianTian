package jay.love.tiantian.ui.home;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.common.utils.DialogUtils;
import com.rom4ek.arcnavigationview.ArcNavigationView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import jay.love.tiantian.R;
import jay.love.tiantian.ui.a.AFragment;
import jay.love.tiantian.ui.b.BFragment;
import jay.love.tiantian.ui.base.BaseActivity;
import jay.love.tiantian.ui.d.DFragment;
import jay.love.tiantian.ui.c.CFragment;
import jay.love.tiantian.utils.AnimationUtil;


public class MainActivity extends BaseActivity<MainContract.Presenter> implements
        MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    @BindView(R.id.nav_view)
    ArcNavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;

    private Fragment mAFragment;
    private Fragment mBFragment;
    private Fragment mCFragment;
    private Fragment mDFragment;
    public final static String FRAGMENT_TAG_A = "A";
    public final static String FRAGMENT_TAG_B = "B";
    public final static String FRAGMENT_TAG_C = "C";
    public final static String FRAGMENT_TAG_D = "D";
    private String currFragmentTag = "";

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initToolbar();
    }

    private void initToolbar() {
        final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
        indicator.setColor(Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(indicator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (mAFragment != null && !FRAGMENT_TAG_A.equals(currFragmentTag)) {
            fragmentTransaction.hide(mAFragment);
        }
        if (mBFragment != null && !FRAGMENT_TAG_B.equals(currFragmentTag)) {
            fragmentTransaction.hide(mBFragment);
        }
        if (mCFragment != null && !FRAGMENT_TAG_C.equals(currFragmentTag)) {
            fragmentTransaction.hide(mCFragment);
        }
        if (mDFragment != null && !FRAGMENT_TAG_D.equals(currFragmentTag)) {
            fragmentTransaction.hide(mDFragment);
        }
    }

    public String getCurrentFragmentTag() {
        return currFragmentTag;
    }

    public void switchFragment(String fragmentTag, int animType) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        switch (fragmentTag) {
            case FRAGMENT_TAG_A:
                if (mAFragment == null) {
                    mAFragment = AFragment.newInstance();
                }
                fragment = mAFragment;
                break;
            case FRAGMENT_TAG_B:
                if (mBFragment == null) {
                    mBFragment = BFragment.newInstance();
                }
                fragment = mBFragment;
                break;
            case FRAGMENT_TAG_D:
                if (mDFragment == null) {
                    mDFragment = DFragment.newInstance();
                }
                fragment = mDFragment;
                break;
            case FRAGMENT_TAG_C:
                if (mCFragment == null) {
                    mCFragment = CFragment.newInstance();
                }
                fragment = mCFragment;
                break;
        }
        //切换动画
        if (animType == AnimationUtil.TYPE_PAGE_IN) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (animType == AnimationUtil.TYPE_PAGE_OUT) {
            transaction.setCustomAnimations(R.anim.return_slide_in_left, R.anim.return_slide_out_right);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_container, fragment, fragmentTag);
        } else {
            transaction.show(fragment);
        }
        currFragmentTag = fragmentTag;
        transaction.commitAllowingStateLoss();
        hideAllFragment(transaction);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            DialogUtils.showCustomDialog(this, getResources().getString(R.string.quit_app),
                    getResources().getString(R.string.yes_upp),
                    getResources().getString(R.string.no_upp), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            MainActivity.super.onBackPressed();
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
        }
    }

    private void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_white_24dp));
        mDrawerLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });
        mNavView.setNavigationItemSelectedListener(this);

        //bottom bar
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
//                mTextMessage.setText(TabMessage.get(tabId, false));
                switch (tabId) {
                    case R.id.tab_favorites:
                        switchFragment(FRAGMENT_TAG_A, AnimationUtil.TYPE_PAGE_NONE);
                        break;
                    case R.id.tab_recents:
                        switchFragment(FRAGMENT_TAG_B, AnimationUtil.TYPE_PAGE_NONE);
                        break;
                    case R.id.tab_friends:
                        switchFragment(FRAGMENT_TAG_C, AnimationUtil.TYPE_PAGE_NONE);
                        break;
                    case R.id.tab_nearby:
                        switchFragment(FRAGMENT_TAG_D, AnimationUtil.TYPE_PAGE_NONE);
                        break;
                }
            }
        });

        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
//                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
