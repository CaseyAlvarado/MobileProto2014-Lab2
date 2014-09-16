package com.example.casey.fragmentedprog;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by casey on 9/11/14.
 */
public class MyFragment extends Fragment{
    public MyFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragmented_prog, container, false);
        ListView myListView = (ListView) rootView.findViewById(R.id.my_list_view);
//        final String[] listChats = {"Chat", "List", "So", "no"};
        final ArrayList<String> listChats = new ArrayList<String>();
        listChats.add("beeeee");
        listChats.add("uhdoiwoid");
        final ChatAdapter chatAdapter = new ChatAdapter(getActivity(), R.layout.texbox2, listChats);
        final EditText text = (EditText) rootView.findViewById(R.id.my_edit_text);
        myListView.setAdapter(chatAdapter);

        Button myButton = (Button) rootView.findViewById(R.id.my_button);
        final String value = text.getText().toString();
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = text.getText().toString();
                listChats.add(value);
                chatAdapter.notifyDataSetChanged();


            }
        });

        return rootView;
    }



}
