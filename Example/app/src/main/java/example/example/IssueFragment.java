package example.example;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.UUID;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssueFragment extends Fragment  implements OnMapReadyCallback {
    public static final String EXTRA_ISSUE_ID = "com.example.aiflab.testfixit.issue_id";

    private Issue mIssue;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mDescription;
    private MapView mMap;
    private ImageButton upvote;
    private GoogleMap gMap;


    public IssueFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID issueId = (UUID)getArguments().getSerializable(EXTRA_ISSUE_ID);
        mIssue = IssueList.get(getActivity()).getIssue(issueId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.issue_view, container, false);

        //Title
        mTitle = (TextView)v.findViewById(R.id.titleTextView);
        mTitle.setText(mIssue.getName());

        //Image
        ImageView mImage = (ImageView)v.findViewById(R.id.imageView);
        if (mIssue.getPic() != null) {
            mImage.setImageBitmap(mIssue.getPic());
        }
        //Description
        mDescription = (TextView)v.findViewById(R.id.descriptionTextView);
        mDescription.setText(mIssue.getDescription());

        //MapView
        // Gets the MapView from the XML layout and creates it
        /*mMap = (MapView)v.findViewById(R.id.issueMapView);
        mMap.onCreate(savedInstanceState);*/

        // Gets to GoogleMap from the MapView and does initialization stuff
        /*SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.issueMapView);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }*/

        //gMap.getUiSettings().setMyLocationButtonEnabled(false);
        //gMap.setMyLocationEnabled(true);

        //Upvote ImageView
        upvote = (ImageButton)v.findViewById(R.id.upvote);
        Drawable tempDrawable = getResources().getDrawable(R.drawable.wrench);
        upvote.setImageBitmap(((BitmapDrawable) tempDrawable).getBitmap());
        return v;
    }

    public static IssueFragment newInstance(UUID issueId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ISSUE_ID, issueId);

        IssueFragment fragment = new IssueFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mIssue.setMap(googleMap);
        LatLng issueLatLng = new LatLng(mIssue.getLat(), mIssue.getLon());
        googleMap.addMarker(new MarkerOptions().position(issueLatLng).title(mIssue.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(issueLatLng, 12.0f));
        }
    }


