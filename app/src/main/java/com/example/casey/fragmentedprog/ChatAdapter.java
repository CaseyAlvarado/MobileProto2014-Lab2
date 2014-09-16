package com.example.casey.fragmentedprog;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by casey on 9/11/14.
 */
public class ChatAdapter extends ArrayAdapter<String>{


    public ChatAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

}
