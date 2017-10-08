package example.example;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // Add markers in Berkeley and move the camera
        LatLng mem_stad = new LatLng(37.8710, -122.2508);
        LatLng dwinelle = new LatLng(37.8705, -122.2606);
        LatLng evans = new LatLng(37.8737,-122.2578);
        mMap.addMarker(new MarkerOptions().position(mem_stad).title("Marker in Memorial Stadium"));
        mMap.addMarker(new MarkerOptions().position(dwinelle).title("Marker in Dwinelle"));
        mMap.addMarker(new MarkerOptions().position(evans).title("Marker in Evans"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mem_stad, 12.0f));
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // TODO: link to individual Issue display pages
        Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
