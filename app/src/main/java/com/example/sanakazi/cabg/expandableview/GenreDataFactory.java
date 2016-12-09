package com.example.sanakazi.cabg.expandableview;

import com.example.sanakazi.cabg.R;
import com.example.sanakazi.cabg.others.AboutUs;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

  public static List<Genre> makeGenres() {
    return Arrays.asList(makeRockGenre(),
        makeJazzGenre(),
        makeClassicGenre());
  }



  public static Genre makeRockGenre() {
    return new Genre("Our History", makeRockArtists(), R.drawable.dial);
  }


  public static List<Artist> makeRockArtists() {
    Artist queen = new Artist(AboutUs.history_html, true);


    return Arrays.asList(queen);
  }

  public static Genre makeJazzGenre() {
    return new Genre("What we do", makeJazzArtists(), R.drawable.dial);
  }



  public static List<Artist> makeJazzArtists() {
    Artist milesDavis = new Artist(AboutUs.what_we_do_html, true);
    return Arrays.asList(milesDavis);
  }

  public static Genre makeClassicGenre() {
    return new Genre("Our Credo", makeClassicArtists(), R.drawable.dial);
  }



  public static List<Artist> makeClassicArtists() {
    Artist beethoven = new Artist(AboutUs.our_credo_html, false);


    return Arrays.asList(beethoven);
  }


}

