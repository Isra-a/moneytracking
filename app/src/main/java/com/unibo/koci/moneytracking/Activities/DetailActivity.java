package com.unibo.koci.moneytracking.Activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unibo.koci.moneytracking.Database.DBHelper;
import com.unibo.koci.moneytracking.Entities.Location;
import com.unibo.koci.moneytracking.Entities.MoneyItem;
import com.unibo.koci.moneytracking.R;

import org.joda.time.LocalDate;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolbar_detail;
    SupportMapFragment mapFragment;
    TextView txt_description, txt_amount, txt_category, txt_date, txt_postion;
    MoneyItem item;
    DBHelper dbHelper;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        item = (MoneyItem) getIntent().getExtras().getSerializable("item");
        dbHelper = new DBHelper(this);


        init_toolbar();
        init_textviews();
        init_map();
        init_fab();

    }

    private void init_textviews() {
        item.__setDaoSession(dbHelper.getDaoSession());

        txt_amount = (TextView) findViewById(R.id.detail_amount);
        txt_category = (TextView) findViewById(R.id.detail_category);
        txt_date = (TextView) findViewById(R.id.detail_date);
        txt_description = (TextView) findViewById(R.id.detail_description);
        txt_postion = (TextView) findViewById(R.id.detail_position);

        txt_amount.setText(String.valueOf(item.getAmount()));

        txt_category.setText((item.getCategory().getName()));
        LocalDate d = new LocalDate(item.getDate());
        txt_date.setText(d.toString());
        txt_description.setText(String.valueOf(item.getDescription()));
        txt_postion.setText(String.valueOf(item.getLocation().getName()));

    }

    private void init_fab() {
        FloatingActionButton fabedit = (FloatingActionButton) findViewById(R.id.fab_edit);
        fabedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton fabdelete = (FloatingActionButton) findViewById(R.id.fab_delete);
        fabdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder
                        .setTitle("Delete item")
                        .setMessage("Are you sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                delete_item(item);
                                Toast.makeText(DetailActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });
    }

    private void delete_item(MoneyItem i) {
        Location l = i.getLocation();
        dbHelper.getDaoSession().delete(i);
        dbHelper.getDaoSession().delete(l);
        //todo refresh all
    }

    private void init_map() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng item_pos = new LatLng(item.getLocation().getLatitude(), item.getLocation().getLongitude());
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());

        mMap.addMarker(new MarkerOptions().position(item_pos).title(item.getName().toString()));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(item_pos).zoom(15).build();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


    private void init_toolbar() {
        toolbar_detail = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar_detail);


        getSupportActionBar().setTitle(item.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
