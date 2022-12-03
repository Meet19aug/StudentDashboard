package com.example.studentdashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNoticeFragment newInstance(String param1, String param2) {
        AddNoticeFragment fragment = new AddNoticeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_notice, container, false);

        Button addnotice = (Button)view.findViewById(R.id.addnotice);
        EditText email = (EditText)view.findViewById(R.id.email);
        EditText semester =(EditText) view.findViewById(R.id.semester);
        EditText title =(EditText) view.findViewById(R.id.noticeTitle);
        EditText data = (EditText)view.findViewById(R.id.noticeData);

        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entEmail = email.getText().toString();
                String entSem = semester.getText().toString();
                String entTitle = title.getText().toString();
                String entData = data.getText().toString();

                DBNoticeHelper dbHelper = new DBNoticeHelper(getContext());
                dbHelper.addToGeneralNotice(entEmail, entSem, entTitle, entData);
                Toast.makeText(getContext(), "Notice Added to db Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}