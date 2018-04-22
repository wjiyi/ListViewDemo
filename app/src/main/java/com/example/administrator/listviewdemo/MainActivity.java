package com.example.administrator.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{

    private ListView listView;
    //private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        //String[] arr_data = {"慕课网1","慕课网2","慕课网3","慕课网4"};
        dataList = new ArrayList<Map<String,Object>>();

        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);

        simpleAdapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"pic","text"},new int[]{R.id.pic,R.id.text});

        //listView.setAdapter(arrayAdapter);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String,Object>> getData()
    {
        for (int i=0;i<20;i++)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","慕课网"+i);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String text = listView.getItemAtPosition(position)+"";
        Toast.makeText(this,"position="+position+" text="+text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState)
        {
            case SCROLL_STATE_FLING:
                Log.i("Main","用户在手指离开屏幕之前，由于用力滑了一下，视图仍依靠惯性继续滑动");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text","增加项");
                dataList.add(map);
                simpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main","手指没有离开屏幕，视图正在滑动");
                break;
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
