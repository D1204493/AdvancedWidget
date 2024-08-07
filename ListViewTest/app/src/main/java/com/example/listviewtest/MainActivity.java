package com.example.listviewtest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        ListView lv = (ListView) findViewById(R.id.cityListView);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView tv = (TextView) findViewById(R.id.textView);
        String[] citiesArray = getResources().getStringArray(R.array.cities);

        //使用AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("城市");
        dialog.setMessage("您選擇的是：" + citiesArray[i]);
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

}