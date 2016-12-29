package com.example.tyler.scavengerhunt1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btnStart;
    SQLiteDB db;
    SQLiteDatabase sq;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if( resultCode == RESULT_OK){
                Log.d("AUTH", auth.getCurrentUser().getEmail() );
            }
            else{
                Log.d("AUTH", "Not authenticated");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        db=new SQLiteDB(this);

        if(auth.getCurrentUser() != null) {

        }else
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setProviders(AuthUI.EMAIL_PROVIDER, AuthUI.FACEBOOK_PROVIDER, AuthUI.GOOGLE_PROVIDER).build(), 1);
        }

        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Load homepage screen
                sq = db.getWritableDatabase();

                //multiple db.addInfo calls
                db.addInfo(sq,"english_daisy", "English Daisy", "Ballis perennis", "Mesic (Moderate)","Groundcover, Culinary Herb, Medicinal Herb", "western, central and northern Europe", "english_daisy.png","0");
                db.addInfo(sq,"sunflower", "Sunflower (Sunspot)","Helianthus annuus", "Mesic (Moderate)", "Dye production, Cut Flower","North America", "sunflower.png","0");
                db.addInfo(sq,"daffodils", "Daffodils", "Narcissus", "Mesic (Moderate)", "Erosion Control, Cut Flower", "Southern europe, North Africa", "daffodils.png","0");
                db.addInfo(sq,"stonecrop", "Stonecrop","Phedimus spurius", "Dry Mesic,Dry","Groundcover", "Africa and South America", "stonecrop.png","0");
                db.addInfo(sq,"blueflagiris", "Blue Flag Iris", "Iris vericolor", "High, Wet","Aesthetics", "North America, in the Eastern United States and Eastern Canada", "blueflagiris.png","0");
                db.addInfo(sq,"marigold", "Marsh Marigold","Caltha palustris", "High, Wet", "Water gardens", "Woodlands in temperate region", "marigold.png","0");
                db.addInfo(sq,"amaryllis", "Amaryllis","Hippeastrum red lion", "Mesic (Moderate)", "Cut Flower", "Western Cape region of South Africa", "amaryllis.jpg","0");
                db.addInfo(sq,"daylily", "Daylily","Hemerocallis", "Mesic (Moderate)", "Herb, Medicinal use", "China, Korea, and Japan", "daylily.jpg","0");
                db.addInfo(sq,"tulip", "Tulip","Tulipa (Tango)", "High, Wet", "Cut Flower", "Eurasian and North African", "tulip.jpg","0");
                db.addInfo(sq,"nightshade", "Climbing Nightshade","Solanum dulcamara", "Mesic (Moderate)", "Herb, Medicinal use", "Europe and Asia", "nightshade.jpg","0");

                startActivity(new Intent("android.intent.action.list_item"));
            }
        });
    }
}