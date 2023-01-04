package webapi;

import daos.TourLocationsDAO;
import io.javalin.Javalin;
import tablemodels.TourLocations;

import java.util.List;

public class WebAPI {

    private static String welcomePage = "<h1>Welcome to Band Manager</h1><h2>Here you will get to experience the high-flying career of managing your own coverband</h2>" +
            "</h4>Your job is to use Postman and this crude web API to schedule your band's engagements for the next year by adding tour dates and selecting a setlist of songs to play at each location</h4>" +
            "<br><p><b>To get started use the following instructions:</b></p><p>The addresses you can send or request information from are: <br> " +
            "<ul><li>'/' - the home page with instructions</li><br><li> '/songs' - where you can send GET a list of all playable songs or POST requests to add new songs to band catalog</li>" +
            "<br> <li> '/songs/{id}' where you can PUT new song info for an existing id, GET a specific song by id, or DELETE a song by id </li></ul>" +
            "<br>The same functionality is available for '/tourlocations' , '/tourlocations/{id}', '/setlist' and '/setlist/{id} " +
            "<br>where you can interact with the list of locations your band will play (/tourlocations) and the combination of songs and location they will be played on the setlist" +
            "<br><h3>Forms coming soon</h3>";

    private static String dataFormats = "<h3>To create or edit objects in any of the tables be sure to format request body data appropriately</h3>" +
            "<p><b>Use the following table descriptions to see how the data is structured:</b></p><ol><li><p>SONGS TABLE columns: song_id(int), title(String), duration(int)</li></p>" +
            "<li><p>Tour locations table: id(int), city(String), day_and_month (int format - mmddyyyy but all years should be 2023)</p></li>" +
            "<li><p>SetList table: song_id(int - references songs.id), title(String), show_id(int -references tourlocations.id). Only unique song_id-show_id combinations accepted</p></li></ol>";

    public static void main(String[] args){

        Javalin app = Javalin.create();

        app.get("/", ctx ->{
            ctx.html(welcomePage + dataFormats);
        }); // home page, graphical return of all tables

        //tour page
        app.get("/tourlocations", ctx->{//tour page, get all songs, when post = add new song, when edit change, when delete delete

            TourLocationsDAO tldao = new TourLocationsDAO();
            List<TourLocations> allLocations = tldao.getAllRecords();
            String longJSON ="";
            for(TourLocations t : allLocations){
                longJSON +=  t.getCity() + " , " + String.valueOf(t.getId());
            }
            ctx.html(longJSON);
            //ctx.json(allLocations);

        });
        app.post("/tourlocations", ctx->{}); //add new song method
        app.delete("/tourlocations", ctx->{}); //reset method to delete all songs
        //songsByID
        app.put("/tourlocations/{id}", ctx->{}); //edit song
        app.delete("/tourlocations/{id}", ctx->{}); // deletesong


        //songs page
        app.get("/songs", ctx->{}); //songs page, get all songs, when post = add new song, when edit change, when delete delete
        app.post("/songs", ctx->{}); //add new song method
        app.delete("/songs", ctx->{}); //reset method to delete all songs
            //songsByID
        app.put("/songs/{id}", ctx->{}); //edit song
        app.delete("/songs/{id}", ctx->{}); // deletesong


        //setlist page
        app.get("/setlist", ctx->{}); //songs page, get all songs, when post = add new song, when edit change, when delete delete
        app.post("/setlist", ctx->{}); //add new song method
        app.delete("/setlist", ctx->{}); //reset method to delete all songs
        //songsByID
        app.put("/setlist/{id}", ctx->{}); //edit song
        app.delete("/setlist/{id}", ctx->{}); // deletesong

        app.start(8080);
    }


}
