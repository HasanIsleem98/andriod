package com.example.database;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Delete extends AppCompatActivity implements View.OnClickListener {
  //  SQLiteDatabase db= null;

Button delete,c,b;
EditText e;
TextView e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        delete=findViewById(R.id.delete);
        c=findViewById(R.id.button2);
        b=findViewById(R.id.button3);
        e=findViewById(R.id.g);
        e1=findViewById(R.id.textView3);
        delete.setOnClickListener(this);
        c.setOnClickListener(this);
        b.setOnClickListener(this);
       // db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        e.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );

    }
    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    public void onClick(View v) {
        if (v==b){
      onBackPressed();
        }
        if (v==c){
            e.setText(" ");
        }
        if (v==delete){
            if (e.getText().toString().trim().length() == 0){
                e1.setText("Please enter the value");
            }
            else{


                    Intent i = new Intent (this,MyIntentService.class);
                    i.setAction ( "delete" );
                    i.putExtra ( "ID",e.getText().toString().trim() );
                    startService ( i );
                BroadcastReceiver broadcast = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals("delete")) {
                            String result = intent.getStringExtra("r");
                            e1.setText(result);
                            System.out.println("dsdadddddddddddddddddddddddd");
                        }
                    }

                };
                IntentFilter Filter1 = new IntentFilter();
                Filter1.addAction("delete");
                registerReceiver(broadcast, Filter1);
            }

        }

    }
}