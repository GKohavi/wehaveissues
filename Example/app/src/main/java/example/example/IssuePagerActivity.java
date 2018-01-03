package example.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by aiflab on 10/7/17.
 */

public class IssuePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Issue> mIssues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mIssues = IssueList.get(this).getIssues();

        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Issue anIssue = mIssues.get(position);
                return IssueFragment.newInstance(anIssue.getMId());
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

//        UUID issueId = (UUID)getIntent().getSerializableExtra(IssueFragment.EXTRA_ISSUE_ID);
        String issueId = (String)getIntent().getSerializableExtra(IssueFragment.EXTRA_ISSUE_ID);
        for (int i = 0; i < mIssues.size(); i++) {
            if (mIssues.get(i).getMId().equals(issueId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
