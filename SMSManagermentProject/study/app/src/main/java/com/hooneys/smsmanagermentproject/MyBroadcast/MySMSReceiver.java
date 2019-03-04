package com.hooneys.smsmanagermentproject.MyBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.hooneys.smsmanagermentproject.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MySMSReceiver extends BroadcastReceiver {
    private final String TAG = MySMSReceiver.class.getSimpleName();
    SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy년 MM월 HH시 mm분 ss초 ", Locale.KOREA);

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // 여러 브로드캐스트의 여러 알림 중 SMS 문자 메시지를 수신받도록하자.
        // 브로드캐스트 리시버가 어떤 행동 정보만 받아 처리할지 정해주자.
        if(intent.getAction().equals("~~~")){
            //Bundle 을 통해 Intent 값을 받다록 하자.

            //문자메시지를 받도록 하자 이부분은 어렵다.
            //해당 부분은 고정값으로 생각하면 편하다.
            //이진 데이터 -> 문자메시지 형식으로 바꿔준다.
            Object[] Msg = (Object[])bundle.get("pdus");
            SmsMessage[] smsMessages = new SmsMessage[Msg.length];
            for(int index = 0; index<Msg.length; index++){
                smsMessages[index] = SmsMessage.createFromPdu((byte[])Msg[index]);
            }
            //수신 받은 시간을 정해준다

            //수신 받은 시간을 보기편한 형식으로 바꿔준다.
            //mDateFormat 의 format 기능을 이용해보자.

            Log.i(TAG, "문자 수신 시간 : " );

            //SMS 발신 번호
            String receivedNum = smsMessages[0].getOriginatingAddress();
            Log.i(TAG, "발신 번호 : " + receivedNum);

            //문자 내용
            //발신번호와 마찬가지로 smsMessages 배열의 Index 0에 위치한 값을 받는다.
            //문자 내용은 Body값으로 받는다.

            Log.i(TAG, "발신 내용 : " + );

            //Toast 를 통해 문자 내용을 띄우자.

            //이번 예제는 포그라운드라는 가정을 갖는다.
            //포그라운드의 경우 SMS 문자 정보를 받을 Activity 에 Broadcast 정보를 보낸다.
            //백그라운드의 경우 데이터베이스에 저장한다. Push 알림을 통해 앱 실행 시 저장된
            //문자 정보를 출력한다.
            sendToBroadcast(context, receivedNum, msg, currentDate);
        }
    }

    private void sendToBroadcast(Context context, String sender,
                                String contents, String receivedDate){
        //Intent 를 통해 어떤 값을 보낸 것인지 이름을 지어주자.
        Intent intent = new Intent("SmsMessage.intent.MAIN");

        //flag 를 설정하여, 어떤 일을 하는 지 앱에 알려주자.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //intent 에 send, contents, date 값을 저장하자.

        //sendBroadcast 를 통해 SMS 정보를 Activity 에 보내자
        context.sendBroadcast(intent);
    }
}
