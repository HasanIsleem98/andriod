package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class   Modify extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db= null;
    Button save,b,c;
    EditText e1,e2,e3,e4,e5,e6;
    String sd;
    TextView ee;
    private SQLiteStatement state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ee=findViewById(R.id.ee);
        sd="The Record is saved";
        save=findViewById(R.id.save);
        b=findViewById(R.id.b);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        e5=findViewById(R.id.e5);
        e6=findViewById(R.id.e6);
        c=findViewById(R.id.clear);
        db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        save.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        e1.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        e4.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        e5.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        e2.setKeyListener ( DigitsKeyListener.getInstance ( "qwertyuiopasdfghjkl;zxcvbnm" ) );
        e6.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );



    }

    @Override
    public void onClick(View v) {

        if (v==save){
            if(e1.getText().toString().matches(""))
            {
                e1.setError("please Inter the values");
            }

            if(e2.getText().toString().matches(""))
            {
                e2.setError("please Inter the values");
            }

            if(e3.getText().toString().matches(""))
            {
                e3.setError("please Inter the values");
            }

            if(e4.getText().toString().matches(""))
            {
                e4.setError("please Inter the values");
            }
            if(e5.getText().toString().matches(""))
            {
                e5.setError("please Inter the values");
            }
            if(e6.getText().toString().matches(""))
            {
                e6.setError("please Inter the values");
            }
     /*       state=db.compileStatement ( "UPDATE employees SET NAME = ?, SEX = ? ,BaseSalary = ?, TotalSales = ? ,CommissionRate = ? WHERE id=?" );
            state.bindLong(1,Integer.parseInt(e1.getText().toString().trim()) );
            state.bindString(2,e2.getText().toString().trim());
            state.bindString(3,e3.getText().toString().trim());
            state.bindLong(4,Integer.parseInt(e4.getText().toString().trim()) );
            state.bindLong(5,Integer.parseInt(e5.getText().toString().trim()) );
            state.bindLong(6,Integer.parseInt(e6.getText().toString().trim()) );
            state.executeInsert();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NMAE",e2.getText().toString());
            contentValues.put("SEX",e3.getText().toString());
            contentValues.put("BaseSalary",Integer.parseInt(e4.getText().toString().trim()));
            contentValues.put("TotalSales",Integer.parseInt(e5.getText().toString().trim()));
            contentValues.put("CommissionRate",Integer.parseInt(e6.getText().toString().trim()));



            db.update("employees", contentValues, "id "+ " = ? " ,
                    new String[]{String.valueOf(Integer.parseInt(e1.getText().toString().trim()))});*/

         //   db.execSQL("UPDATE employees SET NAME = "+"'"+e2.getText().toString()+"'"+","+SEX =+","+ "WHERE salary = "+"'"+s1+"'");


                Intent i = new Intent (this,MyIntentService.class);
                i.setAction ( "modify" );
                i.putExtra ( "ID",e1.getText().toString().trim() );
                i.putExtra ( "name", e2.getText().toString().toLowerCase().trim());
                i.putExtra ( "sex",e3.getText().toString().toLowerCase().trim());
                i.putExtra ( "bs",e4.getText().toString().trim());
                i.putExtra ( "ts",e5.getText().toString().trim());
                i.putExtra ( "cr", e6.getText().toString().trim());
                startService ( i );
            BroadcastReceiver broadcast = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("Modify")) {
                        String result = intent.getStringExtra("r");
                        ee.setText(result);
                        sd=result;
                        System.out.println("dsdadddddddddddddddddddddddd");
                    }
                }

            };
            IntentFilter Filter1 = new IntentFilter();
            Filter1.addAction("Modify");
            registerReceiver(broadcast, Filter1);
               ee.setText(sd);
//                db.execSQL("update employees set NAME=?,SEX=?,BaseSalary=?,TotalSales = ? ,CommissionRate = ? WHERE id=?",
//                        new Object[]{e2.getText().toString().toLowerCase().trim(),
//                                e3.getText().toString().toLowerCase().trim(),
//                                Integer.parseInt(e4.getText().toString().trim()),
//                                Integer.parseInt(e5.getText().toString().trim()),
//                                Integer.parseInt(e6.getText().toString().trim()),
//                                Integer.parseInt(e1.getText().toString().trim())
//
//                        });



        }
        if (v==b){
            onBackPressed();
        }
        if (v==c){
            e1.setText(" ");
            e2.setText(" ");
            e3.setText(" ");
            e4.setText(" ");
            e5.setText(" ");
            e6.setText(" ");
        }
    }
}