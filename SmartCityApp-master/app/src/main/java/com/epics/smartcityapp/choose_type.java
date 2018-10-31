package com.epics.smartcityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.*;
import com.google.android.gms.location.places.*;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class choose_type extends AppCompatActivity {





    public static String KIND;
    ListView listView;
    String Other;
    String Description;
    String Pothole;
    String TrafficLight;
    String TripHazard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);


        listView = (ListView) findViewById(R.id.choose);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Pothole");
        arrayList.add("TrafficLight");
        arrayList.add("TripHazard");
        arrayList.add("Other");



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


                ListView lv = (ListView) arg0;
                TextView tv = (TextView) lv.getChildAt(arg2);
                String s = tv.getText().toString();
                switch (arg2) {

                    case 0:
                        Pothole = "chosen";
                        TrafficLight = null;
                        TripHazard = null;
                        Other = null;
                        startActivity(new Intent(choose_type.this, MapsActivity0.class));
                        break;
                    case 1:
                        TrafficLight = "chosen";
                        Pothole = null;
                        TripHazard = null;
                        Other = null;
                        startActivity(new Intent(choose_type.this, MapsActivity1.class));
                        break;
                    case 2:
                        TripHazard = "chosen";
                        TrafficLight = null;
                        Pothole = null;
                        Other = null;
                        startActivity(new Intent(choose_type.this, MapsActivity2.class));
                        break;
                    case 3:
                        Other = "chosen";
                        TripHazard = null;
                        TrafficLight = null;
                        Pothole = null;
                        startActivity(new Intent(choose_type.this, MapsActivity3.class));
                        break;
                }
            }
        });
    }
}








