package com.etsn05.grupp2.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Connection.ConnectionHandler;
import Model.DeviceModel;
import Model.LightbulbModel;
import Model.SensorModel;

public class DeviceActivity extends AppCompatActivity {
    private ArrayList<DeviceModel> devices;
    private DeviceModel selected;
    private ConnectionHandler ch;
    private ArrayAdapter<DeviceModel> adapter;
    private AlphaAnimation buttonClick=new AlphaAnimation(1F,0.8F);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Button b=(Button) findViewById(R.id.ButtonControlDevice);
        b.setClickable(false);


        devices=new ArrayList<DeviceModel>();

        adapter=new ArrayAdapter<DeviceModel>(this,R.layout.list_device,devices);
        ListView listView=(ListView) findViewById(R.id.DeviceList);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selected=adapter.getItem(position);
                Button b=(Button) findViewById(R.id.ButtonControlDevice);
                b.setClickable(true);
            }
        });

    }


    /** Called when the user clicks the Get Device button */
    public void onClickGetDevices(View view) {
        //ch=new ConnectionHandler();
        //System.out.println(ch.execute());
        devices.add(new SensorModel("1","name1","ip1","on"));
        devices.add(new SensorModel("2","name2","ip2","off"));
        devices.add(new LightbulbModel("3","name3","ip3","on"));
        devices.add(new SensorModel("4","name4","ip4","off"));
        devices.add(new SensorModel("5","name4","ip4","off"));
        devices.add(new LightbulbModel("6","name4","ip4","off"));
        devices.add(new SensorModel("7","name4","ip4","off"));
        devices.add(new SensorModel("8","name4","ip4","off"));
        devices.add(new SensorModel("9","name4","ip4","off"));
        devices.add(new LightbulbModel("10","name4","ip4","off"));
        adapter.notifyDataSetChanged();
        view.startAnimation(buttonClick);

    }

    /** Called when the user clicks Control Device */
    public void onClickControlDevice(View view) {
        view.startAnimation(buttonClick);
        if(selected instanceof SensorModel) {
            Intent intent = new Intent(this, SensorActivity.class);
            intent.putExtra("DeviceModel", selected);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LightBulbActivity.class);
            intent.putExtra("DeviceModel", selected);
            startActivity(intent);
        }

    }

    /* when list is pushed*/
    public void onSelected(ListView l, View v, int position, long id){
        DeviceModel selectedValue = adapter.getItem(position);
        System.out.println(selectedValue);
    }

    public void onBackPressed(){

    }
}
