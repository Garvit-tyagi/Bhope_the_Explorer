package com.example.bhopetheexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.RequestQueue;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
 private TextView date_tv;
 private Button btn_apod;
 private Button btn_explore;
public static final String EXTRADATE="extradate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date_tv=findViewById(R.id.date_tv);
        btn_apod=findViewById(R.id.btn_apod);
        btn_explore=findViewById(R.id.btn_explore);
        Calendar c=Calendar.getInstance();
        int month=c.get(Calendar.MONTH)+1;
        date_tv.setText(c.get(Calendar.YEAR)+"-"+month+"-"+c.get(Calendar.DAY_OF_MONTH));

        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment df=new DatePicker();
                ((DatePicker)df).setListener( MainActivity.this);
                df.show(getSupportFragmentManager(),"date picker");
            }
        });
        btn_apod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,apod_detail.class);
                i.putExtra(EXTRADATE,date_tv.getText().toString());
                startActivity(i);
            }
        });
        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,exploreInside.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
int m=month+1;
        date_tv.setText(year + "-" + m + "-" +dayOfMonth);
    }
}
