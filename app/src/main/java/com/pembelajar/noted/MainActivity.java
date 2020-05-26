package com.pembelajar.noted;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pembelajar.noted.model.Noted;
import com.pembelajar.noted.viewmodel.NotedViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton added;
    private RecyclerView rvNoted;
    private NotedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        added = findViewById(R.id.added_noted);
        rvNoted = findViewById(R.id.rv_noted);
        final NotedAdapter adapter = new NotedAdapter(this);
        rvNoted.setLayoutManager(new LinearLayoutManager(this));
        rvNoted.setAdapter(adapter);

        added.setOnClickListener(this);
        viewModel = new ViewModelProvider(this).get(NotedViewModel.class);

        viewModel.getAllNoted().observe(this, new Observer<List<Noted>>() {
            @Override
            public void onChanged(List<Noted> noteds) {
                adapter.setNoted(noteds);
            }
        });

        adapter.setOnItemClickListener(new NotedAdapter.onItemCallback() {
            @Override
            public void itemClicked(Noted noted) {
                Intent intent = new Intent(getApplicationContext(), DetailNoted.class);
                intent.putExtra(DetailNoted.DATA, noted);
                intent.putExtra(DetailNoted.FLAG, 1);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.added_noted){
            Intent intent = new Intent(this, DetailNoted.class);
            intent.putExtra(DetailNoted.FLAG, 0);
            startActivity(intent);
        }
    }
}
