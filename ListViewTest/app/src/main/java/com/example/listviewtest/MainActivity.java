package com.example.listviewtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView cityListView;  //宣告ListView
    private List<String> cities = new ArrayList<String>();  //建立 ArrayList 物件

    //第三節
    private class CityAdapter extends ArrayAdapter<City> {
        public CityAdapter(@NonNull Context context, ArrayList<City> cities) {
            super(context, 0, cities);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {   //position 第幾筆資料；convertView轉換的元件；parent父母
            // 找到哪一筆資料
            City city = getItem(position);
            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).
                        inflate(R.layout.city_information, parent, false);
            }
            // 將資料塞入設計好的樣式
            TextView tvName = (TextView) convertView.findViewById(R.id.cityName);
            TextView tvZipCode = (TextView) convertView.findViewById(R.id.zipCode);

            tvName.setText(city.name);
            tvZipCode.setText(String.valueOf(city.zipCode));

            return convertView;
        }

    }

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

        //第一節
//        ListView lv = (ListView) findViewById(R.id.cityListView);
//        lv.setOnItemClickListener(this);

        //第二節
        cityListView = (ListView) findViewById(R.id.cityListView);
        setCities();
        cityListView.setOnItemClickListener(this);
    }


    //第一節
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


    //第二節
    //橋接器(Adapter)方法
//    private void setCities() {
//        //先讀入原本六都的縣市
//        cities = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.cities)));
//        //新增縣市
//        cities.add("Hualien");
//        cities.add("Taitung");
//        cities.add("Changhua");
//        //new ArrayAdapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
//        cityListView.setAdapter(adapter);
//    }


    //第三節
    private void setCities() {
       ArrayList<City> cityList = new ArrayList<City>();
       cityList.add(new City("Taipei", 100));
       cityList.add(new City("New Taipei City", 207));
       cityList.add(new City("Taichung",407));
       CityAdapter adapter = new CityAdapter(this, cityList);
       cityListView.setAdapter(adapter);

    }


}