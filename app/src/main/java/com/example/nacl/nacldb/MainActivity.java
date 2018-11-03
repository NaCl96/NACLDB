package com.example.nacl.nacldb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Message;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Handler handler;
    private InformationAdapter adapter;
    private ListView lv;
    private  List<Information> in = new ArrayList<Information>();
    //列表的索引
    private int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        adapter = new InformationAdapter(this,R.layout.information,in);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1){
                    adapter.notifyDataSetChanged();
                }
            }
        };
        query();
    }

//    public void add(View view) {
//        String n1 = edit1.getText().toString();
//        String e1 = edit2.getText().toString();
//        Information information2 = new Information();
//        information2.setName(n1);
//        information2.setEnterprise(e1);
//        in.add(information2);
//        String sql = "insert into information (name,enterprise) values(?,?)";
//        db.execSQL(sql,new Object[]{n1,e1});
//
//    }

    public void query() {
        new Thread(){
            @Override
            public void run() {
                String address = "https://api.myjson.com/bins/11qmiy";

                //jackson,Gson,fastjson,org.json
                try {
                    String info= null;
                    info = new Fetcher(address).fetch();

                    JSONArray jsa=new JSONArray(info);
                    for (int i = 0; i < jsa.length(); i++) {
                        JSONObject jso=jsa.getJSONObject(i);
                        Information u=new Information();
                        u.setName(jso.getString("name"));
                        in.add(u);
                        handler.sendEmptyMessage(1);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currIndex = position;
        Intent intent = new Intent();
        intent.setClass(this,Login.class);
        this.startActivity(intent);
        finish();
    }
}
