package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayAll extends AppCompatActivity implements View.OnClickListener {
   // SQLiteDatabase db= null;
    Button dis,b;
    TextView t;
   // Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        dis=findViewById(R.id.button);
        b=findViewById(R.id.button4);

        t=findViewById(R.id.t);
     //   db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        dis.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==dis){
            Intent i = new Intent(this, MyIntentService.class);
            i.setAction("display");

            startService(i);

            BroadcastReceiver broadcast = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("Display")) {
                        String result = intent.getStringExtra("r");
                        t.setText(result);
                        System.out.println("dsdadddddddddddddddddddddddd");
                    }
                }

            };
            IntentFilter Filter1 = new IntentFilter();
            Filter1.addAction("Display");
            registerReceiver(broadcast, Filter1);
//             rs= db.rawQuery ( "select *  from employees ",null);
//             rs.moveToPosition(-1);
//            t.setText("id || name || sex || Bsalary || Tsalary  || Crate"+"\n");
//            while(rs.moveToNext()) {
//
//
//
//                t.append(
//
//                                rs.getLong(0) + "   || " +
//                                rs.getString(1) + "  ||    " +
//                                rs.getString(2) + "    ||   " +
//                                rs.getLong(3) + "   ||         " +
//                                rs.getLong(4) + "     ||    " +
//                                rs.getLong(5) + "   ||  " + "\n");
//
//
//            }
      }
        if (v==b){
            onBackPressed();
        }
    }
}