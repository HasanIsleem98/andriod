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

public class insert extends AppCompatActivity implements View.OnClickListener {
Button insert,clear,ac;
    public static final  String  KEY_VALUE             = "number";RadioButton male,female;
String smf,sd;
EditText name,id,sex,bs,ts,cr;
TextView textView,tt;
   // SQLiteDatabase db= null;
    public static final  String  DO_FACT_SERVICE_REQ  = "this this fact for me";
    public static final  String  DO_PRIME_SERVICE_REQ  = "this this prime for me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        insert=findViewById(R.id.InsData);
        sd="The Data has been successfully inserted to the database";
        clear=findViewById(R.id.clear);
        name=findViewById(R.id.namee);
        tt=findViewById(R.id.tt);
        id=findViewById(R.id.g);
        //sex=findViewById(R.id.sex);
        bs=findViewById(R.id.bs);
        ts=findViewById(R.id.ts);
        cr=findViewById(R.id.cr);
        ac=findViewById(R.id.ac);
        male=findViewById(R.id.id);
        female=findViewById(R.id.name);
        textView=findViewById(R.id.textView);
        insert.setOnClickListener(this);
        clear.setOnClickListener(this);
        ac.setOnClickListener(this);
     //   SQLiteDatabase db= null;
    //    db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        id.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        bs.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        ts.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        name.setKeyListener ( DigitsKeyListener.getInstance ( "qwertyuiopasdfghjkl;zxcvbnm" ) );
        cr.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789" ) );
        //gell
    }

    @Override
    public void onClick(View v) {
        if (v==ac){

            onBackPressed();
            tt.setText(" ");

        }
        if (v==insert
                && (!name.getText().toString().matches(""))
                && (!id.getText().toString().matches(""))
                && (male.isChecked()==false || female.isChecked()==false)
                && (!bs.getText().toString().matches(""))
                &&(!ts.getText().toString().matches(""))
                &&(!cr.getText().toString().matches(""))
        ){

              /*  state = db.compileStatement("insert into employees (NAME,ID,SEX,BaseSalary,TotalSales,CommissionRate) values(?,?,?,?,?,?)");



                state.bindString(1, name.getText().toString().toLowerCase().trim());
                state.bindLong(2, Integer.parseInt(id.getText().toString().trim()));
                state.bindString(3, sex.getText().toString().toLowerCase().trim());
                state.bindLong(4, Integer.parseInt(bs.getText().toString().trim()));
                state.bindLong(5, Integer.parseInt(ts.getText().toString().trim()));
                state.bindLong(6, Integer.parseInt(cr.getText().toString().trim()));
                state.execute();
                textView.setText("{ The Data has been successfully inserted to the database }");
              //  db.setTransactionSuccessful();*/
//            Cursor rs= db.rawQuery ( "select *  from employees  where id = ? ;",
//                    new String [] { id.getText().toString().trim()});
//            // rs.moveToPosition ( -1 );
//            if (rs.getCount()!= 0){
//                textView.setText("The Record is exist");
//
//                // db.execSQL ( "delete from employees where id =? ", new Object []{Integer.parseInt(e.getText().toString()) } );
//            }

                if (male.isChecked()==true){
                    smf="male";

                }
                if (female.isChecked()==true){
                    smf="false";
                }
if (male.isChecked()==false && female.isChecked()==false){
    tt.setText("Please enter the value");
}
else {
  //  Intent n=new Intent(this,MyService.class);
   // startService (  new Intent (this,MyService.class) );

    Intent i = new Intent (this,MyIntentService.class);
    i.setAction ( "insert" );
    i.putExtra ( "ID",id.getText().toString().trim() );
    i.putExtra ( "name", name.getText().toString().toLowerCase().trim());
    i.putExtra ( "sex",smf);
    i.putExtra ( "bs",bs.getText().toString().trim());
    i.putExtra ( "ts",ts.getText().toString().trim());
    i.putExtra ( "cr", cr.getText().toString().trim());
    startService ( i );
    BroadcastReceiver broadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Insert")) {
                String result = intent.getStringExtra("r");
                sd=result;
                textView.setText(result);
                System.out.println("dsdadddddddddddddddddddddddd");
            }
        }

    };
    IntentFilter Filter1 = new IntentFilter();
    Filter1.addAction("Insert");
    registerReceiver(broadcast, Filter1);
  /*  db.execSQL("insert into employees values (?,?,?,?,?,?);",
            new Object[]{Integer.parseInt(id.getText().toString().trim()),
                    name.getText().toString().toLowerCase().trim(),
                    smf,
                    Integer.parseInt(bs.getText().toString().trim()),
                    Integer.parseInt(ts.getText().toString().trim()),
                    Integer.parseInt(cr.getText().toString().trim())

            });*/
    textView.setText(sd);
}




        }
        if (male.isChecked()==false &&female.isChecked()==false){

            tt.setText("Please enter the value");
        }


        if(name.getText().toString().matches(""))
        {
            name.setError("please Inter the values");
        }

        if(id.getText().toString().matches(""))
        {
            id.setError("please Inter the values");
        }

//        if(sex.getText().toString().matches(""))
//        {
//            sex.setError("please Inter the values");
//        }

        if(bs.getText().toString().matches(""))
        {
            bs.setError("please Inter the values");
        }
        if(ts.getText().toString().matches(""))
        {
            ts.setError("please Inter the values");
        }
        if(cr.getText().toString().matches(""))
        {
            cr.setError("please Inter the values");
        }
        if (v==clear){

            male.clearFocus();
            female.clearFocus();
            name.setText(" ");
            id.setText(" ");
        //    sex.setText(" ");
            bs.setText(" ");
            ts.setText(" ");
            cr.setText(" ");


        }

    }
}