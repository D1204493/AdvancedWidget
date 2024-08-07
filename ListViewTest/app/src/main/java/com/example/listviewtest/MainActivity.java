package com.example.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView cityListView;
    private List<String> cities = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        ListView lv = (ListView) findViewById(R.id.cityListView);
//        lv.setOnItemClickListener(this);

        cityListView = (ListView) findViewById(R.id.cityListView);
        setCities();
        cityListView.setOnItemClickListener(this);
    }


    //覆寫 onItemClick 方法
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv = (TextView) findViewById(R.id.textView);
//        String[] citiesArray = getResources().getStringArray(R.array.cities);  //設橋接器(Adapter)出現錯誤訊息：ArrayIndexOutOfBoundsException。因為使用了 ArrayList<>(較為彈性)，所以這一行註解掉。

        //使用AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("城市");
        dialog.setMessage("您選擇的是：" + cities.get(i));  //設橋接器(Adapter)出現錯誤訊息：ArrayIndexOutOfBoundsException。把 citiesArray[i] 調整成 cities.get(i)
        dialog.setCancelable(true);
        //dialog.show();
        //也可以這樣使用：dialog.setTitle("城市").setMessage("您選擇的是：" + citiesArray[i]).setCancelable(true).show();
        dialog.setPositiveButton("確定", null);
        dialog.setNeutralButton("取消", null);
        dialog.setNegativeButton("返回", null);
        dialog.show();

        //使用Toast
//        Toast.makeText(this,
//                "您選擇的是：" + citiesArray[i],
//                Toast.LENGTH_SHORT).show();


        //使用TextView
//        tv.setText("您選擇的是：" + citiesArray[i]);
    }

    //橋接器(Adapter)方法
    private void setCities() {
        //先讀入原本六都的縣市
        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
        //新增縣市
        cities.add("Hualien");
        cities.add("Taitung");
        cities.add("Changhua");
        //new ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        cityListView.setAdapter(adapter);
    }


}