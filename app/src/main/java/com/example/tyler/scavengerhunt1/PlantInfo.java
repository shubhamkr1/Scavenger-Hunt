package com.example.tyler.scavengerhunt1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tyler on 04-12-2016.
 */
public class PlantInfo extends Activity {

    String value_from_main_intent;
    Cursor c;
    SQLiteDB db;
    SQLiteDatabase sq;
    StringBuffer sb;
    TextView tvName, tvSciName, tvWaterPref, tvUses, tvNativ;
    ImageView imgView;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_info);

        tvName = (TextView) findViewById(R.id.tvName);
        tvSciName = (TextView) findViewById(R.id.tvSciName);
        tvWaterPref = (TextView) findViewById(R.id.tvWaterPref);
        tvUses = (TextView) findViewById(R.id.tvUses);
        tvNativ = (TextView) findViewById(R.id.tvNativ);
        imgView = (ImageView) findViewById(R.id.imgView);

        Bundle extras = getIntent().getExtras();
        value_from_main_intent = extras.getString("key2");
        //Toast.makeText(this,"value from intent is " + value_from_main_intent ,Toast.LENGTH_LONG).show();

        db=new SQLiteDB(this);
        sq = db.getWritableDatabase();
        sb = new StringBuffer();

        c = db.getInfo(sq,value_from_main_intent);

        //fetch info from DB
        if(c.getCount()==0)
            Toast.makeText(getBaseContext(),"database error",Toast.LENGTH_LONG).show();
        else
             {
                 c.moveToFirst();
                tvName.setText(c.getString(1));
                tvSciName.setText(c.getString(2));
                tvWaterPref.setText(c.getString(3));
                tvUses.setText(c.getString(4));
                tvNativ.setText(c.getString(5));
                tvName.setText(c.getString(1));
                imgPath = c.getString(6);

            int id = getResources().getIdentifier("com.example.tyler.scavengerhunt1:drawable/" + value_from_main_intent, null, null);

            Drawable res = getResources().getDrawable(id);
            imgView.setImageDrawable(res);
        }
    }
}