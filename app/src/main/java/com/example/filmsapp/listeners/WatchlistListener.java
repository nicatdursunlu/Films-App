package com.example.filmsapp.listeners;

import com.example.filmsapp.models.TVShow;

public interface WatchlistListener {

    void onTVShowClicked(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow, int position);

}
