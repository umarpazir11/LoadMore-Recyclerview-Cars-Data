package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters.DataAdapter;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters.ManufactureRecyclerViewAdapter;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ManufacturerData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.utils.MySingleton;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.utils.OnLoadMoreListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ManufactureFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private List<ManufacturerData> mManufacturerDataList;
    Context context;
    private ManufactureRecyclerViewAdapter mManufactureRecyclerViewAdapter;



    private TextView tvEmptyView;
    private DataAdapter mAdapter;
    private int mStart=0,mEnd=15;
    private List<ManufacturerData> studentList;
    private List<ManufacturerData> mTempCheck;
    public static int pageNumber;
    public int total_size=0;
    protected Handler handler;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ManufactureFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ManufactureFragment newInstance(int columnCount) {
        ManufactureFragment fragment = new ManufactureFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manufacture_fragment_item_list, container, false);

        pageNumber = 1;
        tvEmptyView = (TextView) view.findViewById(R.id.empty_view);
        studentList = new ArrayList<>();
        mTempCheck=new ArrayList<>();
        mManufacturerDataList=new ArrayList<>();
        handler = new Handler();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        context = view.getContext();
        getActivity().setTitle("Manufacturers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //test();
        mAdapter = new DataAdapter(mManufacturerDataList, recyclerView,mListener);
        recyclerView.setAdapter(mAdapter);
        requestManufactureData("" + mStart, "" + mEnd);
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mTempCheck.size() > 0) {
                    mManufacturerDataList.add(null);
                    mAdapter.notifyItemInserted(mManufacturerDataList.size() - 1);
                    pageNumber++;
                    mTempCheck.clear();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            requestManufactureData("" + pageNumber, "" + mEnd);
                        }
                    }, 500);

                }
            }
        });
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
        void onListFragmentInteraction(ManufacturerData item);
    }

    public void requestManufactureData(final String LimitStart, final String LimitEnd){
        //String url = Utils.mServerUrl+"manufacturer";
        String url = "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/manufacturer?page="+LimitStart+"&pageSize="+LimitEnd+"&wa_key=coding-puzzle-client-449cc9d";
      //  mManufacturerDataList.clear();
        //String url = Utils.K_USER_BASE_URL;
//        Map<String, String>  params = new HashMap<>();
//        // the POST parameters
//            params.put("test", "123");
//        Map<String, String> params = new HashMap();
//        params.put("page", "0");
//        params.put("pageSize", "10");
        //params.put("Content-Type", "application/json; charset=utf-8");
        // params.put("Content-Type", "application/json");
        // String creds = String.format("wa_key","coding-puzzle-client-449cc9d");
        //String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
     //   params.put("wa_key", "coding-puzzle-client-449cc9d");
        //params.put("test", "123");
      //  JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ManufacturerData mData = null;
                           // Toast.makeText(getActivity(), "totalPageCount :"+response.getString("totalPageCount"), Toast.LENGTH_LONG).show();
                            JSONObject jObjpages = response.getJSONObject("wkda");//get pages JSONObject
                            Iterator<?> keys = jObjpages.keys();

                            if (pageNumber > 1 ) {
                                mManufacturerDataList.remove(mManufacturerDataList.size() - 1);
                                mAdapter.notifyItemRemoved(mManufacturerDataList.size());
                            }
                            while( keys.hasNext() ){

                                String key = (String)keys.next();
                                    mData= new ManufacturerData(key, jObjpages.getString(key));
                                    Log.i("keys",key);
                                    Log.i("keys",jObjpages.getString(key));
                                //mManufacturerDataList.add(mData);
                                if(! mTempCheck.contains(mData))
                                        mTempCheck.add(mData);
                                    if(! mManufacturerDataList.contains(mData))
                                        mManufacturerDataList.add(mData);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAdapter.notifyItemInserted(mManufacturerDataList.size());
                                    }
                                });
                            }
                            mAdapter.setLoaded(false);
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
        }) {

//            /**
//             * Passing some request headers
//             * */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("page", "0");
//                headers.put("pageSize", "10");
//                headers.put("wa_key", "coding-puzzle-client-449cc9d");
//                return headers;
//            }

        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjReq);
    }

    public void test(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
      // String url = Utils.mServerUrl+"manufacturer";
        String url = "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/manufacturer?page=0&pageSize=10&wa_key=coding-puzzle-client-449cc9d";
//        Map<String, String>  params = new HashMap<>();
//        params.put("test", "123");
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("response",response.toString());
                        Toast.makeText(getActivity(), "response :"+response.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        VolleyLog.e("ErrorTT: ", error.getMessage());
                        Toast.makeText(getActivity(), "ErrorTT :"+ error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        Volley.newRequestQueue(getActivity()).add(postRequest);
    }
}
