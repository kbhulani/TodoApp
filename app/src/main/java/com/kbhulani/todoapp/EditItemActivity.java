package com.kbhulani.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {

    EditText etEditTodoItemText;
    Integer todoItemIndex;
    String todoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        getSupportActionBar().setTitle(R.string.edit_todo);


        etEditTodoItemText = (EditText) findViewById(R.id.etEditTodoItemText);
        todoItemIndex  = getIntent().getIntExtra(MainActivity.TODO_ITEM_KEY_INDEX, 0);
        todoItem = getIntent().getStringExtra(MainActivity.TODO_ITEM_KEY);
        etEditTodoItemText.setText(todoItem);
        etEditTodoItemText.setSelection(todoItem.length());
    }

    public void onEditItem(View view) {

        String editedTodoItem = etEditTodoItemText.getText().toString();
        if(editedTodoItem.length() > 0) {
            Intent anIntent = new Intent();
            // Pass relevant data back as a result
            anIntent.putExtra(MainActivity.TODO_ITEM_KEY, editedTodoItem);
            anIntent.putExtra(MainActivity.TODO_ITEM_KEY_INDEX, todoItemIndex);
            // Activity finished ok, return the data
            setResult(RESULT_OK, anIntent); // set result code and bundle data for response
            finish();
        } else {
            Toast.makeText(this, "Error: Empty Text", Toast.LENGTH_SHORT).show();
        }

    }
}
