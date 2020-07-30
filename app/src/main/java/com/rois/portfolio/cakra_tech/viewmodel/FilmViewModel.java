package com.rois.portfolio.cakra_tech.viewmodel;

import android.text.Editable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rois.portfolio.cakra_tech.model.Film;

import java.util.ArrayList;

public class FilmViewModel extends ViewModel {
    MutableLiveData<ArrayList<Film>> filmLiveData;
    ArrayList<Film> filmArrayList;

    public FilmViewModel() {
        filmLiveData = new MutableLiveData<>();
        filmArrayList = new ArrayList<>();;
        init();
    }

    public MutableLiveData<ArrayList<Film>> getFilmMutableLiveData() {
        return filmLiveData;
    }

    public void init(){
        setData();
        filmLiveData.setValue(filmArrayList);
    }

    public void setData(){

        Film film = new Film();
        film.setTitle("Darknight");
        film.setDescription("Best rating movie");
        film.setImgIcon("https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png");

        filmArrayList.add(film);
    }

    public void setData(String title, String desc, String url) {
        Film film = new Film();
        film.setTitle(title);
        film.setDescription(desc);
        film.setImgIcon(url);

        filmArrayList.add(film);
    }
}
