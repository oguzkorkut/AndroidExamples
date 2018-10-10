package com.okorkut.mymaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    /**
     * Harita
     */
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Maps activity
         */
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /**
         *
         */
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public void onLocationChanged(Location location) {
                //Kullanıcı yeri değişse ne yapılacak
                //System.out.println("MyMaps Location:" + location.toString());

                //Harita üzerindeki herşeyi temizler
                mMap.clear();

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                //Kullanıcı konumuna marker konuyor
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

                /**
                 * Kullanıcı yeri değiştikçe kamera konumlanıyor
                 */
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));

                /**
                 *
                 * Locale.getDefault() Kullanıcı hangi dilde kullanıyorsa o standarta gösteriyor adresi
                 *
                 * Geocoding  bir adres ismini enlem boylama dönüştürme işlemi
                 * Reverse Geocoding : Enlem boylamdan sokak adresi bulma
                 *
                 */
                /*
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addressList = geocoder.getFromLocation( location.getLatitude(), location.getLongitude(), 1);

                    if (addressList != null && addressList.size() != 0){
                        System.out.println("Address:" + addressList.get(0).toString());
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
                */


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                //GPS sinyali varsa
            }

            @Override
            public void onProviderDisabled(String s) {
                //GPS sinyali yoksa
            }
        };

        if (Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {

                /**
                 * Zaman uygun seçilmezse sürekli locasyon güncellemeye çalışır ve kullanıcı pilini yer.
                 */
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        } else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            //Son bilinen lokasyonu GPS Provider dean alma
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng userLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

            mMap.addMarker(new MarkerOptions().title("Your Location").position(userLastLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( userLastLocation , 15));
        }

        /**
         * onLongclick listener ile harita bağlanıyor. Tanım yapılıyor
         */
        mMap.setOnMapLongClickListener( this);
        /**
         * İzin var mı kontrol edilecek. Kullanıcı yerine ulaşma
         */

        /**
         * Aşğıda default bir yeri marker ile gösterimi yer almaktadır
         */

        /*

            // Add a marker in Sydney and move the camera /Enlem ve Boylam
            LatLng gaziAntep = new LatLng(37.063698, 37.383570);
            //Marker ekle
            mMap.addMarker(new MarkerOptions().position(gaziAntep).title("Marker in GaziAntep"));
            //Haritayı yönlendir
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(gaziAntep));
            //Zoom yapmak istenildiğinde
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gaziAntep, 15));

        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        /**
         * Sonuç geldi ise
         */
        if (grantResults.length > 0){
            if (requestCode == 1 ){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        mMap.clear();

        /**
         * Haritaya uzun tıklandığında ne olacak
         */
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        String address = "";

        try {
            List<Address> addressList = geocoder.getFromLocation( latLng.latitude, latLng.longitude, 1);

            if (addressList != null && addressList.size() != 0){

                //Her tıklanan yerde cadde adı olmayabilir
                if (addressList.get(0).getThoroughfare() != null){
                    address += addressList.get(0).getThoroughfare();
                }
                System.out.println("Address:" + addressList.get(0).toString());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        if (address.matches("")){
            address = "No address";
        }

        System.out.println("Click Adress:" + address);
        mMap.addMarker(new MarkerOptions().position(latLng).title(address));


    }
}
