package com.meagain.hw2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    static final int ADD_MOVIE_REQUEST = 1;  // The request code
    static final int EDIT_MOVIE_REQUEST = 2;
    static final String EDIT_MOVIE_EXTRA = "EDIT_MOVIE";
    public static final String EDIT_MOVIE_INDEX = "EDIT_MOVIE_INDEX";
    public static final String SORT_MOVIES_BY_TYPE = "SORT_MOVIES_BY_TYPE";
    public static final String MOVIE_TYPE_YEAR = "YEAR";
    public static final String MOVIE_TYPE_RATING = "RATING";
    public static String MOVIES = "MOVIES";
    private ArrayList movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList();
    }

    public void addMovieButtonClicked(View view) {
        Intent intent = new Intent(this, AddMovieActivity.class);
        startActivityForResult(intent, ADD_MOVIE_REQUEST);
    }


    public void editMovieButtonClicked(View view) {
        String[] movies = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++) {
            movies[i] = ((Movie) movieList.get(i)).getName();
        }

        new AlertDialog.Builder(this)
                .setTitle("Pick a Movie").setItems(movies, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(EDIT_MOVIE_INDEX, which);
                intent.putExtra(EDIT_MOVIE_EXTRA, (Movie) movieList.get(which));
                startActivityForResult(intent, EDIT_MOVIE_REQUEST);
            }
        }).show();
    }

    public void deletebuttonClicked(View view) {
        String[] movies = new String[movieList.size()];
        for (int i = 0; i < movieList.size(); i++) {
            movies[i] = ((Movie) movieList.get(i)).getName();
        }

        new AlertDialog.Builder(this)
                .setTitle("Pick a Movie").setItems(movies, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Movie " + ((Movie) movieList.get(which)).getName() + " deleted.", Toast.LENGTH_SHORT).show();
                movieList.remove(which);
            }
        }).show();
    }

    public void showListByYearButtonClicked(View view) {
        if (movieList != null && movieList.size() > 0) {
            Movie[] movies = new Movie[movieList.size()];
            for (int i = 0; i < movieList.size(); i++) {
                movies[i] = (Movie) movieList.get(i);
            }

            Collections.sort(Arrays.asList(movies), new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getYear() - o2.getYear(); //ascending
                }
            });

            Intent intent = new Intent(MainActivity.this, DisplayListActivity.class);
            intent.putExtra(MOVIES, movies);
            intent.putExtra(SORT_MOVIES_BY_TYPE, MOVIE_TYPE_YEAR);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Create movies first!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showListByRatingButtonClicked(View view) {
        if (movieList != null && movieList.size() > 0) {
            Movie[] movies = new Movie[movieList.size()];
            for (int i = 0; i < movieList.size(); i++) {
                movies[i] = (Movie) movieList.get(i);
            }

            Collections.sort(Arrays.asList(movies), new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getRating() - o1.getRating(); //descending
                }
            });

            Intent intent = new Intent(MainActivity.this, DisplayListActivity.class);
            intent.putExtra(MOVIES, movies);
            intent.putExtra(SORT_MOVIES_BY_TYPE, MOVIE_TYPE_RATING);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Create movies first!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == ADD_MOVIE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Movie movie = data.getExtras().getParcelable(AddMovieActivity.NEW_MOVIE);
                movieList.add(movie);
                System.out.println("!!!!!!!!!! Movie added: " + movie);
                // Do something with the contact here (bigger example below)
            }
        } else if (requestCode == EDIT_MOVIE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Movie movie = data.getExtras().getParcelable(EditActivity.EDITED_MOVIE);
                int index = data.getExtras().getInt(EditActivity.EDITED_MOVIE_INDEX);
                movieList.set(index, movie);
                System.out.println("!!!!!!!!!! Movie edited: " + movie);
            } else {
                System.out.println("!!!!!!!!!! NOT OKAY");
            }
        }
        System.out.println("!!!!!!!!!! ");

    }
}
