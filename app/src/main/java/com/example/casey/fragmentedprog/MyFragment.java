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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Log.d("New Insert", "I just want to know where my code ends");
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


        //final Firebase myFirebaseRef = new Firebase("https://chatty-ducks.firebaseio.com/");
        Firebase.setAndroidContext(context);
        final Firebase myFirebaseRef = new Firebase("https://mobileproto2014.firebaseio.com/chatroom/0");


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

                //adding chats to local database
                MainActivity.dbHandler.addToDatabase(chat);
                //myFirebaseRef.child("message").setValue(chat);
                //Log.d("six", listChats.get(listChats.size()-1).message);

                Firebase postRef = myFirebaseRef;

                Map<String, String> post1 = new HashMap<String, String>();
                post1.put("username", chat.sender);
                post1.put("message", chat.message);
                post1.put("timestamp", chat.timestamp);
                postRef.push().setValue(post1);


                //clear message
                text.setText("");
                chatAdapter.notifyDataSetChanged();

            }
        });

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Log.d("Chats from firebase", dataSnapshot.getValue().toString());
                    //ChatObject chat = new ChatObject(null, null, null);
                    ChatObject chat = new ChatObject(dataSnapshot.child("username").getValue().toString(), dataSnapshot.child("message").getValue().toString(), dataSnapshot.child("time").getValue().toString());
                    //chat.message = dataSnapshot.child("message").getValue().toString();
                    //chat.sender = dataSnapshot.child("username").getValue().toString();
                    //chat.timestamp = dataSnapshot.child("timestamp").getValue().toString();
                    listChats.add(chat);
                    MainActivity.dbHandler.addToDatabase(chat);
                    chatAdapter.notifyDataSetChanged();
                } catch (Exception E) {

                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return rootView;
    }



}
