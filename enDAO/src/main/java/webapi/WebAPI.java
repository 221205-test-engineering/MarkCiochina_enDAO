package webapi;

import controllers.SetListController;
import controllers.SongsController;
import controllers.TourLocationsController;
import daos.SetListDAO;
import daos.SongsDAO;
import daos.TourLocationsDAO;
import io.javalin.Javalin;
import services.SetListService;
import services.SongsService;
import services.TourLocationsService;
import tablemodels.SetItem;
import tablemodels.Songs;
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
        TourLocationsDAO tldao = new TourLocationsDAO();
        TourLocationsService tourLocationsService = new TourLocationsService(tldao);
        TourLocationsController tourLocationsController = new TourLocationsController(tourLocationsService);

        SetListDAO setDAO = new SetListDAO();
        SetListService setListService = new SetListService(setDAO);
        SetListController setListController = new SetListController(setListService);

        SongsDAO songsDAO = new SongsDAO();
        SongsService songsService = new SongsService(songsDAO);
        SongsController songsController = new SongsController(songsService);

        Javalin app = Javalin.create();

        app.get("/", ctx ->{
            ctx.html(welcomePage + dataFormats);
        }); // home page, graphical return of all tables

        //tour page
        app.get("/tourlocations", tourLocationsController::getAllRecords);
        app.get("/tourlocations/{id}", tourLocationsController::getById);
        app.post("/tourlocations", tourLocationsController::addNew);
        app.delete("/tourlocations", tourLocationsController::deleteAll);
        app.put("/tourlocations/{id}", tourLocationsController::editById);
        app.delete("/tourlocations/{id}", tourLocationsController::deleteById);

        //songs page
        app.get("/songs", songsController::getAllRecords);
        app.get("/songs/{song_id}", songsController::getById);
        app.post("/songs", songsController::addNew);
        app.put("/songs/{id}", songsController::editById);
        app.delete("/songs", songsController::deleteAll);
        app.delete("/songs/{id}", songsController::deleteById);

        //setlist page
        app.get("/setlist", setListController::getAllRecords);
        app.get("/setlist/{id}", setListController::getSetListItemById);
        app.post("/setlist", setListController::addNew);
        app.put("/setlist", setListController::editById);
        app.delete("/setlist", setListController::deleteAll);
        app.delete("/setlist{id}", setListController::deleteById);

        app.start(8080);

    }
}

        //old codes for reference
//                ctx->{
//            List<Songs> allsongs = songsDAO.getAllRecords();
//            String longJSON ="";
//            for(Songs s : allsongs){
//                longJSON +=  "id : " +String.valueOf(s.getSong_id()) + ", title : " + s.getTitle() + ", duration : " + String.valueOf(s.getDuration() +"<br>");
//            }
//            ctx.html("<h1><u>All songs</u></h1><br>" + longJSON);
//        }); //songs page, get all songs, when post = add new song, when edit change, when delete delete
//



//                ctx->{ //get song by id   // part in params needs/does not need to match the column table name
//            String id = ctx.pathParam("song_id");
//            System.out.println("the path param for {song id} is : " + id);
//            if(!id.equals("")){
//                ctx.json(songsDAO.getById(Integer.parseInt(id)));
//            } else {
//                ctx.result("that id does not exist");
//            }

//        app.post("/songs", ctx->{
//            Songs newSong = ctx.bodyAsClass(Songs.class);
//            songsDAO.add(newSong);
//            System.out.println("New song added with title: " + newSong.getTitle() + ", and id : " + String.valueOf(newSong.getSong_id()));
//        }); //add new song method
//        app.put("/songs/{id}", ctx->{//edit song
//            Songs editSong = ctx.bodyAsClass(Songs.class);
//            songsDAO.edit(editSong);
//            System.out.println("Song with id : " + String.valueOf(editSong.getSong_id()) + " has been edited");
//
//        });
//
//        app.delete("/songs", ctx->{ //reset method to delete all songs -- Will not work unless first deleted from setlist, include logic to delete from setlist automatically before deleting from songs
//            songsDAO.resetToEmptyTable();
//            System.out.println("You have emptied the songs table :(");
//        });
//
//        app.delete("/songs/{id}", ctx->{// delete specific song -- Will not work unless first deleted from setlist, include logic to delete from setlist automatically before deleting from songs
//            String idNum = ctx.pathParam("id");
//            System.out.println("idNum = " + idNum);
//            if(idNum != null) {
//                songsDAO.delete(Integer.parseInt(idNum));
//                System.out.println("Song with id : " + idNum + " is has been removed from the table");
//            } else {
//                ctx.html("No id submitted, please add an id");
//            }
//
//        });


//        //setlist page
//        app.get("/setlist", );

//    ctx->{
//            List<SetItem> fullSetList = setDAO.getAllRecords();
//            String longJSON ="";
//            for(SetItem s : fullSetList){
//                longJSON +=  "song_id : " +String.valueOf(s.getSong_id()) + ", title : " + s.getTitle() + ", show_id : " + String.valueOf(s.getShow_id());
//            }
//            ctx.html(longJSON);
//        });
//
//        app.post("/setlist", ctx->{
//            SetItem newSetItem = ctx.bodyAsClass(SetItem.class);
//            setDAO.add(newSetItem);
//            System.out.println("new set item added");
//        });
//
//
//        app.delete("/setlist", ctx->{});
//        //songsByID
//        app.put("/setlist/{id}", ctx->{});
//        app.delete("/setlist/{id}", ctx->{
//
//        });


