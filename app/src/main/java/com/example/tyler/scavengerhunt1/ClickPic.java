package com.example.tyler.scavengerhunt1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

/**
 * Created by Tyler on 28-11-2016.
 */
public class ClickPic extends Activity {

    Button btnCaptureImage, btnNext;
    ImageView img,img2;
    TextView tvMsg;
    ImageButton btnMap;
    String value_from_main_intent;

    Firebase mref;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_pic);

        Firebase.setAndroidContext(this);

        btnCaptureImage = (Button) findViewById(R.id.btnCaptureImage);
        btnNext= (Button) findViewById(R.id.btnNext);
        //img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.imageView2);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        btnMap = (ImageButton) findViewById(R.id.btnMap);

        info = "null";

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnNext.getText().toString().equals("Try again"))
                {
                    Intent camera_intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = getFile();
                    camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(camera_intent,1);
                }
                else
                {
                    //load list_item
                    startActivity(new Intent("android.intent.action.list_item"));
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.googlemap"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //String path = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/tmp_img.jpg").toString();

        //Toast.makeText(this,"path- " + path.toString() ,Toast.LENGTH_SHORT).show();

        //img.setImageDrawable(Drawable.createFromPath(path)); //good

        mref = new Firebase("https://fir-basic-1c5a2.firebaseio.com/Name");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //Toast.makeText(this,"value from DB is " + info.toString() ,Toast.LENGTH_SHORT).show();

        Bundle extras = getIntent().getExtras();
            value_from_main_intent = extras.getString("key");

        //Toast.makeText(this,"value from intent is " + value_from_main_intent ,Toast.LENGTH_LONG).show();

        if(info.equals(value_from_main_intent))
        {
            tvMsg.setText("You got it right. You have earned 100 points");
            btnNext.setVisibility(View.VISIBLE);
            btnMap.setVisibility(View.VISIBLE);
            btnNext.setText("Next Challenge");
            img2.setImageResource(R.drawable.right);
        }
        else
        {
            tvMsg.setText("You got it wrong. Please try again!!");
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Try again");
            img2.setImageResource(R.drawable.wrong);
        }
    }

    private File getFile()
    {
        File img_file  = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/tmp_img.jpg");
        return  img_file;
    }

}