package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        final int position = getIntent().getIntExtra("position", -1);
        if (position > -1) {
            model = store.modelList.get(position);
            setLayout();
        } else {
            model = new Model("", "", null, "");
        }

        title.addTextChangedListener(textWatcher);
        notes.addTextChangedListener(textWatcher);
        tag.addTextChangedListener(textWatcher);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleEditText = title.getText().toString();
                String notesEditText = notes.getText().toString();
                Date timeTextView = Calendar.getInstance().getTime();
                String tagEditText = tag.getText().toString();

                if (position > -1) {
                    store.modelList.get(position).setTitle(titleEditText);
                    store.modelList.get(position).setNotes(notesEditText);
                    store.modelList.get(position).setTime(timeTextView);
                    store.modelList.get(position).setTag(tagEditText);
                } else {
                    store.modelList.add(0, new Model(titleEditText, notesEditText, timeTextView, tagEditText));
                }
                store.setStore();
                finish();
            }
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String titleTxt = title.getText().toString().trim();
            String notesTxt = notes.getText().toString().trim();
            String tagTxt = tag.getText().toString().trim();
            boolean onCreateItem = !titleTxt.isEmpty() && !notesTxt.isEmpty() && !tagTxt.isEmpty();
            boolean onSelectItem = (titleTxt.compareTo(model.getTitle()) != 0) || (notesTxt.compareTo(model.getNotes()) != 0) || (tagTxt.compareTo(model.getTag()) != 0);
            button.setEnabled(onCreateItem && onSelectItem);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
