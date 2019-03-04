package com.example.hiandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*
    * 장문 주석
    * */

    // 한줄 주석

    // Log 출력 시 편하도록 TAG 변수를 지정해줍니다.
    private final String TAG = "MainActivity";

    //디자인한 XML의 View들을 사용할 수 있도록 Java Class들을 지정해줍니다.
    TextView dialog_text, text_counter;
    EditText editText;
    Button sendBTN;

    //이외에 필요한 변수들을 지정해줍니다.

    //OnCreate()는 MainActivity 실행 시 가정 먼저 실행되며 한번만 실행됩니다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //각 변수들을 메모리에 지정하여 사용할 수 있도록 초기화 메소드(함수)를 호출합니다.
        init();
    }

    //변수 초기화 함수
    //초기화 = 메모리에 관련 데이터를 올려두어 사용할 수 있도록 하는 것.
    private void init(){
        Log.i(TAG, "init start....");
        //지정한 View Class 이외의 변수들을 초기화합니다.

        //지정한 View Class 변수들을 초기화합니다.
        dialog_text = (TextView) findViewById(R.id.dialog_text);
        text_counter = (TextView) findViewById(R.id.text_counter);
        editText = (EditText) findViewById(R.id.send_edit);
        sendBTN = (Button) findViewById(R.id.sendBTN);

        //각 View Class 에 알맞는 이벤트(행위에 대한 반응)을 지정해줍니다.
        //호출은 OnCreate 또는 init 함수 등 자유롭게 한번만 실행하면 됩니다.
        setEvents();
    }

    private void setEvents(){
        //Send Button 의 Click 이벤트를 지정해줍니다.
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                dialog_text.setText(msg);
            }
        });

        //Edittext 의 TextCount 기능을 추가하기 위한 Text Change 이벤트를 추가해줍니다.

        //Edittext 에 Enter Key 를 클릭했을 때, Send Button 의 Click 이벤트를 지정해줍니다.
        //action + keyCode
    }

    //Send Click 이벤트를 메소드로 호출하여 사용할 수 있도록 지정합니다.
    private void sendTextToAndorid(){
    }
}
