package com.example.bepis23.gmapprueba;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private final LatLng UPT = new LatLng(-18.0038755, -70.225904);
    private GoogleMap mMap;

    private EditText editDesde;
    private EditText editHasta;
    private Button btnTrazar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment fragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab_1 = (FloatingActionButton)findViewById(R.id.fab_1);
        FloatingActionButton fab_2 = (FloatingActionButton)findViewById(R.id.fab_2);

        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                FloatingActionButton fab_1 = (FloatingActionButton) findViewById(R.id.fab_1);
                if (fab_1.getVisibility() != View.VISIBLE) {
                    fab_1.setVisibility(View.VISIBLE);
                }else{
                    fab_1.setVisibility(View.INVISIBLE);
                }
                FloatingActionButton fab_2 = (FloatingActionButton) findViewById(R.id.fab_2);
                fab_2.setImageResource(R.drawable.logo_upt);
                if (fab_2.getVisibility() != View.VISIBLE) {
                    fab_2.setVisibility(View.VISIBLE);
                }else{
                    fab_2.setVisibility(View.INVISIBLE);
                }
            }
        });


        fab_1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                FloatingActionButton fab_1 = (FloatingActionButton) findViewById(R.id.fab_1);
                FloatingActionButton fab_2 = (FloatingActionButton) findViewById(R.id.fab_2);
                if (fab_1.getVisibility() == View.VISIBLE) {
                    fab_1.setVisibility(View.INVISIBLE);
                    fab_2.setVisibility(View.INVISIBLE);
                }
                if (mMap.getMyLocation() != null)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude()), 15));
            }
        });

        fab_1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                FloatingActionButton fab_1 = (FloatingActionButton) findViewById(R.id.fab_1);
                FloatingActionButton fab_2 = (FloatingActionButton) findViewById(R.id.fab_2);
                if (fab_1.getVisibility() == View.VISIBLE) {
                    fab_1.setVisibility(View.INVISIBLE);
                    fab_2.setVisibility(View.INVISIBLE);
                }
                if (mMap.getMyLocation() != null)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLatitude(), mMap.getMyLocation().getLongitude()), 15));
            }
        });

        fab_2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                FloatingActionButton fab_1 = (FloatingActionButton) findViewById(R.id.fab_1);
                FloatingActionButton fab_2 = (FloatingActionButton) findViewById(R.id.fab_2);
                if (fab_2.getVisibility() == View.VISIBLE) {
                    fab_1.setVisibility(View.INVISIBLE);
                    fab_2.setVisibility(View.INVISIBLE);
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(UPT));

            }
        });


        editDesde = (EditText)findViewById(R.id.editDesde);
        editHasta = (EditText)findViewById(R.id.editHasta);
        btnTrazar = (Button)findViewById(R.id.btnTrazar);

        btnTrazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(editDesde.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"Ingresar coordenadas inciales",Toast.LENGTH_LONG).show();
                }else if("".equals(editHasta.getText().toString().trim())){
                    Toast.makeText(MainActivity.this,"Ingresar coordenadas finales",Toast.LENGTH_LONG).show();
                }else{
                    new RutaMapa(MainActivity.this,mMap,editDesde.getText().toString(),editHasta.getText().toString()).execute();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPT, 15));
        //mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setCompassEnabled(true);


        mMap.addMarker(new MarkerOptions().position(UPT).title("Universidad Privada de Tacna"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UPT));

        mMap.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }
}
