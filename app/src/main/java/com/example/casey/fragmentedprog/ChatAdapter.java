package com.example.casey.fragmentedprog;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by casey on 9/11/14.
 */
public class ChatAdapter extends ArrayAdapter<ChatObject>{
    Context context;

    int resource;
    List<ChatObject> chat;

    public ChatAdapter(Context context, int resource, List<ChatObject> objects) {
        super(context, resource, objects);
        Firebase myFirebaseRef = new Firebase("https://chatty-ducks.firebaseio.com/");
        this.context = context;
        this.resource = resource;
        this.chat = objects;
    }

    private class ViewHolder {
        public TextView message, userId, timestamp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("merp", "eij");
        ViewHolder viewHolder;

        Log.d("mep", "this is stupid");
        if (convertView == null) {
            Log.d("three", "This is the begining");
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chat_item, parent, false);


            /// = inflater.inflate(R.layout.chat_item, parent, false);

            viewHolder = new ViewHolder();
            //Log.d("text",chat.get(position).message);
            viewHolder.message = (TextView) convertView.findViewById(R.id.chat_text_view);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.userId = (TextView) convertView.findViewById(R.id.username);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
       }
        //viewHolder.userId.setText(chat.get(position).userId);
        viewHolder.userId.setText(chat.get(position).getSender());
        viewHolder.message.setText(chat.get(position).getMessage());
        viewHolder.timestamp.setText(chat.get(position).getTimestamp());
//        viewHolder.userId.setText();
//        viewHolder.message.setText(chat.get(position).message);
//        viewHolder.timestamp.setText(chat.get(position).time);

        return convertView;
    }
}
