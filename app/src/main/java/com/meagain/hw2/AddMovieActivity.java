package com.meagain.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {
    static final String NEW_MOVIE = "NEW_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

    }

    public void addThisMovieButtonClicked(View view) {
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
            intent.putExtra(NEW_MOVIE, movie);
            this.setResult(RESULT_OK, intent);
            finish();

        } catch (Exception e) {
            System.out.println("Exception message: " + e);
            Toast.makeText(this, "Error, Check inputs", Toast.LENGTH_SHORT).show();
        }
    }
}
