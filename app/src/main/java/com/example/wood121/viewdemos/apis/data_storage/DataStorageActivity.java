package com.example.wood121.viewdemos.apis.data_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.util.ToastUtil;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStorageActivity extends AppCompatActivity {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_sp_put)
    Button tvSpPut;
    @BindView(R.id.tv_sp_get)
    Button tvSpGet;
    @BindView(R.id.tv_file)
    Button tvFile;
    @BindView(R.id.tv_SQLite)
    Button tvSQLite;
    @BindView(R.id.tv_ContentProvider)
    Button tvContentProvider;
    @BindView(R.id.tv_internet)
    Button tvInternet;
    @BindView(R.id.tv_disklrucache)
    Button tvDisklrucache;
    @BindView(R.id.tv_lrucache)
    Button tvLrucache;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private SharedPreferences sp;
    private SharedPreferences sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        ButterKnife.bind(this);

        //获取sp对象
        sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        sp2 = getSharedPreferences("User2", Context.MODE_PRIVATE);
    }

    private void spGet() {
        String name = sp.getString("name", "defautlName");
        String input = sp.getString("inputkey", "defautlInput");

        //证明是不同文件
        String name2 = sp.getString("name2", "defautlName2");
        String input2 = sp.getString("inputkey2", "defautlInput2");

//        String name2 = sp2.getString("name2", "defautlName2");
//        String input2 = sp2.getString("inputkey2", "defautlInput2");
        tvShow.setText("defaultName: " + name + " ;getInputString: " + input + "\nname2: " + name2 + " ;input2: " + input2);
    }

    /**
     * SharedPreference：
     * 存储的内容：保存和检索的任何基本数据类型（ boolean, float, int, long, string,set,map  ）的持久键-值对
     * 存储的位置："data/data/程序包名/shared_prefs"目录下
     * 应用场景：通常用来存储程序的一些配置信息；
     * 原理类：xml 处理时Dalvik会通过自带底层的本地XML Parser解析，比如XMLpull方式，这样对于内存资源占用比较好
     */
    private void spPut() {
        String input = etInput.getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", "user");
        editor.putString("inputkey", input);
        editor.apply();

        SharedPreferences.Editor editor2 = sp2.edit();
        editor2.putString("name2", "user2");
        editor2.putString("inputkey2", input + "2");
        editor2.apply();

        ToastUtil.showToast(this, "存储成功");
    }

    @OnClick({R.id.tv_sp_put, R.id.tv_sp_get, R.id.tv_file, R.id.tv_SQLite, R.id.tv_ContentProvider, R.id.tv_internet, R.id.tv_disklrucache, R.id.tv_lrucache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sp_put:
                spPut();
                break;
            case R.id.tv_sp_get:
                spGet();
                break;
            case R.id.tv_file:
                startActivity(new Intent(this, FileStorageActivity.class));
                break;
            case R.id.tv_SQLite:
                break;
            case R.id.tv_ContentProvider:
                break;
            case R.id.tv_internet:
                break;
            case R.id.tv_disklrucache:
                break;
            case R.id.tv_lrucache:
                break;
        }
    }
}
