package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
                return true;
            case R.id.add:
                openNewActivity(-1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
