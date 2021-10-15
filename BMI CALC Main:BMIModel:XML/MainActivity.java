//This lab was done by: Tofik Mohamed
//This lab was done individually
//https://youtu.be/kuak_GYrOQs

package com.example.bmiv3;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View v)
    {
        EditText weightView = (EditText) findViewById(R.id.WeightBox);
        String wS = weightView.getText().toString();

        EditText heightView = (EditText) findViewById(R.id.HeightBox);
        String hS = heightView.getText().toString();

        double wD = BMIModel.toDouble(wS);
        double hD = BMIModel.toDouble(hS);
        double bmiD=BMIModel.getBMI(wD, hD);
        String bmiS = BMIModel.formatBMI(bmiD);

        ((TextView) findViewById(R.id.answer)).setText(bmiS);
    }
}