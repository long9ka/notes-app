package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openNewActivity(adapter.getRealPos().get(position));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(store.modelList.get(position).getTitle())
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                store.getStore();
                                store.modelList.remove(store.modelList.get(adapter.getRealPos().get(position).intValue()));
                                adapter.notifyDataSetChanged();
                                store.setStore();
                                recreate();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

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
