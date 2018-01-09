package example.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by aiflab on 10/6/17.
 */

public class IssueListViewFragment extends ListFragment{
    private ArrayList<Issue> mIssues;
    private int mPosititonSelected;
    private IssueAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Issue List");
        mIssues = IssueList.get(getActivity()).getIssues();

        mAdapter = new IssueAdapter(mIssues);
        setListAdapter(mAdapter);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setOnScrollListener(new InfiniteScroller() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                Toast.makeText(getActivity(),String.valueOf(mIssues.size()), Toast.LENGTH_SHORT).show();
//                DatabaseFunctions.loadMoreIssues(null, mIssues, 5);
                int howManyToLoad = 5;
                DatabaseReference aDatabaseRef = FirebaseDatabase.getInstance().getReference().child("allIssues");

                String lastId = mIssues.get(mIssues.size()-1).getMId();
                if (lastId.equals("")) {
                    return false;
                }
                aDatabaseRef.orderByKey().startAt(lastId).limitToFirst(howManyToLoad+1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean firstOne = true;
                        for (DataSnapshot aSnapshot: dataSnapshot.getChildren()) {
                            if (firstOne) {
                                firstOne = false;
                                continue;
                            }
                            Issue anIssue = aSnapshot.getValue(Issue.class);
                            mIssues.add(anIssue);
                            mAdapter.notifyDataSetChanged();
                            Log.d("debug", "Database: Adding stuff to list!!!");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getActivity(),"load more stuff", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),String.valueOf(mIssues.size()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),"Issue List Clicked", Toast.LENGTH_SHORT).show();
        goToRuleView(position);
    }

    private class IssueAdapter extends ArrayAdapter<Issue> {
        public IssueAdapter(ArrayList<Issue> issues) {
            super(getActivity(), 0, issues);
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_issues,null);
            }
            Issue anIssue = getItem(position);
            Button mTitle = (Button)convertView.findViewById(R.id.issueNameButton);
            mTitle.setText(anIssue.getName());
            mTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosititonSelected = position;
                    titleClick(v);
                }
            });

            ImageView mImage = (ImageView)convertView.findViewById(R.id.listImageView);
            if (anIssue.getBitmapPic() != null) {
                mImage.setImageBitmap(anIssue.getBitmapPic());
            }
            return convertView;
        }
    }

    private void titleClick(View v) {
        goToRuleView(mPosititonSelected);
    }

    private void goToRuleView(int position) {
        Toast.makeText(getActivity(),"Loading Issue...", Toast.LENGTH_SHORT).show();
        Issue anIssue = ((IssueAdapter)getListAdapter()).getItem(position);

        Intent i = new Intent(getActivity(), IssuePagerActivity.class);
        i.putExtra(IssueFragment.EXTRA_ISSUE_ID, anIssue.getMId());
        startActivity(i);
    }


    //https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews-and-RecyclerView
    //https://stackoverflow.com/questions/1080811/android-endless-list
//    @Override
//    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//        Toast.makeText(getActivity(),"onScroll", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView absListView, int i) {
//        Toast.makeText(getActivity(),"onScrollStateChanged", Toast.LENGTH_SHORT).show();
//    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug", "Resuming list fragment!!!");
    }
}
