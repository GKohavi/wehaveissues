package example.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssuePagerActivity extends FragmentActivity implements OnMapReadyCallback {
    private ViewPager mViewPager;
    private ArrayList<Issue> mIssues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map); //Find fragment by id?!?!?!?!
        mapFragment.getMapAsync(this);*/

        mIssues = IssueList.get(this).getIssues();

        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Issue anIssue = mIssues.get(position);
                return IssueFragment.newInstance(anIssue.mId);
            }

            @Override
            public int getCount() {
                return mIssues.size();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Issue anIssue = mIssues.get(position);
                if (anIssue.getName() != null) {
                    setTitle(anIssue.getName());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID issueId = (UUID)getIntent().getSerializableExtra(IssueFragment.EXTRA_ISSUE_ID);
        for (int i = 0; i < mIssues.size(); i++) {
            if (mIssues.get(i).mId.equals(issueId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (Issue mIssue : mIssues) {
            mIssue.setMap(googleMap);
            LatLng issueLatLng = new LatLng(mIssue.getLat(), mIssue.getLon());
            googleMap.addMarker(new MarkerOptions().position(issueLatLng).title(mIssue.getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(issueLatLng, 12.0f));
        }
    }
}
