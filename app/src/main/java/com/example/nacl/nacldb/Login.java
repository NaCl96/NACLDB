package com.example.nacl.nacldb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText user;
    private EditText password;
    String[] name;
    String mypwd;


    public /*String[]*/ void query() {
        String address = "https://api.myjson.com/bins/e9afe";

        //jackson,Gson,fastjson,org.json
        try {
            String info= null;
            info = new Fetcher(address).fetch();

            JSONArray jsa=new JSONArray(info);
            for (int i = 0; i < jsa.length(); i++) {
                JSONObject jso=jsa.getJSONObject(i);
                Information u=new Information();
                u.setName(jso.getString("name"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        return jsa;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        //关联组件
        user = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.pass);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "你想提示的信息", Toast.LENGTH_SHORT).show();
//
//        while (true) {
            /*for (int i = 0; i < name.length; i++) {
                Toast.makeText(getApplicationContext(), "nihao", Toast.LENGTH_SHORT).show();*/
                if ((user.getText().toString().equals("杨幂"))) {
                    Toast.makeText(this, "用户验证成功", Toast.LENGTH_SHORT).show();

                    //intent bundle传值
                    Intent MainActivity = new Intent();
                    MainActivity.setClass(this, MainActivity.class);
                    Bundle bundle = new Bundle(); //该类用作携带数据
                    bundle.putString("user", user.getText().toString());
                    MainActivity.putExtras(bundle);   //向MainActivity传值
                    this.startActivity(MainActivity);
                    finish();//退出

                }else {
                    Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
//            }
//        }
    }
}