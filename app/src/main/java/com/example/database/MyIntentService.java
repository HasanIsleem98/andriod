package com.example.database;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class MyIntentService extends IntentService {

    String Result = "";
    Cursor c;

    private  static final String ChannelID=" hasan Channel  Id";
    SQLiteDatabase db= null;
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        System.out.println("\nThe oncreate is been called...");

        db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        super.onCreate();
    }



    protected void onHandleIntent(Intent intent) {
        if (intent.getAction ().equals ( "display")){

            c = db.rawQuery("select *  from employees ",null);
            c.moveToPosition(-1);
            Result="id || name || sex || Bsalary || Tsalary  || Crate"+"\n";
            while(c.moveToNext()){


                Result+=

                        c.getLong(0) + "   || " +
                                c.getString(1) + "  ||    " +
                                c.getString(2) + "    ||   " +
                                c.getLong(3) + "   ||         " +
                                c.getLong(4) + "     ||    " +
                                c.getLong(5) + "   ||  " + "\n";

            }
            Intent g=new Intent();
            g.setAction("Display");
            g.putExtra("r",Result);
            sendBroadcast(g);
            Intent i = new Intent (this,MyService2.class);
            i.setAction("Display");
            startForegroundService(i);


        }
        if (intent.getAction ().equals ( "s")){
            String id = intent.getStringExtra("ID");


            c = db.rawQuery("select *  from employees  where id = ? ;", new String[]{id});

            if(c.getCount()==0)
            {
                Result="the record dose not exist";
                Intent g=new Intent();
                g.setAction("toSearch");
                g.putExtra("r",Result);
                sendBroadcast(g);
               // Toast.makeText(getApplicationContext(),"ID is Not Found",Toast.LENGTH_LONG).show();
            }
            else {
                c.moveToNext();
                System.out.println("ggggggggggggggggggggggg");

                Result="id || name || sex || Bsalary || Tsalary  || Crate"+"\n";
                Result+=

                        c.getLong(0) + "   || " +
                                c.getString(1) + "  ||    " +
                                c.getString(2) + "    ||   " +
                                c.getLong(3) + "   ||         " +
                                c.getLong(4) + "     ||    " +
                                c.getLong(5) + "   ||  " + "\n";

                Intent g=new Intent();
                g.setAction("toSearch");
                g.putExtra("r",Result);
                sendBroadcast(g);
                Intent i = new Intent (this,MyService2.class);
                i.setAction("Search");
                startForegroundService(i);


            }

            System.out.println("dddddddddddddddddddddddddddddddd");

        }
        if (intent.getAction ().equals ( "modify") ){

            String id = intent.getStringExtra("ID");
            String name = intent.getStringExtra("name");
            String sex = intent.getStringExtra("sex");
            String cr = intent.getStringExtra("cr");
            String salary = intent.getStringExtra("ts");
            String sale = intent.getStringExtra("bs");
            c = db.rawQuery("select *  from employees  where id = ? ;", new String[]{id});
            if (c.getCount() ==0){
                Result="the record dose not exist";
                Intent g=new Intent();
                g.setAction("Modify");
                g.putExtra("r",Result);
                sendBroadcast(g);

            }

            else {
                db.execSQL("update employees set NAME=?,SEX=?,BaseSalary=?,TotalSales = ? ,CommissionRate = ? WHERE id=?",
                        new Object[]{
                                name,
                                sex,
                                Integer.parseInt(sale),
                                Integer.parseInt(salary),
                                Integer.parseInt(cr),
                                Integer.parseInt(id)
                        });
                Intent i = new Intent(this, MyService2.class);
                i.setAction("modify");
                startForegroundService(i);
            }
        }

        if (intent.getAction ().equals ( "insert") ){



            String id = intent.getStringExtra("ID");
            String name = intent.getStringExtra("name");
            String sex = intent.getStringExtra("sex");
            String cr = intent.getStringExtra("cr");
            String salary = intent.getStringExtra("ts");
            String sale = intent.getStringExtra("bs");

            c = db.rawQuery("select *  from employees  where id = ? ;", new String[]{id});
            if (c.getCount() !=0){
                Result="the record  exist";
                Intent g=new Intent();
                g.setAction("Insert");
                g.putExtra("r",Result);
                sendBroadcast(g);

            }

            else{

            db.execSQL("insert into employees values (?,?,?,?,?,?);",
                    new Object[]{Integer.parseInt(id),
                            name,
                            sex,
                            Integer.parseInt(sale),
                            Integer.parseInt(salary),
                            Integer.parseInt(cr)
                    });
            Intent i = new Intent (this,MyService2.class);
            i.setAction("insert");
            startForegroundService(i);}

        }


        else if(intent.getAction ().equals ( "delete")){
            String id = intent.getStringExtra("ID");
            c = db.rawQuery("select *  from employees  where id = ? ;", new String[]{id});
            if(c.getCount()==0)
            {
                Result="the record dose not exist";
                Intent g=new Intent();
                g.setAction("delete");
                g.putExtra("r",Result);
                sendBroadcast(g);
                // Toast.makeText(getApplicationContext(),"ID is Not Found",Toast.LENGTH_LONG).show();
            }
            else {
                db.execSQL("delete from employees where id =? ", new Object[]{Integer.parseInt(id)});
                Intent g = new Intent(this, MyService2.class);
                g.setAction("delete");

                startForegroundService(g);
            }
        }
        else if(intent.getAction ().equals ( "serach")){
            System.out.println("gggggggggggggggggggggggggggggg");


           String id = intent.getStringExtra("ID");


            c = db.rawQuery("select *  from employees  where id = ? ;", new String[]{id});
            if(c.getCount()==0)
            {
                Toast.makeText(getApplicationContext(),"ID is Not Found",Toast.LENGTH_LONG).show();
            }
            else {
                Result="id || name || sex || Bsalary || Tsalary  || Crate"+"\n";
                Result+=

                                       c.getLong(0) + "   || " +
                  c.getString(1) + "  ||    " +
                  c.getString(2) + "    ||   " +
                  c.getLong(3) + "   ||         " +
                 c.getLong(4) + "     ||    " +
                 c.getLong(5) + "   ||  " + "\n";

                Intent g=new Intent();
                g.setAction("toSearch");
                g.putExtra("r",Result);
                sendBroadcast(g);


            }





        }

    }


}