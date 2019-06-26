package com.example.wood121.viewdemos.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.views.widgets_self.topbar.TopBar;

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

    @Override
    protected int initPageLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FragmentTabManager.getInstance(this).initFragments();
    }

    @Override
    protected void initEvent() {
        tabRbHome.setChecked(true);
        FragmentTabManager.getInstance(this).slectFragment(FragmentTabManager.API_VIEW_FRAGMENT);
        tbView.setTitle("APIView");
        tabRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_rb_home:
                        tbView.setTitle("APIView");
                        FragmentTabManager.getInstance(MainActivity.this).slectFragment(FragmentTabManager.API_VIEW_FRAGMENT);
                        break;
                    case R.id.tab_rb_shop:
                        tbView.setTitle("FSDK");
                        FragmentTabManager.getInstance(MainActivity.this).slectFragment(FragmentTabManager.FRAME_SDK_FRAGMENT);
                        break;
                    case R.id.tab_rb_neighbor:
                        tbView.setTitle("SDA");
                        FragmentTabManager.getInstance(MainActivity.this).slectFragment(FragmentTabManager.MATH_JNI_FRAGMENT);
                        break;
                    case R.id.tab_rb_user:
                        tbView.setTitle("Mine");
                        FragmentTabManager.getInstance(MainActivity.this).slectFragment(FragmentTabManager.MINE_FRAGMENT);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //处理好Activity中fragment跳出去，回来的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                FragmentTabManager
                        .getInstance(this)
                        .getFragment(FragmentTabManager.API_VIEW_FRAGMENT)
                        .onActivityResult(requestCode, resultCode, data);
                break;
            case 1:
                FragmentTabManager
                        .getInstance(this)
                        .getFragment(FragmentTabManager.FRAME_SDK_FRAGMENT)
                        .onActivityResult(requestCode, resultCode, data);
                break;
            case 2:
                FragmentTabManager
                        .getInstance(this)
                        .getFragment(FragmentTabManager.MATH_JNI_FRAGMENT)
                        .onActivityResult(requestCode, resultCode, data);
                break;
            case 3:
                FragmentTabManager
                        .getInstance(this)
                        .getFragment(FragmentTabManager.MINE_FRAGMENT)
                        .onActivityResult(requestCode, resultCode, data);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        FragmentTabManager.getInstance(this).onDestroy();
        super.onDestroy();
    }
}
