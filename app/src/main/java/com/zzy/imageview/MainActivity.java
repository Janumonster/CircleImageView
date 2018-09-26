package com.zzy.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.zzy.circleimageview.VinciImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    VinciImageView imageView;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_davinci);
        button = findViewById(R.id.button_imageview);
        Glide.with(this).load(R.drawable.davinci).into(imageView);
//        imageView.setmBackgroundCorlor("#000000");
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_imageview:
                break;
        }
    }
}
