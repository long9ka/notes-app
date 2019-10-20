package com.example.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    /**
     * @param position
     * -1 -> add item
     * else -> click item
     */
    private void openNewActivity(int position) {
        Intent intent = new Intent(this, MainViewActivity.class);
        intent.putExtra("position", position);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // handle code
        store = new Store(MainActivity.this);
        store.getStore();

        adapter = new ItemAdapter(this, R.layout.list_item, store.modelList);
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // show search bar & button
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_bar:
                openSearchView(item);
                return true;
            case R.id.add:
                openNewActivity(-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        recreate();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
