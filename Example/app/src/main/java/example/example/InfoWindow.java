package example.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by aiflab on 4/7/18.
 */

public class InfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context myContext;
    public InfoWindow(Context context) {
        myContext = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.map_info_window, null);

        LatLng latLng = marker.getPosition();
        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
        tvLat.setText(marker.getTitle());
        tvLng.setText("" + latLng.longitude);
        return v;
    }
}
