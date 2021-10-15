//Created by Tofik Mohammed
//https://youtu.be/Sok-JjOb7E4
package com.example.myapplication;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import ca.roumani.i2c.MPro;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {

    private static final String TAG = "MCalcPro";
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Log.d(TAG,"OnCreate");
       this.tts = new TextToSpeech(this,this);
       SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
       sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }
    public void onInit(int initStatus)
    {
        this.tts.setLanguage(Locale.US);
    }
    public void analyzedClick(View v){

        Log.d(TAG,"analyze button Clicked");
        try {
            MPro mp = new MPro();
            //mp.setPrinciple("400000");
            //mp.setAmortization("20");
            //mp.setInterest("5");

            EditText cashPrice =  findViewById(R.id.pBox);
            String csh = cashPrice.getText().toString();
            mp.setPrinciple(csh);

            EditText amortization =  findViewById(R.id.aBox);
            String amz = amortization.getText().toString();
            mp.setAmortization(amz);

            EditText interest =  findViewById(R.id.iBox);
            String inter = interest.getText().toString();
            mp.setInterest(inter);

            String s = "Monthly Payment = " + mp.computePayment("%.2f");
            s += "\n\n";
            s += "By making the payments monthly for " + amz + " years, the mortgage will be paid in full. But if you " +
                    "terminate the mortgage on its nth anniversary, the balance still owing depends on n as shown below: \n\n\n";

            s += String.format(Locale.CANADA,"%8d", 0) + mp.outstandingAfter(0, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 1) + mp.outstandingAfter(1, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 2) + mp.outstandingAfter(2, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 3) + mp.outstandingAfter(3, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 4) + mp.outstandingAfter(4, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 5) + mp.outstandingAfter(5, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 6) + mp.outstandingAfter(6, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 7) + mp.outstandingAfter(7, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 8) + mp.outstandingAfter(8, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 9) + mp.outstandingAfter(9, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 10) + mp.outstandingAfter(10, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 11) + mp.outstandingAfter(11, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 12) + mp.outstandingAfter(12, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 13) + mp.outstandingAfter(13, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 14) + mp.outstandingAfter(14, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 15) + mp.outstandingAfter(15, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 16) + mp.outstandingAfter(16, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 17) + mp.outstandingAfter(17, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 18) + mp.outstandingAfter(18, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 19) + mp.outstandingAfter(19, "%16.0f\n");
            s += "\n\n";

            s += String.format(Locale.CANADA,"%8d", 20) + mp.outstandingAfter(20, "%16.0f\n");
            s += "\n\n";


            TextView txt = (TextView) findViewById(R.id.output);
            txt.setText(s);
            txt.setMovementMethod(new ScrollingMovementMethod());
            tts.speak(s, TextToSpeech.QUEUE_FLUSH,null);
        }

    catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];

        double a = Math.sqrt(ax*ax + ay*ay + az*az);
        if (a>10) {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    { }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    { }
}