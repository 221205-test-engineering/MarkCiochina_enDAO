package services;

import daos.SongsDAO;
import interfaces.ServicesInterface;
import io.javalin.http.Context;
import tablemodels.Songs;

import java.util.List;

public class SongsService implements ServicesInterface<Songs> {
    private SongsDAO songDAO;

    public SongsService(SongsDAO songDAO){
        this.songDAO = songDAO;
    }

    public List<Songs> getAllRecords() {
        //for business logic can implement additional gatekeeping here
        //ie: check user credentials
        return songDAO.getAllRecords();
    }

    public Songs getById(Context ctx){
        return songDAO.getById(Integer.parseInt(ctx.pathParam("song_id")));
    }

    public void editById(Context ctx){
        songDAO.edit(ctx.bodyAsClass(Songs.class));
    }
    public void addNew(Context ctx){
        songDAO.add(ctx.bodyAsClass(Songs.class));
    }
    public void deleteAll(Context ctx){
        songDAO.resetToEmptyTable();
    }

    public void deleteById(Context ctx){
        songDAO.deleteById(Integer.parseInt(ctx.pathParam("id")));//must match the text inside the {} not what the field name of the table is
    }


}
