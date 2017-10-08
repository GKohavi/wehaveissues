package example.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by aiflab on 10/7/17.
 */

public class CreateIssueFragment extends Fragment implements View.OnClickListener{

    private Issue mIssue;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private ImageButton mImageButton;

    public CreateIssueFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_issue, container, false);

        mTitleField = (EditText)v.findViewById(R.id.titleEditText);
        mTitleField.setText(mIssue.getName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIssue.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = (EditText)v.findViewById(R.id.descriptionEditText);
        mDescriptionField.setText(mIssue.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIssue.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mImageButton = (ImageButton)v.findViewById(R.id.addImageButton);
        mImageButton.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addImageButton:
                addImageClick(v);
                break;
        }
    }

    public void addImageClick(View v) {
        Toast.makeText(getActivity(),"Add Image Button Clicked", Toast.LENGTH_SHORT).show();
    }
}
