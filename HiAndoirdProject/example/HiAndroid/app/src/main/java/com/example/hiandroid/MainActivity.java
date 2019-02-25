package com.example.hiandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private TextView dialogText, textCounter;
    private EditText editText;
    private Button sendBTN;

    private boolean isOkTextLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        isOkTextLength = true;

        dialogText = (TextView) findViewById(R.id.dialogText);
        textCounter = (TextView) findViewById(R.id.textCounter);
        editText = (EditText) findViewById(R.id.editText);
        sendBTN = (Button) findViewById(R.id.sendBtn);

        setEvents();
    }

    private void setEvents(){
        View.OnClickListener sendListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextToAndorid();
            }
        };
        sendBTN.setOnClickListener(sendListener);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textCounter.setText(s.length()+"/15");
                if(s.length() < 16){
                    textCounter.setTextColor(getResources().getColor(R.color.blue_grey_900));
                    isOkTextLength = true;
                }else{
                    textCounter.setTextColor(getResources().getColor(R.color.red_900));
                    isOkTextLength = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    sendTextToAndorid();
                }
                return true;
            }
        });
    }

    private void sendTextToAndorid(){
        if(isOkTextLength){
            dialogText.setText(editText.getText());
        }else{
            Toast.makeText(getApplicationContext(), "제한 길이를 초과하였습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
