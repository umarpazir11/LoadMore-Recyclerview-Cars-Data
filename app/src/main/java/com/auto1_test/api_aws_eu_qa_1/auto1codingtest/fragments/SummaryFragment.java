package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;

public class SummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvManufacturer,tvModel,tvYear;
    private String mModel,mManufacturerName,mYear;

    public SummaryFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SummaryFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SummaryFragment newInstance(String param1, String param2) {
//        SummaryFragment fragment = new SummaryFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mModel = getArguments().getString("mModel");
            mManufacturerName = getArguments().getString("mManufacturerName");
            mYear= getArguments().getString("mYear");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        getActivity().setTitle("Summary");
        tvManufacturer = (TextView) view.findViewById(R.id.tv_manufacturer);
        tvModel = (TextView) view.findViewById(R.id.tv_model);
        tvYear = (TextView) view.findViewById(R.id.tv_year);

        tvManufacturer.setText(getResources().getString(R.string.manufacturer,mManufacturerName));
        tvModel.setText(getResources().getString(R.string.model,mModel));
        tvYear.setText(getResources().getString(R.string.year,mYear));

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
