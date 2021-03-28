package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class s extends AppCompatActivity implements View.OnClickListener {
Button b1,b2;
RadioButton r1,r2;
EditText e;
TextView t,t2;
 //   SQLiteDatabase db= null;
    Cursor rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        t=findViewById(R.id.t);
       t2=findViewById(R.id.t2);
        b1=findViewById(R.id.back);
        b2=findViewById(R.id.search);
        r1=findViewById(R.id.id);
        r2=findViewById(R.id.name);
        e=findViewById(R.id.e);
       // db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
      //  e.setEnabled(false);
        e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));

    }

    @Override
    public void onClick(View v) {
        if (v == b1) {


            if (e.getText().toString().trim().length() == 0) {
                t.setText("Please enter the value");
            } else {




                Intent i = new Intent(this, MyIntentService.class);
                i.setAction("s");
                i.putExtra("ID", e.getText().toString().trim());

                startService(i);

                BroadcastReceiver broadcast = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals("toSearch")) {
                            String result = intent.getStringExtra("r");
                            t.setText(result);
                            System.out.println("dsdadddddddddddddddddddddddd");
                        }
                    }

                };
                IntentFilter Filter1 = new IntentFilter();
                Filter1.addAction("toSearch");
                registerReceiver(broadcast, Filter1);



            }
        }

        if (v==b2){
            onBackPressed();
        }

    }
}