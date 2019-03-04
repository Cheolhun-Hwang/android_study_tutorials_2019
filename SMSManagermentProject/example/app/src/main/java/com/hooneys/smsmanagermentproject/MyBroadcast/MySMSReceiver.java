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
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            //문자메시지
            Object[] Msg = (Object[])bundle.get("pdus");
            SmsMessage[] smsMessages = new SmsMessage[Msg.length];
            for(int index = 0; index<Msg.length; index++){
                smsMessages[index] = SmsMessage.createFromPdu((byte[])Msg[index]);
            }
            //수신 받은 시간
            Date date = new Date(smsMessages[0].getTimestampMillis());
            String currentDate = mDateFormat.format(date);
            Log.i(TAG, "문자 수신 시간 : " + currentDate.toString());
            //SMS 발신 번호
            String receivedNum = smsMessages[0].getOriginatingAddress();
            Log.i(TAG, "발신 번호 : " + receivedNum);
            //문자 내용
            String msg = smsMessages[0].getMessageBody();
            Log.i(TAG, "발신 내용 : " + msg);

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            sendToBroadcast(context, receivedNum, msg, currentDate);
        }
    }

    private void sendToBroadcast(Context context, String sender,
                                String contents, String receivedDate){
        Intent intent = new Intent("SmsMessage.intent.MAIN");
        //Flag 를 설정합니다.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //메시지 내용
        intent.putExtra("send", sender);
        intent.putExtra("contents", contents);
        intent.putExtra("date", receivedDate);

        context.sendBroadcast(intent);
    }
}
