package com.example.android.lend;

import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MessageAdapter mMessageAdapter;
    private ListView mListView;
    private EditText mEditText;
    private Button mButton;
    private String user;

    private DBhelper helper;
    private SQLiteDatabase db;

    public void setMessageAdapter(MessageAdapter messageAdapter) {
        mMessageAdapter = messageAdapter;
    }

    public void setListView(ListView listView) {
        mListView = listView;
    }

    public void setEditText(EditText editText) {
        mEditText = editText;
    }

    public void setButton(Button button) {
        mButton = button;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public MessageAdapter getMessageAdapter() {
        return mMessageAdapter;
    }

    public ListView getListView() {
        return mListView;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public Button getButton() {
        return mButton;
    }

    public String isUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper=new DBhelper(getApplicationContext());
        db = helper.getWritableDatabase();

        mButton=(Button)findViewById(R.id.send);
        mListView=(ListView) findViewById(R.id.list);
        mEditText=(EditText) findViewById(R.id.message);
        mMessageAdapter = new MessageAdapter(this, R.layout.send);
        mListView.setAdapter(mMessageAdapter);
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

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = isUser();
                String mes= mEditText.getText().toString();

                Toast.makeText(getApplicationContext(),db.toString(),Toast.LENGTH_SHORT).show();
                db.execSQL("insert into msg_db(message) values(?)", new String[]{mes});

                //db.close();
                //finish();
            }
        });

    }

    //user - pass who's side makes the message and pass the message
    //Messages class sets direction and message then call to ArrayAdapter
    //MessageAdapter class loads from service and sets the data in listview
    private boolean sendMessage() {
        mMessageAdapter.add(new Messages(user, mEditText.getText().toString()));
        mEditText.setText("");
        if(!user.equals(user)) {
            return true;
        }else{
            return false;
        }
    }
}
