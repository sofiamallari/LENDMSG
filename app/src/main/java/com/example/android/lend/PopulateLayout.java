package com.example.android.lend;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by SOFIA on 10/23/2017.
 */

public class PopulateLayout extends MainActivity implements View.OnClickListener{

    public void passMessage(){
        getEditText().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendMessage();
                }
                return false;
            }
        });

        getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        getListView().setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        getListView().setAdapter(getMessageAdapter());

        //scroll listview
        getMessageAdapter().registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                getListView().setSelection(getMessageAdapter().getCount()-1);
            }
        });


    }
    //user - pass who's side makes the message and pass the message
    //Messages class sets direction and message then call to ArrayAdapter
    //MessageAdapter class loads from service and sets the data in listview
    public boolean sendMessage() {
        getMessageAdapter().add(new Messages(isUser(), getEditText().getText().toString()));
        getEditText().setText("");
        setUser(!(isUser()));
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
