package com.meagain.hw2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayListActivity extends AppCompatActivity {
    private Parcelable[] movies;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        if (getIntent().getExtras().getString(MainActivity.SORT_MOVIES_BY_TYPE).equals(MainActivity.MOVIE_TYPE_YEAR)) {
            setTitle(getString(R.string.yearTitle));
        } else if (getIntent().getExtras().getString(MainActivity.SORT_MOVIES_BY_TYPE).equals(MainActivity.MOVIE_TYPE_RATING)) {
            setTitle(getString(R.string.ratingTitle));
        } else {
            System.out.println("!!!!!!!!!!!!ERROR!!!!!!!!!!!!");
            finish();
        }

        movies = getIntent().getExtras().getParcelableArray(MainActivity.MOVIES);
        showMovie();
    }

    public void finishButtonClicked(View view) {
        finish();
    }


    private void showMovie() {
        try {
            Movie movie = (Movie) movies[index];
            System.out.println("!!!!!!!1Movi" + movie);

            try {
                ((TextView) findViewById(R.id.ratingTextView)).setText(Integer.toString(movie.getRating()));
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!1" + e);
                //
            }

            try {
                ((TextView) findViewById(R.id.titleTextView)).setText(movie.getName());
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!2" + e);
                //
            }

            try {
                ((TextView) findViewById(R.id.descriptionTextView)).setText(movie.getDescription());
            } catch (Exception e) {
                //
                System.out.println("!!!!!!!!!!!!3" + e);
            }
            try {
                ((TextView) findViewById(R.id.genreTextView)).setText(getResources().getStringArray(R.array.generes)[movie.getGenre()]);
            } catch (Exception e) {
                //
                System.out.println("!!!!!!!!!!!!4" + e);
            }

            try {
                ((TextView) findViewById(R.id.imdbTextView)).setText(movie.getImdb());
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!5" + e);
                //
            }

            try {
                ((TextView) findViewById(R.id.yearTextView)).setText(Integer.toString(movie.getYear()));
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!6" + e);
                //
            }

        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!" + e);
            Toast.makeText(this, "Error loading movie..", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void firstButtonClicked(View view) {
        index = 0;
        showMovie();
    }

    public void prevButtonClicked(View view) {
        if (index > 0) {
            index--;
        } else {
            index = movies.length - 1;
        }
        showMovie();
    }

    public void nextButtonClicked(View view) {
        if (index < movies.length - 1) {
            index++;
        } else {
            index = 0;
        }
        showMovie();
    }

    public void lastButtonClicked(View view) {
        index = movies.length - 1;
        showMovie();
    }
}
