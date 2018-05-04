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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssueFragment extends Fragment implements OnMapReadyCallback {
    public static final String EXTRA_ISSUE_ID = "com.example.aiflab.testfixit.issue_id";

    private Issue mIssue;
    private TextView mTitle;
    private TextView mDescription;
    private CustomMapView mMapView;
    private ImageButton upvote;
    private TextView upvoteCount;

    private TextView txt_help_gest;
    private TextView help_title_gest;
    private LinearLayout hidden_metrics;

    private ImageButton danger_button;
    private ImageButton accessibility_button;
    private ImageButton blight_button;

    private TextView danger_vote;
    private TextView accessibility_vote;
    private TextView blight_vote;

    public IssueFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String issueId = (String)getArguments().getSerializable(EXTRA_ISSUE_ID);
        mIssue = IssueList.get(getActivity()).getIssue(issueId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.issue_view_with_metrics, container, false);

        //Special stuff
        help_title_gest = (TextView)v.findViewById(R.id.help_title_gest);
        help_title_gest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Blah!", Toast.LENGTH_SHORT).show();
                hidden_metrics.setVisibility( hidden_metrics.isShown()
                        ? View.GONE
                        : View.VISIBLE );
                danger_button.setImageResource(R.drawable.danger);
                accessibility_button.setImageResource(R.drawable.accessibility_image);
                blight_button.setImageResource(R.drawable.natue_icon);
            }
        });

        hidden_metrics = (LinearLayout)v.findViewById(R.id.hidden_metrics);
        hidden_metrics.setVisibility(View.GONE);

        danger_vote = (TextView)v.findViewById(R.id.danger_vote);
        danger_vote.setText(String.valueOf(mIssue.dangerScore));
        danger_button = (ImageButton)v.findViewById(R.id.danger_button);
        danger_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmp;
                if (mIssue.dangerHasVoted) {
                    tmp = -1;
                }
                else {
                    tmp = 1;
                }
                mIssue.dangerScore += tmp;
                mIssue.dangerHasVoted = !mIssue.dangerHasVoted;
                danger_vote.setText(String.valueOf(mIssue.dangerScore));
            }
        });

        accessibility_vote = (TextView)v.findViewById(R.id.accessibility_vote);
        accessibility_vote.setText(String.valueOf(mIssue.acsScore));
        accessibility_button = (ImageButton)v.findViewById(R.id.accessibility_button);
        accessibility_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmp;
                if (mIssue.acsHasVoted) {
                    tmp = -1;
                }
                else {
                    tmp = 1;
                }
                mIssue.acsScore += tmp;
                mIssue.acsHasVoted = !mIssue.acsHasVoted;
                accessibility_vote.setText(String.valueOf(mIssue.acsScore));
            }
        });

        blight_vote = (TextView)v.findViewById(R.id.blight_vote);
        blight_vote.setText(String.valueOf(mIssue.blightScore));
        blight_button = (ImageButton)v.findViewById(R.id.blight_button);
        blight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tmp;
                if (mIssue.blightHasVoted) {
                    tmp = -1;
                }
                else {
                    tmp = 1;
                }
                mIssue.blightScore += tmp;
                mIssue.blightHasVoted = !mIssue.blightHasVoted;
                blight_vote.setText(String.valueOf(mIssue.blightScore));
            }
        });


//        txt_help_gest = (TextView)v.findViewById(R.id.txt_help_gest);
//        txt_help_gest.setVisibility(View.GONE);




        //Title
        mTitle = (TextView)v.findViewById(R.id.titleTextView);
        mTitle.setText(mIssue.getName());

        //Image
        ImageView mImage = (ImageView)v.findViewById(R.id.imageView);
        if (mIssue.getBitmapPic() != null) {
            mImage.setImageBitmap(mIssue.getBitmapPic());
        }
        //Description
        mDescription = (TextView)v.findViewById(R.id.descriptionTextView);
        mDescription.setText(mIssue.getDescription());

        //MapView
        // Gets the MapView from the XML layout and creates it
        mMapView = (CustomMapView)v.findViewById(R.id.issueMapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

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
        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedUpVote();
//                mIssue.increaseScore();
//                Toast.makeText(getContext(), "You just upvoted this Issue!", Toast.LENGTH_SHORT).show();
//                upvoteCount.setText(String.valueOf((int)mIssue.getScore()));
            }
        });

        //Upvote Count
        upvoteCount = (TextView)v.findViewById(R.id.upvoteCount);
        upvoteCount.setText(String.valueOf((int)mIssue.getScore()));

        return v;
    }

    public static IssueFragment newInstance(String issueId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ISSUE_ID, issueId);
        IssueFragment fragment = new IssueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void clickedUpVote() {
        if (!mIssue.getHasVoted()) {
            mIssue.setHasVoted(true);
            mIssue.increaseScore();
            Toast.makeText(getContext(), "You just upvoted this Issue!", Toast.LENGTH_SHORT).show();
            upvoteCount.setText(String.valueOf((int)mIssue.getScore()));
        }
        else {
            mIssue.setHasVoted(false);
            mIssue.setScore(mIssue.getScore()-1);
            Toast.makeText(getContext(), "You unvoted this Issue!", Toast.LENGTH_SHORT).show();
            upvoteCount.setText(String.valueOf((int)mIssue.getScore()));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng issueLatLng = new LatLng(mIssue.getLat(), mIssue.getLon());
        googleMap.addMarker(new MarkerOptions().position(issueLatLng).title(mIssue.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(issueLatLng, 12.0f));
    }

    //https://stackoverflow.com/questions/16536414/how-to-use-mapview-in-android-using-google-map-v2
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}




