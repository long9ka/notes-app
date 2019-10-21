package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private Store store;
    private ItemAdapter adapter;
    private ListView listView;

    private void openNewActivity(int position) {
        startActivity(new Intent(this, MainViewActivity.class).putExtra("position", position));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store = new Store(MainActivity.this);
    }

    @Override
    protected void onStart() {
        store.getStore();
        super.onStart();
    }

    @Override
    protected void onResume() {
        adapter = new ItemAdapter(this, R.layout.list_item, store.modelList);
        listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_bar) {
            openSearchView(item);
        } else if (item.getItemId() == R.id.add) {
            openNewActivity(-1);
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSearchView(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
