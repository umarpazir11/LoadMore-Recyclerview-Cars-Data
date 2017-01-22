package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters.ModelRecyclerViewAdapter;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ManufacturerData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ModelData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.utils.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ModelItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView mModelRecyclerView;
    private List<ModelData> mModelDataList=new ArrayList<>();
    ManufacturerData mManufacturerData;
    String mId,mManufacturerName;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ModelItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ModelItemFragment newInstance(int columnCount) {
        ModelItemFragment fragment = new ModelItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            //mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mId = getArguments().getString("mManufacturerId");
            mManufacturerName = getArguments().getString("mManufacturerName");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_model_list, container, false);
        mModelRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        Context context = view.getContext();
        getActivity().setTitle(mManufacturerName);
        mModelRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        requestCarModelData();
        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new ModelRecyclerViewAdapter(DummyContent.ITEMS, mListener));
//        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ModelData item);
    }

    public void requestCarModelData(){
        String url = "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/main-types?manufacturer="+mId+"&page=0&pageSize=15&wa_key=coding-puzzle-client-449cc9d";
        mModelDataList.clear();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ModelData mModelData = null;
                            JSONObject jObjpages = response.getJSONObject("wkda");//get pages JSONObject
                            Iterator<?> keys = jObjpages.keys();
                            while( keys.hasNext() ){
                                String key = (String)keys.next();
                                mModelData= new ModelData(key,mId,mManufacturerName);
                                Log.i("keys",key);
                                mModelDataList.add(mModelData);
                            }

                            ModelRecyclerViewAdapter mModelRecyclerViewAdapter = new ModelRecyclerViewAdapter(mModelDataList,mListener);
                            mModelRecyclerView.setAdapter(mModelRecyclerViewAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                Toast.makeText(getActivity(), "Error :"+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjReq);
    }
}
