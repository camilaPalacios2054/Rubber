package com.example.rubber;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.LocaleManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,LocationListener,GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    //EditText txtLatitud,txtlongitud;
    GoogleMap mMap;
    TextView tvUbicacion;
    ImageButton btnCamara,btnMenu;
    private LocationManager locationManager;
    private Marker myLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        tvUbicacion=findViewById(R.id.tvUbicacion);
        btnCamara=findViewById(R.id.btnCamera);
        btnMenu=findViewById(R.id.btnMenu);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        //pedirPermisosUbicacion();
        //miUbicacion();
        //txtLatitud=findViewById(R.id.txtLatitud);
        //txtlongitud=findViewById(R.id.txtLongitud);

        // Obtener el mapa asincrónicamente
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Inicializar el LocationManager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Habilitar la capa de ubicación del usuario
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Solicitar permisos si no están concedidos
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }

        // Actualizar la ubicación del usuario cuando cambia
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10,this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // Actualizar la ubicación del marcador
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (myLocationMarker == null) {
            // Si el marcador aún no está creado, crearlo
            myLocationMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Mi Ubicación"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            tvUbicacion.setText("LATITUD:"+location.getLatitude()+" LOGITUD:" +location.getLongitude());
        } else {
            // Si ya existe, simplemente actualizar su posición
            myLocationMarker.setPosition(latLng);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Manejar la respuesta de solicitud de permisos
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
     //txtLatitud.setText(""+latLng.latitude);
     //txtlongitud.setText(""+latLng.longitude);
    }
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
       // txtLatitud.setText(""+latLng.latitude);
       // txtlongitud.setText(""+latLng.longitude);
    }
}