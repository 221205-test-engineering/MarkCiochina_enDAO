package controllers;

import interfaces.ControllerInterface;
import io.javalin.http.Context;
import services.SongsService;
import tablemodels.Songs;

import java.util.List;


public class SongsController implements ControllerInterface {
    private SongsService songsService;

    public SongsController(SongsService songsService){
        this.songsService = songsService;
    }

    public void getAllRecords(Context ctx){ //Context class from JAVALIN
        List<Songs> allSongs = songsService.getAllRecords();
        ctx.status(200);
        ctx.json(allSongs);
    }

    public void getById(Context ctx){
        ctx.json(songsService.getById(ctx));
    }

    public void editById(Context ctx){
        songsService.editById(ctx);
    }

    public void deleteAll(Context ctx){
        songsService.deleteAll(ctx);
    }

    public void deleteById(Context ctx){
        songsService.deleteById(ctx);
        ctx.result("Song deleted");
    }
    public void addNew(Context ctx){
        songsService.addNew(ctx);
        ctx.result("song added");
    }



}
