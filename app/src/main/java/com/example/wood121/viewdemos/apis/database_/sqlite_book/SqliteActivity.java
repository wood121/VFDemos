package com.example.wood121.viewdemos.apis.database_.sqlite_book;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.apis.database_.sqlite_book.bean.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int INSERT_ONE = 101;
    private static final int QUERY_ONE = 102;
    private ArrayList<Book> mList;
    private EditText mEtName;
    private EditText mEtAuthor;
    private EditText mEtPrice;
    private EditText mEtPages;
    private RecAdapter mRecAdapter;
    private EditText mEtInsertNumbers;
    private RadioButton mRbSingle;
    private RadioButton mRbWhole;
    private RadioGroup mRgWhether;
    private int checkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_book);

        mEtName = findViewById(R.id.et_sname);
        mEtAuthor = findViewById(R.id.et_sauthor);
        mEtPrice = findViewById(R.id.et_sprice);
        mEtPages = findViewById(R.id.et_spages);
        mEtInsertNumbers = findViewById(R.id.et_insert_numbers);

        findViewById(R.id.btn_sqliteadd).setOnClickListener(this);
        findViewById(R.id.btn_sqlitedelete).setOnClickListener(this);
        findViewById(R.id.btn_sqliteupdate).setOnClickListener(this);
        findViewById(R.id.btn_sqlitequery).setOnClickListener(this);
        findViewById(R.id.btn_bat_insert).setOnClickListener(this);

        mRbSingle = findViewById(R.id.rb_single);
        mRbWhole = findViewById(R.id.rb_whole);
        mRgWhether = findViewById(R.id.rg_whether);
        mRbSingle.setChecked(true);
        mRgWhether.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_single:
                        checkType = 0;
                        break;
                    case R.id.rb_whole:
                        checkType = 1;
                        break;
                }
            }
        });

        RecyclerView recView = findViewById(R.id.rec_sqlite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(linearLayoutManager);
        mRecAdapter = new RecAdapter();
        recView.setAdapter(mRecAdapter);

        mList = new ArrayList<>();
        getAllBooks();
        mRecAdapter.setData(mList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sqliteadd:
                add();
                break;
            case R.id.btn_sqlitedelete:
                if (checkType == 0) {
                    delete();
                } else if (checkType == 1) {
                    removeAll();
                }
                break;
            case R.id.btn_sqliteupdate:
                update();
                break;
            case R.id.btn_sqlitequery:
                query();
                break;
            case R.id.btn_bat_insert:
                batInsert();
                break;
        }
    }


    //批量插入数据
    private void batInsert() {
        String insertNumbers = mEtInsertNumbers.getText().toString().trim();

        String name = mEtName.getText().toString().trim();
        String author = mEtAuthor.getText().toString().trim();
        String pages = mEtPages.getText().toString().trim();
        String price = mEtPrice.getText().toString().trim();
        Book book = new Book(name, author, Integer.parseInt(pages), Double.parseDouble(price));

        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(insertNumbers); i++) {
            list.add(book);
        }

//        DBManager.getInstence(this).executeBatInsert(list);

        DBManager.getInstence(this).executeTranStateInsert(list);
        getAllBooks();
        mRecAdapter.setData(mList);
    }

    //查询某个字段
    private void query() {
        String bookname = mEtName.getText().toString().trim();
        List<Book> bookList = DBManager.getInstence(this).getBook(bookname);
        mList.clear();
        mList.addAll(bookList);
        mRecAdapter.setData(mList);
    }

    //修改数据
    private void update() {
        getAllBooks();
        mRecAdapter.setData(mList);
    }

    //删除一条
    private void delete() {
        String name = mEtName.getText().toString().trim();
        boolean deleteFlag = DBManager.getInstence(this).deleteBook(name);
        Log.e("wood", "deleteFlag:" + deleteFlag);
        getAllBooks();
        mRecAdapter.setData(mList);
    }

    //删除所有数据
    private void removeAll() {
        boolean removeAllFlag = DBManager.getInstence(this).removeAll();
        Log.e("wood", "deleteFlag:" + removeAllFlag);
        getAllBooks();
        mRecAdapter.setData(mList);
    }

    //插入一条数据
    private void add() {
        String name = mEtName.getText().toString().trim();
        String author = mEtAuthor.getText().toString().trim();
        String pages = mEtPages.getText().toString().trim();
        String price = mEtPrice.getText().toString().trim();
        Book book = new Book(name, author, Integer.parseInt(pages), Double.parseDouble(price));

        Book insertBook = DBManager.getInstence(this).insertBook(book);
        insertData(insertBook);
    }

    //将新插入的数据查询出来添加到列表
    private void insertData(Book insertBook) {
        mList.add(insertBook);
        mRecAdapter.setData(mList);
    }

    //查询表中所有数据
    private void getAllBooks() {
        mList.clear();
        mList.addAll(DBManager.getInstence(this).getAllBooks());
    }
}
