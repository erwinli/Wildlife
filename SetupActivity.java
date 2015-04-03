package com.li.tritonia.wildlife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class SetupActivity extends Activity {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private Button newGameBtn;
    private ArrayList<Hunt> huntList;
    private ArrayList<String> huntNameList;
    private int idCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        idCount = MainActivity.dataBase.numberOfHunts();

        Log.d("HuntCount", Integer.toString(idCount));

        newGameBtn = (Button)findViewById(R.id.newGameButton);
        listView = (ListView)findViewById(R.id.task_list);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent taskList = new Intent(SetupActivity.this, AddHuntActivity.class);

                startActivity(taskList);
            }
        });

    }

    public void initializeHuntList(){

        huntList = new ArrayList<Hunt>();
        huntNameList = new ArrayList<String>();

        for(int i = 0; i <= idCount; i++){

            Hunt obj = new Hunt();

            obj.setHuntId(Integer.parseInt(MainActivity.dataBase.getHuntId(i)));
            obj.setHuntName(MainActivity.dataBase.getHuntName(i));
            obj.setHuntDesc(MainActivity.dataBase.getHuntDesc(i));

            huntList.add(obj);

            String name = new String();

            name = MainActivity.dataBase.getHuntName(i);
            huntNameList.add(name);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        initializeHuntList();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, huntNameList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
