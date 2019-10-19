package com.example.kammatrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class RecordToService extends AppCompatActivity {

    private LocationManager locationManager;
    private android.location.Location location;
    private final int REQUEST_LOCATION = 200;



 /*   private String[][] ac = new String[10][5];

    ac[0][0] = "ТФК КАМАЗ Набережные Челны";
    ac[0][1] = "55.7058530000";
    ac[0][2] = "52.3987930000";
    ac[0][3] = "7-8552-91-97-57";
    ac[0][4] = "SharipovIS@kamaz.ru";

    ac[1][0] = "ТФК КАМАЗ Вологда";
    ac[1][1] = "59.1993620000";
    ac[1][2] = "39.8113480000";
    ac[1][3] = "7-8172-51-10-60";
    ac[1][4] = "SaharovDV@kamaz.ru";
*/



    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //вызывается при обновлении данных о местоположении
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //вызывается при изменении статуса указанного провайдера
        }

        @Override
        public void onProviderEnabled(String provider) {
            //вызывается при включении указанного провайдера
        }

        @Override
        public void onProviderDisabled(String provider) {
            //вызывается при выключении указанного провайдера
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_to_service);



        TextView textView_location = findViewById(R.id.textView_location);
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
            ActivityCompat.requestPermissions(RecordToService.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        else
            {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, locationListener);
            if (locationManager != null)
                {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    String latitude  = "Latitude: "  + location.getLatitude();
                    String longitude = "Longitude: " + location.getLongitude();

                    textView_location.setText( latitude + ", " +  longitude );
                   //textView_location.setText( String.valueOf(location.getLongitude())  + ", " +  String.valueOf(location.getLatitude())  );
                }

            }
    }
}
