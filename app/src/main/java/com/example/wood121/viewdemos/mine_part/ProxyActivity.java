package com.example.wood121.viewdemos.mine_part;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wood121.viewdemos.R;
import com.example.wood121.viewdemos.java.Operate;
import com.example.wood121.viewdemos.java.OperateImpl;
import com.example.wood121.viewdemos.java.TimingInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyActivity extends AppCompatActivity {

    private TextView mTvCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        mTvCreate = (TextView) findViewById(R.id.tv_create);
        mTvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                outerM();

                //Retrofit形式的动态代理使用方式
                Proxy.newProxyInstance(Operate.class.getClassLoader(), new Class[]{Operate.class}, new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        return null;
                    }
                });

            }
        });
    }

    private void outerM() {
        //创建代理对象
        TimingInvocationHandler timingInvocationHandler = new TimingInvocationHandler(new OperateImpl());
        Operate operate = (Operate) Proxy.newProxyInstance(Operate.class.getClassLoader(), new Class[]{Operate.class}, timingInvocationHandler);

        //调用代理对象的方法
        operate.OperateMethod1();
        System.out.println();
        operate.OperateMethod2();
        System.out.println();
        operate.OperateMethod3();
    }
}
