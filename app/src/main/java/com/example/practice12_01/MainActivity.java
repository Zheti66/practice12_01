package com.example.practice12_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerMap, containerDial, containerMail;
    private ImageView imgMap;
    private SeekBar seekBarZoom;
    private RatingBar ratingBar;
    private EditText editEmail;
    private CheckBox checkCopy;
    private Button btnSend;

    private LinearLayout btnNavMap, btnNavDial, btnNavMail;
    private ImageView iconMap, iconDial, iconMail;
    private TextView textMap, textDial, textMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containerMap = findViewById(R.id.container_map);
        containerDial = findViewById(R.id.container_dial);
        containerMail = findViewById(R.id.container_mail);

        imgMap = findViewById(R.id.img_map);
        seekBarZoom = findViewById(R.id.seekBar_zoom);
        ratingBar = findViewById(R.id.ratingBar);
        editEmail = findViewById(R.id.edit_email);
        checkCopy = findViewById(R.id.check_copy);
        btnSend = findViewById(R.id.btn_send);

        btnNavMap = findViewById(R.id.btn_nav_map);
        btnNavDial = findViewById(R.id.btn_nav_dial);
        btnNavMail = findViewById(R.id.btn_nav_mail);

        iconMap = (ImageView) btnNavMap.getChildAt(0);
        textMap = (TextView) btnNavMap.getChildAt(1);

        iconDial = (ImageView) btnNavDial.getChildAt(0);
        textDial = (TextView) btnNavDial.getChildAt(1);

        iconMail = (ImageView) btnNavMail.getChildAt(0);
        textMail = (TextView) btnNavMail.getChildAt(1);

        btnNavMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerMap.setVisibility(View.VISIBLE);
                containerDial.setVisibility(View.GONE);
                containerMail.setVisibility(View.GONE);
                setActiveTab(0);
            }
        });

        btnNavDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerMap.setVisibility(View.GONE);
                containerDial.setVisibility(View.VISIBLE);
                containerMail.setVisibility(View.GONE);
                setActiveTab(1);
            }
        });

        btnNavMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerMap.setVisibility(View.GONE);
                containerDial.setVisibility(View.GONE);
                containerMail.setVisibility(View.VISIBLE);
                setActiveTab(2);
            }
        });

        seekBarZoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = 0.5f + (progress / 100f) * 1.5f;
                imgMap.setScaleX(scale);
                imgMap.setScaleY(scale);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, "Оценка: " + (int) rating + " звёзд", Toast.LENGTH_SHORT).show();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    String message = "Письмо отправлено на " + email;
                    if (checkCopy.isChecked()) {
                        message += " (копия себе)";
                    }
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Некорректный адрес email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setActiveTab(int tabIndex) {
        int activeColor = Color.WHITE;
        int inactiveColor = Color.GRAY;

        iconMap.setColorFilter(tabIndex == 0 ? activeColor : inactiveColor);
        textMap.setTextColor(tabIndex == 0 ? activeColor : inactiveColor);

        iconDial.setColorFilter(tabIndex == 1 ? activeColor : inactiveColor);
        textDial.setTextColor(tabIndex == 1 ? activeColor : inactiveColor);

        iconMail.setColorFilter(tabIndex == 2 ? activeColor : inactiveColor);
        textMail.setTextColor(tabIndex == 2 ? activeColor : inactiveColor);
    }
}