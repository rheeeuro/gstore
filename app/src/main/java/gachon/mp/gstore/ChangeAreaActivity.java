package gachon.mp.gstore;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChangeAreaActivity extends AppCompatActivity {

    GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    String area, areaTown;
    String SIGUN = "";
    String DONG = "";
    TextView textView;
    ArrayAdapter<CharSequence> adspin1, adspin2;
    Button complete_btn;
    Button gps_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_area);

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission();
        }

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("지역 설정");
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);

        gps_btn = (Button) findViewById(R.id.gps_btn);
        complete_btn = (Button) findViewById(R.id.complete_btn);
        final Spinner spin1 = (Spinner)findViewById(R.id.spinnerArea);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinnerArea2);

        //adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, R.layout.spinneritem);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_do, R.layout.spinneritem);
        // adspin1.setDropDownViewResource(R.layout.spinneritem);
        adspin1.setDropDownViewResource(R.layout.spinneritem);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area=adspin1.getItem(position).toString();
                if (adspin1.getItem(position).equals("가평군")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Gapyoung, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("고양시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Goyang, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("과천시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Gwacheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            areaTown=adspin2.getItem(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if (adspin1.getItem(position).equals("광명시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Gwangmyeong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("광주시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Gwangju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("구리시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Guri, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("군포시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Gunpo, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("남양주시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Namyangju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("동두천시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Dongducheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("부천시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Bucheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("성남시 분당구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SeongNamBundang,R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("성남시 수정구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SeongNamSujeong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("성남시 중원구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SeongNamJungwon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("수원시 권선구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SuwonGwonseongu, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("수원시 영통구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SuwonYeongtong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("수원시 장안구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SuwonJangan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("수원시 팔달구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_SuwonPaldal, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("안산시 단원구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_AnsanDanwon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("안산시 상록구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_AnsanSanglok, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("안성시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Anseong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("안양시 동안구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_AnyangDongan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("안양시 만안구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_AnyangManan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("양주시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Yangju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("양평군")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Yangpeong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("연천군")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Yeonchon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("오산시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Osan, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("용인시 기흥구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_YonginGiheung, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("용인시 수지구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_YonginSuji, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                else if (adspin1.getItem(position).equals("용인시 처인구")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_YonginCheoin, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("의왕시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Uiwang, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("의정부시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Uijeongbu, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("이천시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Icheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("파주시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Paju, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("평택시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Pyeongtaek, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("포천시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Pocheon, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("하남시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Hanam, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
                else if (adspin1.getItem(position).equals("화성시")){
                    adspin2 = ArrayAdapter.createFromResource(ChangeAreaActivity.this, R.array.spinner_do_Hwaseong, R.layout.spinneritem);
                    adspin2.setDropDownViewResource(R.layout.spinneritem);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            areaTown=adspin2.getItem(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle mybundle = new Bundle();

                String[] areas = area.split(" ");
                if(areas.length == 2){
                    SIGUN = area.split(" ")[0];
                    DONG = area.split(" ")[1] + " " + areaTown;
                }else {
                    SIGUN = areas[0];
                    DONG = areaTown;
                }

                mybundle.putString("SIGUN", SIGUN);
                mybundle.putString("DONG", DONG);


                intent.putExtras(mybundle);
                startActivity(intent);
                finish();

            }
        });

        gps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gpsTracker = new GpsTracker(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Bundle mybundle = new Bundle();

                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();

                String address = getCurrentAddress(latitude, longitude);
                String split[] = address.split(" ");
                area = split[2];
                if(!split[3].substring(split[3].length()-1).equals("동")) {
                    area = area + " " + split[3];
                    if(split[4].substring(split[4].length()-1).equals("동")) {
                        areaTown = split[4];
                    }
                }
                else {
                    areaTown = split[3];
                }

                String[] areas = area.split(" ");
                if(areas.length == 2){
                    SIGUN = area.split(" ")[0];
                    DONG = area.split(" ")[1] + " " + areaTown;
                }else {
                    SIGUN = areas[0];
                    DONG = areaTown;
                }

                mybundle.putString("SIGUN", SIGUN);
                mybundle.putString("DONG", DONG);
                intent.putExtras(mybundle);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(getApplicationContext(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(getApplicationContext(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(getApplicationContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress(double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";

    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


}


