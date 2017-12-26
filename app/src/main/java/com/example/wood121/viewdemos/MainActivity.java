package com.example.wood121.viewdemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wood121.viewdemos.fragment.APIViewFragment;
import com.example.wood121.viewdemos.fragment.FrameSDKFragment;
import com.example.wood121.viewdemos.fragment.MineFragment;
import com.example.wood121.viewdemos.fragment.MathJniFragment;
import com.example.wood121.viewdemos.widget.TopBar;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tb_view)
    TopBar tbView;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.view_divider)
    View viewDivider;
    @BindView(R.id.tab_rb_home)
    RadioButton tabRbHome;
    @BindView(R.id.tab_rb_shop)
    RadioButton tabRbShop;
    @BindView(R.id.tab_rb_neighbor)
    RadioButton tabRbNeighbor;
    @BindView(R.id.tab_rb_user)
    RadioButton tabRbUser;
    @BindView(R.id.tab_rg_menu)
    RadioGroup tabRgMenu;

    private ArrayList<Fragment> fragments;
    private APIViewFragment apiViewFragment;
    private FrameSDKFragment frameSDKFragment;
    private MineFragment mineFragment;
    private MathJniFragment sdaFragment;
    private FragmentManager supportFragmentManager;

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPageViewListener() {
        fragments = new ArrayList<>();
        addFragments();
        tabRbHome.setChecked(true);
        slectFragment(0);
        tbView.setTitle("APIView");

        tabRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_rb_home:
                        tbView.setTitle("APIView");
                        slectFragment(0);
                        break;
                    case R.id.tab_rb_shop:
                        tbView.setTitle("FSDK");
                        slectFragment(1);
                        break;
                    case R.id.tab_rb_neighbor:
                        tbView.setTitle("SDA");
                        slectFragment(2);
                        break;
                    case R.id.tab_rb_user:
                        tbView.setTitle("Mine");
                        slectFragment(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragments(savedInstanceState);
    }

    /**
     * fragments的意外情况处理
     *
     * @param savedInstanceState
     */
    private void initFragments(Bundle savedInstanceState) {
        supportFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            apiViewFragment = (APIViewFragment) supportFragmentManager.getFragment(savedInstanceState, "apiViewFragment");
            frameSDKFragment = (FrameSDKFragment) supportFragmentManager.getFragment(savedInstanceState, "frameSDKFragment");
            sdaFragment = (MathJniFragment) supportFragmentManager.getFragment(savedInstanceState, "sdaFragment");
            mineFragment = (MineFragment) supportFragmentManager.getFragment(savedInstanceState, "mineFragment");
        } else {
            apiViewFragment = APIViewFragment.newInstance();
            frameSDKFragment = FrameSDKFragment.newInstance();
            sdaFragment = MathJniFragment.newInstance();
            mineFragment = MineFragment.newInstance();
        }
    }

    private void addFragments() {
        fragments.add(apiViewFragment);
        fragments.add(frameSDKFragment);
        fragments.add(sdaFragment);
        fragments.add(mineFragment);
    }

    private void slectFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                if (i == position) {// 显示fragment
                    if (fragment.isAdded()) {
                        fragmentTransaction.show(fragment);
                    } else {
                        fragmentTransaction.add(R.id.fragment_container, fragment);
                    }
                } else {// 隐藏fragment
                    if (fragment.isAdded()) {
                        fragmentTransaction.hide(fragment);
                    }
                }

//                if (i == position) {
//                    fragmentTransaction.replace(R.id.fragment_container, fragment);
//                    break;
//                }
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        supportFragmentManager.putFragment(outState, "apiViewFragment", apiViewFragment);
//        supportFragmentManager.putFragment(outState, "frameSDKFragment", frameSDKFragment);
//        supportFragmentManager.putFragment(outState, "mineFragment", mineFragment);
//        supportFragmentManager.putFragment(outState, "sdaFragment", sdaFragment);
    }

    //处理好Activity中fragment跳出去，回来的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                fragments.get(0).onActivityResult(requestCode, resultCode, data);
                break;
            case 1:
                fragments.get(1).onActivityResult(requestCode, resultCode, data);
                break;
            case 2:
                fragments.get(2).onActivityResult(requestCode, resultCode, data);
                break;
            case 3:
                fragments.get(3).onActivityResult(requestCode, resultCode, data);
                break;
            default:
                break;
        }
    }
}
