package com.meagain.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    static final String EDITED_MOVIE = "EDITED_MOVIE";
    static final String EDITED_MOVIE_INDEX = "EDITED_MOVIE_INDEX";

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Movie movie = getIntent().getExtras().getParcelable(MainActivity.EDIT_MOVIE_EXTRA);
        index = getIntent().getExtras().getInt(MainActivity.EDIT_MOVIE_INDEX);
        try {
            ((Spinner) findViewById(R.id.genre_dropdown)).setSelection(movie.getGenre());
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!1" + e);
        }

        try {

            ((EditText) findViewById(R.id.year_edit_text)).setText(Integer.toString(movie.getYear()));
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!2" + e);
        }
        try {
            ((EditText) findViewById(R.id.imdb_edit_text)).setText(movie.getImdb());
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!3" + e);
        }
        try {
            ((EditText) findViewById(R.id.description_edit_text)).setText(movie.getDescription());
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!4" + e);
        }
        try {
            ((EditText) findViewById(R.id.name_edit_text)).setText(movie.getName());
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!5" + e);
        }
        try {
            ((SeekBar) findViewById(R.id.rating_seek_bar)).setProgress(movie.getRating());
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!6" + e);
        }
    }

    public void saveThisMovieButtonClicked(View view) {
        try {
            String name = ((EditText) findViewById(R.id.name_edit_text)).getText().toString();
            String description = ((EditText) findViewById(R.id.description_edit_text)).getText().toString();
            int rating = ((SeekBar) findViewById(R.id.rating_seek_bar)).getProgress();
            int genre = ((Spinner) findViewById(R.id.genre_dropdown)).getSelectedItemPosition();
            int year = Integer.parseInt(((EditText) findViewById(R.id.year_edit_text)).getText().toString());
            String imdb = ((EditText) findViewById(R.id.imdb_edit_text)).getText().toString();
            Movie movie = new Movie(description, genre, imdb, name, rating, year);
            System.out.println(movie);


            Intent intent = this.getIntent();
            intent.putExtra(EDITED_MOVIE_INDEX, index);
            intent.putExtra(EDITED_MOVIE, movie);
            this.setResult(RESULT_OK, intent);
            finish();

        } catch (Exception e) {
            System.out.println("Exception message: " + e);
            Toast.makeText(this, "Error, Check inputs", Toast.LENGTH_SHORT).show();
        }
    }
}
