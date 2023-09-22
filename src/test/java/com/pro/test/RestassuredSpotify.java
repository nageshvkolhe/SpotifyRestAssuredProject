package com.pro.test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestassuredSpotify {
    public String token = "";
    public static String userId;
    public static String playlistId;

    @BeforeTest
    public void setUp(){
        token = "Bearer BQAsXGWkN5_k3SRS2pP9yeKb7QqXuh9gqhDhrKrEoIhakmn7PWyAHZuszdKZueq2nLpTShEAaHURaecshFbTpwGhYnRj6JPWCt-3UmYwde4aOsikQGLzjL1JMF5i9zzQ-IFBMXDYas1Ayvhml7FPx8IWg0y-ICirYFuteyR5NC2F1HpcGqaHgSyhBmUCF-7Gm74DIIqcnyBXsIHKVbuIe3BKomkJLWqq_iM36imuuRu9XUAFTVLMqGbVQVt6VFwXn_m4uxYLJLnfRT380phy2QyBtv4D70T-n7GzzdJGYEHg";
    }

    @Test(priority = 0)
    public void User_Current_Profile(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me");
        System.out.println("Response print using sout: " +response);
        userId = response.path("id");
        System.out.println("User Id :"+userId);
        response.prettyPrint();
    }

    @Test(priority = 1)
    public void Users_Profile(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId);
        response.prettyPrint();
    }

    @Test(priority = 2)    //_______________remaining
    public void List_of_Current_Users_Playlists(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        System.out.println("Response print using sout: " +response);
        playlistId = response.path("");
        System.out.println("Playlist Id :"+playlistId);
        response.prettyPrint();
    }

    @Test(priority = 3)
    public void List_of_Users_Playlists(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/playlists");

        response.prettyPrint();
    }

    @Test(priority = 4)
    public void Playlist(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/playlists/31DSgZVLJLuYYdmdL7HDmr");
        response.prettyPrint();
    }

    @Test(priority = 5)
    public void Playlist_items(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("\thttps://api.spotify.com/v1/playlists/31DSgZVLJLuYYdmdL7HDmr/tracks");
        response.prettyPrint();
    }

    @Test(priority = 6)
    public void Playlist_Cover_Image(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("\thttps://api.spotify.com/v1/me/31DSgZVLJLuYYdmdL7HDmr");
        response.prettyPrint();
    }

    @Test(priority = 7)  //remaining
    public void Add_Items_to_Playlist(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .body("")
                .post("https://api.spotify.com/v1/playlists/31DSgZVLJLuYYdmdL7HDmr/tracks");
        response.prettyPrint();
    }

    @Test(priority = 8)
    public void Create_Playlist(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .body("{\n" +
                        "   \"name\": \"nagesh moody\",\n" +
                        "   \"description\": \"New playlist description\",\n" +
                        "\"public\": false};}")
                .when()
                .post("https://api.spotify.com/v1/users/316pgn7hgnbehzs4mnxypxxaoz6e/playlists");
        response.prettyPrint();
    }

    @Test(priority = 9)
    public void custom_playlist_Cover_Image(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .put("https://api.spotify.com/v1/playlists/31DSgZVLJLuYYdmdL7HDmr/C:\\Users\\User\\Pictures\\capture_20201231002312.png");
        response.prettyPrint();
    }

    @Test(priority = 10)
    public void Reorder_or_Replace_Playlists_Items(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .put("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
    }

    @Test(priority = 11)
    public void Change_Playlists_Details(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .body("{\n" +
                        "  \"name\": \"Updated Playlist Name\",\n" +
                        "  \"description\": \"Updated playlist description\",\n" +
                        "  \"public\": false\n" +
                        "}")
                .put("https://api.spotify.com/v1/playlists/{playlist_id}");
        response.prettyPrint();
    }

    @Test(priority = 12)
    public void Remove_Items_from_Playlist(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .body("{\n" +
                        "  \"tracks\": [\n" +
                        "    {\n" +
                        "      \"uri\": \"spotify:track:2DB2zVP1LVu6jjyrvqD44z\",\n" +
                        "      \"positions\": [\n" +
                        "        0\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"uri\": \"spotify:track:5ejwTEOCsaDEjvhZTcU6lg\",\n" +
                        "      \"positions\": [\n" +
                        "        1\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .delete("https://api.spotify.com/v1/playlists/{playlist_id}/tracks");
        response.prettyPrint();
    }

    @Test(priority = 13)
    public void Get_Audio_Analysis_for_Track(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/{id}");
        response.prettyPrint();
    }

    @Test(priority = 14)
    public void Get_Audio_Features_for_Several_Tracks(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/audio-features");
        response.prettyPrint();
    }

    @Test(priority = 15)
    public void Get_Audio_Features_for_Track(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/{id}");
        response.prettyPrint();
    }

    @Test(priority = 16)
    public void Get_Several_Tracks(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/tracks");
        response.prettyPrint();
    }

    @Test(priority = 17)
    public void Get_a_Track(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/tracks/{id}");
        response.prettyPrint();
    }

    @Test(priority = 18)
    public void Search_for_an_Item(){
        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/search");
        response.prettyPrint();
    }

}