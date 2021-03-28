package com.example.database;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyService2 extends Service {
    Cursor c;
    String id,sale,sex,salary,rate,name;
    String Result = "";
    String names[];
    SQLiteDatabase db= null;
    private  static final String ChannelID=" HASAN Channel  Id";
    private void createChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel x;
            x=new NotificationChannel (ChannelID,"My  Hi Channel with you"  , NotificationManager.IMPORTANCE_HIGH);
            NotificationManager  man= (NotificationManager)getSystemService ( NOTIFICATION_SERVICE );
            man.createNotificationChannel ( x );
        }
    }


    public MyService2() {
    }


    @Override
    public void onCreate() {

        db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );

        System.out.println("\nForeGroundService t the oncreate is been called...");
        super.onCreate ();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("\nForeGroundService t the OnStartCommand() is been called...");
        if (intent.getAction ().equals ( "modify") ){
            createChannel();
            NotificationCompat.Builder notefor=null;
            notefor = new NotificationCompat.Builder ( getApplicationContext (),ChannelID )
                    .setAutoCancel ( true )
                    .setContentTitle ( "modify For Employee" )
                    .setContentText ( "update employees set NAME=?,SEX=?,BaseSalary=?,TotalSales = ? ,CommissionRate = ? WHERE id=?" )
                    .setSmallIcon (R.drawable.qwe )
                    .setOngoing ( true )
                    .setColor ( Color.BLUE )
                    .setUsesChronometer ( true );
            startForeground ( 1, notefor.build () );
           }
      else if (intent.getAction ().equals ( "insert")){
            NotificationCompat.Builder notefor=null;
            notefor = new NotificationCompat.Builder ( getApplicationContext (),ChannelID )
                    .setAutoCancel ( true )//if true whene clickon is disapper
                    .setContentTitle ( "insert For Employee" )
                    .setContentText ( "insert into employees values (?,?,?,?,?,?)" )
                    .setSmallIcon (R.drawable.qwe )
                    .setOngoing ( true )//if tze7ha bt5tfe
                    .setColor ( Color.BLUE )

                    .setUsesChronometer ( true );//this for time
            startForeground ( 1, notefor.build () );//if id is same make update for natification otherwise make new noti
        }
        else if (intent.getAction ().equals ( "Search")){
            NotificationCompat.Builder notefor=null;
            notefor = new NotificationCompat.Builder ( getApplicationContext (),ChannelID )
                    .setAutoCancel ( true )
                    .setContentTitle ( "insert For Employee" )
                    .setContentText ( "select * from employees where id=?" )
                    .setSmallIcon (R.drawable.qwe )
                    .setOngoing ( true )
                    .setColor ( Color.BLUE )
                    .setUsesChronometer ( true );
            startForeground ( 1, notefor.build () );
        }
      else if (intent.getAction ().equals ( "Display")){
            NotificationCompat.Builder notefor=null;
            notefor = new NotificationCompat.Builder ( getApplicationContext (),ChannelID )
                    .setAutoCancel ( true )
                    .setContentTitle ( "Delete For Employee" )
                    .setContentText ( "select * from employees" )
                    .setSmallIcon (R.drawable.qwe )
                    .setOngoing ( true )
                    .setColor ( Color.BLUE )
                    .setUsesChronometer ( true );
            startForeground ( 1, notefor.build () );
        }
        else if (intent.getAction ().equals ( "delete")){
            NotificationCompat.Builder notefor=null;
            notefor = new NotificationCompat.Builder ( getApplicationContext (),ChannelID )
                    .setAutoCancel ( true )
                    .setContentTitle ( "Delete For Employee" )
                    .setContentText ( "delete from employees where id =?" )
                    .setSmallIcon (R.drawable.qwe )
                    .setOngoing ( true )
                    .setColor ( Color.BLUE )
                    .setUsesChronometer ( true );
            startForeground ( 1, notefor.build () );
        }


        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        System.out.println("\nForeGroundService the OnDestroy  is been called...");
        super.onDestroy ();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException ( "Not yet implemented" );
    }
}