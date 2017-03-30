package com.khantilchoksi.healthcareapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khantilchoksi.healthcareapp.ArztAsyncCalls.GetDoctorMainSpecialitiesTask;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionsFragment extends Fragment implements GetDoctorMainSpecialitiesTask.AsyncResponse{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private SpecilaityAdapter mSpecialityAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> specialityIdList;
    private ArrayList<String> specialityNamesList;
    private ArrayList<String> specialityDescriptionList;
    private ArrayList<String> specialityIconUrlList;

    private ProgressDialog progressDialog;

    public SectionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionsFragment newInstance(String param1, String param2) {
        SectionsFragment fragment = new SectionsFragment();
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

    private void initDataset() {
        progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching Doctor Specialities...");
        progressDialog.show();
        GetDoctorMainSpecialitiesTask getDoctorSpecialitiesTask = new GetDoctorMainSpecialitiesTask(getContext(),
                getActivity(),this,progressDialog);
        getDoctorSpecialitiesTask.execute((Void) null);


        /*specialityNamesList = new ArrayList<String>();
        specialityDescriptionList = new ArrayList<String>();

        specialityNamesList.add("Ontology");
        specialityDescriptionList.add("Treat diseases and disorders of ear and hearing.");

        specialityNamesList.add("Physician");
        specialityDescriptionList.add("Treat diseases and disorders of internal structures of the body.");

        specialityNamesList.add("Ontology");
        specialityDescriptionList.add("Treat diseases and disorders of ear and hearing.");

        specialityNamesList.add("Physician");
        specialityDescriptionList.add("Treat diseases and disorders of internal structures of the body.");

        specialityNamesList.add("Ontology");
        specialityDescriptionList.add("Treat diseases and disorders of ear and hearing.");

        specialityNamesList.add("Physician");
        specialityDescriptionList.add("Treat diseases and disorders of internal structures of the body.");*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_sections, container, false);

        initDataset();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.doctor_categories_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.scrollToPosition(scrollPosition);



        return rootView;
    }

    @Override
    public void processFinish(ArrayList<String> specialityIdList,
                              ArrayList<String> specialityNameList,
                              ArrayList<String> specialityDescriptionList,
                              ArrayList<String> specialityIconUrlList,
                              ProgressDialog progressDialog) {
        this.specialityIdList = specialityIdList;
        this.specialityNamesList = specialityNameList;
        this.specialityDescriptionList = specialityDescriptionList;
        this.specialityIconUrlList = specialityIconUrlList;
        progressDialog.dismiss();

        mSpecialityAdapter = new SpecilaityAdapter(this.specialityIdList,this.specialityNamesList,
                this.specialityDescriptionList, this.specialityIconUrlList,getActivity());
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mSpecialityAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        //initDataset();
    }
}
