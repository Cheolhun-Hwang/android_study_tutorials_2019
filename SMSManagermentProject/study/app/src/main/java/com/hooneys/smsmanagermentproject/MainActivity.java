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

    //화면에 보일 TextView 를 정의하자.

    //BroadCast 정보를 받을 BroadcastReceiver 를 정의하자.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    @Override
    protected void onStart() {
        super.onStart();

        //앱 화면 등장과 함께 BroadcastReceiver 를 등록하자.
        //Intent 정보 값을 구분하기 위해 IntentFilter 를 통해 이름을 등록하자.
    }

    @Override
    protected void onDestroy() {
        //앱이 완전 종료될 경우 BroadcastReceiver 를 제거해주자.
        //포그라운드 작업에서만 사용할 경우 이런 식으로 제거해준다.
        //백그라운드 작업을 지속적으로 요구할 경우 필요없다.

        super.onDestroy();
    }

    private void init(){
        //BroadcastReceiver 를 초기화 및 Receive 이후 행동을 정의해주자.
        //intent 를 이용하여 send, contents, date 값을 받아 modifySMSUI에 정보를 주자.

        //XML 에서 정의한 Textview 를 Java class 로 이용하기 위해 ID를 이용하여
        //연결해주자.
    }

    //SMS 정보를 입력받아 TextView 에 출력하는 기능(역할)
    private void modifySMSUI(String s, String d, String c){
        //발신인, 발신날짜, 내용을 Textview 에 출력하자.
    }

    //SMS의 문자 내용을 구분하여 일괄적인 형식으로 출력하기 위한 기능
    private String filterContents(String content){
        // example1 : 이**!LGT!01960!!!20190305181500!176!!서울시 송파구 문정동 634 가든파이브
        // example2 : 이**!KT!01960!!!20190305184200!L154!OFF!서울 송파구 문정동 634
        String result ="";

        // 구분자인 '!'를 이용하여 문자 내용을 구분하자.
        // ["이**", "LGT", "01960","", "", "20190305181500", "176", "", "서울시 송파구 문정동 634 가든파이브"]
        // ["이**", "KT", "01960","", "", "20190305184200", "L154", "OFF", "서울 송파구 문정동 634"]

        // 조건 1 :
        // 만약 입력 받은 문자에서 '!'값으로 구분하여 길이가 1이라면
        // 회사에서 온 문자이지 않을 가능성이 있다.
        // 그렇기에 구분한 내용의 길이가 2 미만일 때를 1차적 예외로 구분하자.
        // 단, 누구든지 '!'를 문자 내용에 넣을 수 있기 때문에 이를 염려하자.

        // 조건 2 : 회사 문자 타입은 일괄적으로 '!' 구분자로 구분 시 0~8의 인덱스를 갖는
        // 길이가 9를 갖는 형태를 갖는다.
        // 회사 문자 타입 구분 시 인덱스 '1'(0부터 시작)의 값은 약속한 회사의 정보가 들어있다.
        // 이를 이용하여 LGT, KT 만 구분하여 화면에서 출력하도록 한다.
        // 나머지 사항은 "등록되지 않거나 회사에서 온 문자 메시지가 아님."을 반환한다.

        // 조건 3 : 필요 정보인 회사 약속 정보를 확립하고 반환한다. 각 정보마다 한줄씩 출력한다.

        return result;
    }

}
