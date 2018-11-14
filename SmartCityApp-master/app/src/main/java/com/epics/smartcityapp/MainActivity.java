package com.epics.smartcityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, 0, null)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();


        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Home");

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == R.id.navigation_dashboard)
            {
                callPlacePicker(null);
            }
//
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    toolbar.setTitle(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//
//                    callPlacePicker();
//
//                    //startActivity(new Intent(MainActivity.this, choose_type.class));
//                    return true;
//                case R.id.navigation_notifications:
//                    toolbar.setTitle(R.string.title_notifications);
//                    return true;
//            }
//            return false;
            return true;
        }
    };

    GoogleApiClient googleApiClient;

    public void callPlacePicker(View view)
    {
        if((googleApiClient == null) || (!googleApiClient.isConnected()))
        {
            return;
        }

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try
        {
            startActivityForResult(builder.build(MainActivity.this), 0);
        }
        catch(GooglePlayServicesRepairableException e)
        {
            Log.e("Place Picker SDK", e.getMessage());
        }
        catch(GooglePlayServicesNotAvailableException e)
        {
            Log.e("Place Picker SDK", "Place Services not available " + e.getMessage());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(intent != null)
        {
            Place place = PlacePicker.getPlace(this, intent);
            Intent i = new Intent(MainActivity.this, SendIssueActivity.class);
            LatLng ll = place.getLatLng();

            i.putExtra("lat", ll.latitude);
            i.putExtra("lng", ll.longitude);
            i.putExtra("address", place.getAddress().toString());

            startActivity(i);
        }
    }
}
