package com.example.btapbuoi8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String sharedPrefFile = "com.example.android.hellosharedprefs";
    private SharedPreferences mPreferences;
    int mCurrentColor;
    int mCurrent;
    TextView txt_num;
    Button btn_black, btn_green, btn_red, btn_blue, btn_Add;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_num = findViewById(R.id.txt_num);
        btn_Add =findViewById(R.id.btn_Add);
        btn_black = findViewById(R.id.btn_black);
        btn_blue = findViewById(R.id.btn_blue);
        btn_green = findViewById(R.id.btn_green);
        btn_red = findViewById(R.id.btn_red);

        mPreferences = getPreferences(MODE_PRIVATE);

        if(savedInstanceState != null){
            int count = mPreferences.getInt(String.valueOf(R.id.txt_num),1);
            String color = String.valueOf(mPreferences.getInt("color", mCurrentColor));
            txt_num.setText(String.valueOf(count));
            txt_num.setBackgroundColor(Integer.valueOf(color));
        }
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrent = Integer.parseInt(txt_num.getText().toString());
                mCurrent = mCurrent + 1;
                txt_num.setText(String.valueOf(mCurrent));
            }
        });

        btn_black.setOnClickListener(clickListener);
        btn_blue.setOnClickListener(clickListener);
        btn_red.setOnClickListener(clickListener);
        btn_green.setOnClickListener(clickListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(String.valueOf(R.id.txt_num), mCurrent);
        preferencesEditor.putInt("color", mCurrentColor);
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            switch (v.getId()){
                case R.id.btn_black:
                    txt_num.setBackgroundColor(Color.BLACK);
                    mCurrentColor = Color.BLACK;
                    break;
                case R.id.btn_red:
                    txt_num.setBackgroundColor(Color.RED);
                    mCurrentColor = Color.RED;
                    break;
                case R.id.btn_green:
                    txt_num.setBackgroundColor(Color.GREEN);
                    mCurrentColor = Color.GREEN;
                    break;
                case R.id.btn_blue:
                    txt_num.setBackgroundColor(Color.BLUE);
                    mCurrentColor = Color.BLUE;
                    break;
            }
        }
    };
}