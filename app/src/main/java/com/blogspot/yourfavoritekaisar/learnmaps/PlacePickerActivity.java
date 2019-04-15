package com.blogspot.yourfavoritekaisar.learnmaps;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePickerActivity extends AppCompatActivity {

    TextView txtInfoPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_place_picker);
        txtInfoPlace.findViewById(R.id.txt_info_place);
    }

    public void pickPlace(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();


        try {
            startActivityForResult(builder.build(PlacePickerActivity.this), 1);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Mencek request code apkah 1
        if (requestCode == 1 && resultCode != 0){
            // Menampung data dari place picker
            Place place = PlacePicker.getPlace(PlacePickerActivity.this, data);
            // Menampilkan indo ke dalam String
            String informasi = String.format("Place : %s \n alamat : %s \n latlong : %s", place.getName(),place.getAddress(),place.getLatLng());
            // Tampilkan ke textview
            txtInfoPlace.setText(informasi);
        }
    }
}
