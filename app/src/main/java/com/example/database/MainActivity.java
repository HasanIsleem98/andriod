package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button insert,delete,search,modify,display;
    TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert =findViewById(R.id.insert);
        delete=findViewById(R.id.delete);
        search=findViewById(R.id.search);
        modify=findViewById(R.id.modify);
        display=findViewById(R.id.display);
        t1=findViewById(R.id.textView10);
        t2=findViewById(R.id.textView11);
        t3=findViewById(R.id.textView12);
        t4=findViewById(R.id.textView13);

        display.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        search.setOnClickListener(this);
        modify.setOnClickListener(this);
        SQLiteDatabase db= null;
        db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );

        db.execSQL ( "create table if not exists employees ( ID int primary key,NAME varchar , SEX  varchar , BaseSalary float , TotalSales float,CommissionRate float);" );



    }

    @Override
    public void onClick(View v) {
        Intent K;
        if (v==display){
            K=new Intent(this,displayAll.class);
            startActivity(K);
        }
        if(v==insert)
        {
            K=new Intent(this,insert.class);
            startActivity(K);
        }
        else if(v==search)
        {
            K=new Intent(this,s.class);
            startActivity(K);
        }
        else if(v==delete)
        {
            K=new Intent(this,Delete.class);
            startActivity(K);
        }
        else if(v==modify)
        {
            K=new Intent(this,Modify.class);
            startActivity(K);
        }

    }
}