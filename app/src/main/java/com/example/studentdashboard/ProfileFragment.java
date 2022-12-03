package com.example.studentdashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        home_activity activity = (home_activity)getActivity();
        String myDataFromActivity = activity.getMyData();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        DBHelper dbHelper = new DBHelper(getContext());
        StudentInfoDomain studentInfoDomain = dbHelper.findStudentDetails(myDataFromActivity);
        System.out.println("Student Info : "+ studentInfoDomain);
        EditText name = (EditText) view.findViewById(R.id.nameSPTV);
        EditText emailid = (EditText) view.findViewById(R.id.emailSPTV);
        EditText department = (EditText) view.findViewById(R.id.deptSPTV);
        EditText id = (EditText) view.findViewById(R.id.sidSPTV);
        EditText division = (EditText) view.findViewById(R.id.divSPTV);
        EditText semester = (EditText) view.findViewById(R.id.semSPTV);

        name.setText(studentInfoDomain.getUsername());
        emailid.setText(studentInfoDomain.getUseremail());
        department.setText(studentInfoDomain.getUserdepartment());
        id.setText(studentInfoDomain.getUserid());
        division.setText(studentInfoDomain.getUserdivision());
        semester.setText(studentInfoDomain.getUsersemester());

        System.out.println("Profile Fragment : "+studentInfoDomain.getUseremail());

        return view;
    }
}