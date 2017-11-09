package com.example.android.lend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SOFIA on 10/23/2017.
 */

class MessageAdapter extends ArrayAdapter<Messages>{
    private TextView chat;
    private List<Messages> list = new ArrayList<Messages>();
    private Context context;

    @Override
    public void add(Messages object) {
        list.add(object);
        super.add(object);
    }
    public MessageAdapter(Context context, int resourceId) {
        super(context, resourceId);
        this.context=context;
    }

    public int getCount() {
        return this.list.size();
    }

    public Messages getItem(int index) {
        return this.list.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Messages messageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (messageObj.user) {
            row = inflater.inflate(R.layout.send, parent, false);
        }else{
            row = inflater.inflate(R.layout.receive, parent, false);
        }
        chat= (TextView) row.findViewById(R.id.msg);
        chat.setText(messageObj.message);
        return row;
    }
}
