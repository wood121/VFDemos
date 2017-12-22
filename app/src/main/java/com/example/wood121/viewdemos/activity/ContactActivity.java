package com.example.wood121.viewdemos.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.adapter.ContactAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.lv_list)
    ListView lvList;
    private Cursor phoneCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        //控件监听事件
        initListeners();
        //获取号码数据
        getContacts();
    }

    private void getContacts() {
        ContentResolver resolver = getContentResolver();
        String[] phoneProjection = {
                "_id",
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
        phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, phoneProjection, null, null, null);

        ContactAdapter contactAdapter = new ContactAdapter(this, phoneCursor);
        lvList.setAdapter(contactAdapter);
    }

    private void initListeners() {
        //点击号码拨打电话
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                CursorWrapper cursorWrapper = (CursorWrapper) lvList.getItemAtPosition(position - 1);
                //进行查找、寻找列名
                int columnIndex = cursorWrapper.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                if (!cursorWrapper.isNull(columnIndex)) {
                    String number = cursorWrapper.getString(columnIndex);
                    if (PhoneNumberUtils.isGlobalPhoneNumber(number)) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" + number));
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        phoneCursor.close();
    }
}
