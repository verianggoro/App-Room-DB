package com.pembelajar.noted;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.pembelajar.noted.model.Noted;
import com.pembelajar.noted.viewmodel.NotedViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailNoted extends AppCompatActivity implements View.OnClickListener {
    public static final String DATA = "data";
    public static final String FLAG = "Flag_From";

    private Button saved, delete;
    private TextInputEditText titleNoted;
    private EditText summary;
    private Noted noted = new Noted();

    private NotedViewModel viewModel;
    private int flag;
    private int idNote = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_noted);

        saved = findViewById(R.id.save_noted);
        titleNoted = findViewById(R.id.input_title);
        summary = findViewById(R.id.summary_note);
        delete = findViewById(R.id.delete_noted);

        viewModel = new ViewModelProvider(this).get(NotedViewModel.class);

        saved.setOnClickListener(this);
        delete.setOnClickListener(this);
        flag =  getIntent().getIntExtra(FLAG, 0);

        if (flag != 0){
            noted = (Noted) getIntent().getSerializableExtra(DATA);
            if (noted != null){
                titleNoted.setText(noted.getTitleNote());
                summary.setText(noted.getSummaryNote());
                idNote = noted.getNoteId();
            }else{
                titleNoted.setText("");
                summary.setText("");
            }
            delete.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_noted){
            if (titleNoted.getText() != null && summary.getText() != null){
                if (flag == 0){
                    noted.setTimeNoted(setTime());
                    noted.setTitleNote(titleNoted.getText().toString());
                    noted.setSummaryNote(summary.getText().toString());
                    viewModel.insert(noted);
                    Toast.makeText(this, "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.update(setTime(),
                            titleNoted.getText().toString(),
                            summary.getText().toString(),
                            idNote);
                    Toast.makeText(this, "Data Berhasil Diperbaharui", Toast.LENGTH_SHORT).show();
                }
                finish();
            }else{
                Toast.makeText(this, "Harap Diisi", Toast.LENGTH_SHORT).show();
            }
        }else if (view.getId() == R.id.delete_noted){
            viewModel.deletedNoted(noted);
            Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private String setTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formate = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String result = formate.format(calendar.getTime());
        return result;
    }

}
