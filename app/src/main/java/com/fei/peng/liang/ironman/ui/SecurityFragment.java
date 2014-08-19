package com.fei.peng.liang.ironman.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.fei.peng.liang.ironman.R;
import com.fei.peng.liang.ironman.adapter.SecurityAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecurityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecurityFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SecurityFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context=this.getActivity();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DummyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecurityFragment newInstance(String param1, String param2) {
        SecurityFragment fragment = new SecurityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public SecurityFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_security, container, false);
        GridView gridView= (GridView) view.findViewById(R.id.gv_security);
        gridView.setAdapter(new SecurityAdapter(this.getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p=position+1;
                switch (p){
                    case 1:
                        Intent intent=new Intent(getActivity(),LostProtectedActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(getActivity(),LostProtectedActivity.class.toString(),Toast.LENGTH_LONG).show();
                        Intent intent2=new Intent(getActivity(),LostProtectedActivity.class);
                        startActivity(intent2);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("离开了Security");
        Toast.makeText(this.getActivity(),"你离开了Security",Toast.LENGTH_LONG).show();
    }
}
