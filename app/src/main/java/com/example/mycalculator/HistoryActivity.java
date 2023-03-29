package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private static List<String> history = new ArrayList<>();
    private static ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        historyListView.setAdapter(adapter);
    }

    public void addCalculationToHistory(String result) {
        history.add(result);
    }
}