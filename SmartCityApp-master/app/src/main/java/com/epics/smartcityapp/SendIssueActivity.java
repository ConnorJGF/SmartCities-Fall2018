package com.epics.smartcityapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SendIssueActivity extends AppCompatActivity {

    ArrayList<String> issueList = new ArrayList<>();

    ImageView imageSelector;
    Button button;
    Intent k;
    Spinner issueSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_issue);

        issueList.add("Pothole");
        issueList.add("Tripping Hazard");
        issueList.add("Traffic Infrastructure");
        issueList.add("Other");

        issueSelector = findViewById(R.id.issueSelector);
        issueSelector.setAdapter(new ArrayAdapter<String>(SendIssueActivity.this,
                android.R.layout.simple_spinner_item, issueList));

        imageSelector = findViewById(R.id.imageSelector);
        imageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
            }
        });

        k = getIntent();

        button = findViewById(R.id.Send);
    }

    void sendToFirebase(View view){

        if(imageSelected)
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference("reports");

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            //make a new pothole object to store all the info of pothole to send to the server
            Damage damage = new Damage(""+k.getDoubleExtra("lat", 0),
                    ""+k.getDoubleExtra("lng", 0), encodedImage,
                    issueSelector.getSelectedItem().toString(), timeStamp, ((EditText)findViewById(R.id.desc)).getText().toString());

            //get the key from the database to append the new pothole information in the server
            String key = databaseReference.push().getKey();

            //sending the data as a object to the server with the unique key
            databaseReference.child(key).setValue(damage);

            Toast.makeText(this, "Data has been sent", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SendIssueActivity.this, MainActivity.class);
            //Clearing the entire back stack and going back to MainActivity
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finishAndRemoveTask();
        }
        else
        {
            Toast.makeText(this, "Image not selected!", Toast.LENGTH_LONG).show();
        }
    }

    String encodedImage = null;
    boolean imageSelected = false;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageSelected = true;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                byte[] byteArray = stream.toByteArray();

                encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                //the encoded string has newline char, so to replace all newline char with empty string
                encodedImage = encodedImage.replaceAll("\n", "");

                imageSelector.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
