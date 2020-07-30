package com.rois.portfolio.cakra_tech.view;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rois.portfolio.cakra_tech.R;
import com.rois.portfolio.cakra_tech.adapter.RecyclerViewAdapter;
import com.rois.portfolio.cakra_tech.model.Film;
import com.rois.portfolio.cakra_tech.viewmodel.FilmViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LifecycleOwner, View.OnClickListener {
    MainActivity context;
    FilmViewModel viewModel;
    RecyclerView recyclerView;

    EditText editTextTitle, editTextDescription, editTextUrlImage;
    Button buttonConfirm;

    AlertDialog dialog;

    RecyclerViewAdapter recyclerViewAdapter;
    Observer<ArrayList<Film>> filmListUpdateObserver = new Observer<ArrayList<Film>>() {
        @Override
        public void onChanged(ArrayList<Film> filmArrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter(context, filmArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        recyclerView = findViewById(R.id.rv_main);
        findViewById(R.id.addButton).setOnClickListener(this);

        viewModel = ViewModelProviders.of(context).get(FilmViewModel.class);
        viewModel.getFilmMutableLiveData().observe(context, filmListUpdateObserver);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                AlertDialog.Builder alertBuilder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    alertBuilder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    alertBuilder = new AlertDialog.Builder(this);
                }

                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.new_film, null);

                editTextTitle = view.findViewById(R.id.editTextTitle);
                editTextDescription = view.findViewById(R.id.editTextDescription);
                editTextUrlImage = view.findViewById(R.id.editTextUrlImage);
                buttonConfirm = view.findViewById(R.id.buttonConfirm);
                buttonConfirm.setOnClickListener(this);

                alertBuilder.setView(view);
                alertBuilder.setCancelable(true);

                dialog = alertBuilder.create();
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            default:
                if (editTextTitle.getText() != null && editTextDescription.getText() != null && editTextUrlImage.getText() != null) {
                    try {
                        viewModel.setData(editTextTitle.getText().toString(), editTextDescription.getText().toString(), editTextUrlImage.getText().toString());
                        dialog.cancel();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Entry Data Film First!", Toast.LENGTH_SHORT).show();
                }

        }
    }
}