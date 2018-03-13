package com.chan.revernue.filterapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickLogin(View view){
        Button btn_next = (Button)findViewById(R.id.btnLogin);
        Intent intent = new Intent(MainActivity.this,CustomerActivity.class);
        startActivity(intent);
    }
    public void onClickCreateAccount(View view){
        TextView textView = (TextView) findViewById(R.id.txtCreateAccount);
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
        startActivity(intent);


    }
}
