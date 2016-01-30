package com.kbhulani.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddItem;
    ListView lvItems;
    EditText etEditText;

    ArrayList<String> todoItems;
    ArrayAdapter<String> todoItemsAdapter;

    public final int REQUEST_CODE_EDIT = 100;
    public static final String TODO_ITEM_KEY = "todoItem";
    public static final String TODO_ITEM_KEY_INDEX = "index";
    public static final String TODO_FILE = "todo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems = (ListView) findViewById(R.id.lvItems);

        setupTodoList();
        setupListViewListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EDIT) {
            String todoItem = data.getStringExtra(TODO_ITEM_KEY);
            int todoItemIndex = data.getIntExtra(TODO_ITEM_KEY_INDEX, -1);

            if (todoItemIndex >= 0) {
                todoItems.set(todoItemIndex, todoItem);
                todoItemsAdapter.notifyDataSetChanged();
                writeItems();
            }
        }

        lvItems.setClickable(true);
    }


    private void setupTodoList() {
        readItems();
        todoItemsAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, todoItems);
        lvItems.setAdapter(todoItemsAdapter);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                todoItemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent anIntent = new Intent(MainActivity.this, EditItemActivity.class);
                anIntent.putExtra(TODO_ITEM_KEY_INDEX, position);
                anIntent.putExtra(TODO_ITEM_KEY, todoItems.get(position));
                startActivityForResult(anIntent, REQUEST_CODE_EDIT);
            }
        });
    }

    public void onAddItem(View view) {
        String todoItem = etEditText.getText().toString();
        if (todoItem.length() > 0) {
            todoItemsAdapter.add(todoItem);
            etEditText.setText("");
            writeItems();
        }
        else {
            Toast.makeText(this, R.string.errorSave, Toast.LENGTH_SHORT).show();
        }
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, TODO_FILE);
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            todoItems = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, TODO_FILE);
        try {
            FileUtils.writeLines(todoFile, todoItems);
            Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }


}
