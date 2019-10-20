package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainViewActivity extends AppCompatActivity {
    private Store store;
    private Model model;
    private EditText title;
    private EditText notes;
    private TextView time;
    private EditText tag;
    private Button button;

    private void getIdFromLayout() {
        title = findViewById(R.id.title);
        notes = findViewById(R.id.notes);
        time = findViewById(R.id.time);
        tag = findViewById(R.id.tag);
        button = findViewById(R.id.add);
    }

    private void setLayout() {
        title.setText(model.getTitle());
        notes.setText(model.getNotes());
        time.setText(DateFormat.getDateTimeInstance().format(model.getTime()));
        tag.setText(model.getTag());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        store = new Store(MainViewActivity.this);
        store.getStore();
        getIdFromLayout();
        int position = getIntent().getIntExtra("position", -1);
        if (position > -1) {
            model = store.modelList.get(position);
            setLayout();
        }
    }
}
