package com.example.spotifyapidashboard.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;


import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.io.IOException;
import java.text.ParseException;

import static com.example.spotifyapidashboard.web.AuthController.spotifyApi;

@RestController
@RequestMapping("/api")
public class SpotifyApiController {

    @GetMapping(value = "user-top-artists")
    public Artist[] getUserTopArtists(){

        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range("medium_term")
                .limit(10)
                .offset(5)
                .build();

        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();

            // return top artists as JSON
            return artistPaging.getItems();
        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
        return new Artist[0];
    }



    //public static void getCurrentUsersProfile_Sync() {
    //    final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile()
    //            .build();
    //    try {
    //        final User user = getCurrentUsersProfileRequest.execute();
//
    //        System.out.println("Display name: " + user.getDisplayName());
    //    } catch (IOException | SpotifyWebApiException | ParseException e) {
    //        System.out.println("Error: " + e.getMessage());
    //    }
    //}
}
