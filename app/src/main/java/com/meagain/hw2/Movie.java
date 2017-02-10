package com.meagain.hw2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Megan on 1/26/2017.
 */

public class Movie implements Parcelable {
    private String name;
    private String description;
    private int year;
    private int rating;
    private int genre;
    private String imdb;

    public Movie(String description, int genre, String imdb, String name, int rating, int year) {
        this.description = description;
        this.genre = genre;
        this.imdb = imdb;
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        description = in.readString();
        year = in.readInt();
        rating = in.readInt();
        genre = in.readInt();
        imdb = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public String toString() {
        return "Movie{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", imdb='" + imdb + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(year);
        dest.writeInt(rating);
        dest.writeInt(genre);
        dest.writeString(imdb);
    }
}
