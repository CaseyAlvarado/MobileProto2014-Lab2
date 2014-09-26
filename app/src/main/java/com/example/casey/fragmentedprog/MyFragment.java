package com.example.casey.fragmentedprog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Created by casey on 9/11/14.
 */
public class MyFragment extends Fragment{
    Context context;
    String userId = "default";


    public MyFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //instantiate a chat object to put value, name, and time in

        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Chat Box");
        alert.setMessage("Please Insert Your Name");

        final EditText name = new EditText(context);
        alert.setView(name);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String value = name.getText().toString();
                userId = value;

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //operation has been cancelled.
            }
        });
        alert.show();


        //instantiate my Database class
        //Database db = new Database(getContext());
        //gets the data repository in write mode
        //SQLiteDatabase database = Database.getWriteableDatabase();
        //Log.d("one", "This is before view");
        View rootView = inflater.inflate(R.layout.fragment_fragmented_prog, container, false);
        ListView myListView = (ListView) rootView.findViewById(R.id.my_list_view);
//        final String[] listChats = {"Chat", "List", "So", "no"};
        //Log.d("three", "This is before array of chat objects");
        final ArrayList<ChatObject> listChats = new ArrayList<ChatObject>();
        //listChats.add(new ChatObject("hello", "me"));
        //listChats.add(new ChatObject("bye", "you"));

        //Log.d("here", listChats.toString());
        final ChatAdapter chatAdapter = new ChatAdapter(rootView.getContext(), R.layout.chat_item, listChats);
        //final ChatAdapter chatAdapter = new ChatAdapter();
        //ChatAdapter(getActivity(), R.layout.chat_item,listChats);

        //Log.d("five", "This is before text");
        final EditText text = (EditText) rootView.findViewById(R.id.my_edit_text);


        myListView.setAdapter(chatAdapter);

        Button myButton = (Button) rootView.findViewById(R.id.my_button);

        final String value = text.getText().toString();

        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //when button is clicked, this is what happens
                String value = text.getText().toString();
                //ChatObject chat = new ChatObject(value, "Kai");
                ChatObject chat = new ChatObject(value, userId, String.valueOf(System.currentTimeMillis()));
                listChats.add(chat);
                MainActivity.dbHandler.addToDatabase(chat);

                //Log.d("six", listChats.get(listChats.size()-1).message);
                text.setText("");
                chatAdapter.notifyDataSetChanged();

            }
        });

        return rootView;
    }



}
