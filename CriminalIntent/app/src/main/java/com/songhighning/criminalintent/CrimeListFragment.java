package com.songhighning.criminalintent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Alex on 2016-04-27.
 */
public class CrimeListFragment extends ListFragment {
    private static final String TAG = "AlexMessage";
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        /*ArrayAdapter<Crime> adapter =
                new ArrayAdapter<Crime>(getActivity(),
                                        android.R.layout.simple_list_item_1,
                                        mCrimes);*/
        //setListAdapter method is a ListFragment convenience method
        // used to set the adapter of the implicit ListView managed by CrimeListFragment
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        //Crime c = (Crime)(getListAdapter()).getItem(position);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + "was clicked");
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>{

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //if no view, inflate one
            if (convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;

        }
    }
}
