package com.example.tyler.scavengerhunt1;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.Toast;

import java.io.File;
import java.util.Random;

public class MainScreen extends Activity {

    ImageView img1, img2, img3;

    int[] pic_paths = {
            R.drawable.blueflagiris,
            R.drawable.daffodils,
            R.drawable.english_daisy,
            R.drawable.marigold,
            R.drawable.stonecrop,
            R.drawable.amaryllis,
            R.drawable.daylily,
            R.drawable.nightshade,
            R.drawable.tulip,
            R.drawable.sunflower
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        img1 = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);

        Drawable res = getResources().getDrawable(pic_paths[0]);
        img1.setImageDrawable(res);
        img1.setTag("blueflagiris");
        res = getResources().getDrawable(pic_paths[1]);
        img2.setImageDrawable(res);
        img2.setTag("daffodils");
        res = getResources().getDrawable(pic_paths[2]);
        img3.setImageDrawable(res);
        img3.setTag("english_daisy");

        Button addreminder1 = (Button) findViewById(R.id.button);
        Button getinfo1 = (Button) findViewById(R.id.button2);
        Button takepicture1 = (Button) findViewById(R.id.button3);
        Button addreminder2 = (Button) findViewById(R.id.button4);
        Button getinfo2 = (Button) findViewById(R.id.button5);
        Button takepicture2 = (Button) findViewById(R.id.button6);
        Button addreminder3 = (Button) findViewById(R.id.button7);
        Button getinfo3 = (Button) findViewById(R.id.button8);
        Button takepicture3 = (Button) findViewById(R.id.button9);

        takepicture1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img1.getTag().toString();
                onClickButton(plant_name);
            }
        });

        takepicture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img2.getTag().toString();
                onClickButton(plant_name);
            }
        });

        takepicture3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img3.getTag().toString();
                onClickButton(plant_name);
            }
        });

        getinfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img1.getTag().toString();
                onClickButtonInfo(plant_name);
            }
        });

        getinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img2.getTag().toString();
                onClickButtonInfo(plant_name);
            }
        });

        getinfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plant_name = img3.getTag().toString();
                onClickButtonInfo(plant_name);
            }
        });
    }

    public void onClickButton(String plantname) {
        Intent i = new Intent(this, ClickPic.class);
        Bundle bundle = new Bundle();
        bundle.putString("key", plantname);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickButtonInfo(String plantname) {
        Intent i = new Intent(this, PlantInfo.class);
        Bundle bundle = new Bundle();
        bundle.putString("key2", plantname);
        i.putExtras(bundle);
        startActivity(i);
    }
}