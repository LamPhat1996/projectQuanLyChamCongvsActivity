package tdc.edu.vn.projectquanlychamcongvsactivity;

import android.app.ProgressDialog;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import tdc.edu.vn.projectquanlychamcongvsactivity.Function.FunctionChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Function.Function_ChiTietChamCong;
import tdc.edu.vn.projectquanlychamcongvsactivity.Function.Function_CongNhan;
import tdc.edu.vn.projectquanlychamcongvsactivity.Function.Function_SanPham;
import tdc.edu.vn.projectquanlychamcongvsactivity.adapters.MyAdapter;
import tdc.edu.vn.projectquanlychamcongvsactivity.model.CardViewModel;
import tdc.edu.vn.projectquanlychamcongvsactivity.sensor.Myadmin;

//import tdc.edu.vn.projectquanlychamcongvsactivity.Function.Function_SanPham;

public class MainActivity extends AppCompatActivity {
    //khai bao sensor
    private SensorManager mSensorManager;
    private Sensor mSensor = null;
    private TextView tvSensorData;
    private DevicePolicyManager deviceManger;
    private ComponentName compName;
    //-------------
    //rycyclerView
    private Vector<CardViewModel> data = new Vector<CardViewModel>();
    RecyclerView recyclerView;
    private int position = 0;
    private boolean direction = true;
    //khai báo 1 cái mSensorListener để bắt sự kiện của sự kiện cảm biến máy gửi về liên tục
    private SensorEventListener mSensorListener = new SensorEventListener() {
        //   1  đọc dữ liệu
        @Override
        public void onSensorChanged(SensorEvent event) {
            //trả về tất cả các giá trị x y z ( tọa độ cảm biến của điện thoại )
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            float acceleSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            // kiểm tra chuyển màng hình nằm trong phần này
            if (acceleSquareRoot > 2)
            {
//                //lệnh đóng màng hình kiểm tra xem có quyền admin chưa
                boolean active = deviceManger.isAdminActive(compName);
                if (active) {
                    deviceManger.lockNow();
                } else {
                    Toast.makeText(MainActivity.this,"this admin", Toast.LENGTH_SHORT);
                }
            }
        }
        //tích lũy dữ liệu gửi về
        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setEvent();
        recyclerView();
        setmSensor();
    }
    private  void setEvent(){
        final Button buttonCN = findViewById(R.id.btn_congnhan);
        buttonCN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Function_CongNhan.class);
                startActivity(nextScreen);
            }
        });
        final Button buttonCTCC = findViewById(R.id.btn_ctchamCong);
        buttonCTCC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Function_ChiTietChamCong.class);
                startActivity(nextScreen);
            }
        });
        final Button buttonCC = findViewById(R.id.btn_chamCong);
        buttonCC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), FunctionChamCong.class);
                startActivity(nextScreen);
            }
        });
        final Button buttonSP = findViewById(R.id.btn_sanPham);
        buttonSP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Function_SanPham.class);
                startActivity(nextScreen);
            }
        });
        //ham dang xuat
        final Button buttonLogOut = findViewById(R.id.btn_Logout);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Đăng Xuất!");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable(){
                            @Override
                            public void run() {
                                Intent intent =new Intent(MainActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                startActivity(intent);
                            }
                        }
                        , 3000);
            }
        });
    }
    private void setmSensor(){
        deviceManger = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        compName = new ComponentName(this, Myadmin.class);
        //gửi tín hiệu tới admin
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
        startActivity(intent);
        //------------------------
        ArrayList sensorList = new ArrayList<String>();
        //đọc dữ liệu cảm biến ---------**_---------
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 2 lấy sensor ra để xử lý
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //đọc dữ liệu cảm biến ---------**_---------
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor theSensor : sensors) {
            sensorList.add(theSensor.getName() + " : " + theSensor.getStringType() + " : " + theSensor.getVendor());
        }
        //trong arraylist co du thong tin cua sensor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sensorList);
    }
    // 3  đăng ký trong onResume
    @Override
    protected void onResume() {
        super.onResume();
        if (mSensor != null) {
            // SENOR_DELAY_NORMAL thời gian áp dữ liệu
            mSensorManager.registerListener(mSensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(MainActivity.this, "this phone has no not the sensor", Toast.LENGTH_SHORT).show();
        }
    }
    //   4   hủy trong onStop
    @Override
    protected void onPause() {
        if (mSensor != null) {
            mSensorManager.unregisterListener(mSensorListener);
        } else {
            Toast.makeText(MainActivity.this, "this phone has no not the sensor", Toast.LENGTH_SHORT).show();
        }
        super.onPause();
    }
    private void recyclerView(){

        //initiation of data
        data.add(new CardViewModel(R.drawable.ao1));
        data.add(new CardViewModel(R.drawable.quan));
        data.add(new CardViewModel(R.drawable.quan2));
        data.add(new CardViewModel(R.drawable.tivi));
        data.add(new CardViewModel(R.drawable.giay));
        //setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        // bước 1 : thằng recycler phải có thằng layoutmanager quản lý
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
////        cho các hình nằm chung 1 dòng
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //chia cột cho nó
//        GridLayoutManager layoutManager1 = new  GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
         scrollByTime();
    }
    private void scrollByTime()
    {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(direction)
                {
                    position ++;
                    if(position>=data.size()) {
                        position --;
                        direction =false;
                    }
                }
                else {
                    position--;
                    if(position <=0)
                    {
                        direction = true;
                    }
                }
                recyclerView.smoothScrollToPosition(position);
                handler.postDelayed(this,3000);
            }
        });
    }
}
