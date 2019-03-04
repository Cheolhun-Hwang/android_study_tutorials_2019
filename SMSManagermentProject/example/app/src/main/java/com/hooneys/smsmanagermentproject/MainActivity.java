package com.hooneys.smsmanagermentproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private TextView sender, receivedDate, contents;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(broadcastReceiver, new IntentFilter("SmsMessage.intent.MAIN"));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);

        super.onDestroy();
    }

    private void init(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s_sender = intent.getStringExtra("send");
                String s_contents = intent.getStringExtra("contents");
                String s_date = intent.getStringExtra("date");
                modifySMSUI(s_sender, s_date, s_contents);
            }
        };

        sender = (TextView) findViewById(R.id.main_sender);
        receivedDate = (TextView) findViewById(R.id.main_date);
        contents = (TextView) findViewById(R.id.main_contents);
    }

    private void modifySMSUI(String s, String d, String c){
        sender.setText(s);
        receivedDate.setText(d);
        contents.setText(filterContents(c));
    }

    private String filterContents(String content){
        // example1 : 이**!LGT!01960!!!20190305181500!176!!서울시 송파구 문정동 634 가든파이브
        // example2 : 이**!KT!01960!!!20190305184200!L154!OFF!서울 송파구 문정동 634
        String result ="";

        String[] parse_one = content.split("!");
        // ["이**", "LGT", "01960","", "", "20190305181500", "176", "", "서울시 송파구 문정동 634 가든파이브"]
        // ["이**", "KT", "01960","", "", "20190305184200", "L154", "OFF", "서울 송파구 문정동 634"]
        Log.d(TAG, "Split Length : " + parse_one.length);

        if(parse_one.length < 2){
            //회사에서 온 문자메시지 타입 아님
            Log.i(TAG, "Skip Filter");
            result += "Not Define Skip Filter!!";
        }else{
            //회사에서 온 문자메시지 타입
            Log.d(TAG, "등록 회사 : " + parse_one[1]);
            if(parse_one[1].equals("LGT")){
                result += parse_one[0]+"\n";
                result += parse_one[1]+"\n";
                result += parse_one[2]+"\n";
                result += parse_one[5]+"\n";
                result += parse_one[6]+"\n";
                result += parse_one[7]+"\n";
                result += parse_one[8]+"\n";
            }else if(parse_one[1].equals("KT")){
                //who
                result += parse_one[0]+"\n";
                result += parse_one[1]+"\n";
                result += parse_one[2]+"\n";
                result += parse_one[5]+"\n";
                result += parse_one[6]+"\n";
                result += parse_one[7]+"\n";
                result += parse_one[8]+"\n";
            }else{
                result += "등록되지 않거나 회사에서 온 문자 메시지가 아님. ";
            }
        }

        return result;
    }

}
