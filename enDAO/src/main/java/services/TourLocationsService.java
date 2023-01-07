package services;

import daos.TourLocationsDAO;
import interfaces.ServicesInterface;
import io.javalin.http.Context;
import tablemodels.TourLocations;

import java.util.List;

public class TourLocationsService implements ServicesInterface<TourLocations> {
    private TourLocationsDAO tourlocationsDAO;

    public TourLocationsService(TourLocationsDAO tourlocationsDAO){
        this.tourlocationsDAO = tourlocationsDAO;
    }

    public List<TourLocations> getAllRecords() {
        //for business logic can implement additional gatekeeping here
        //ie: check user credentials
        return tourlocationsDAO.getAllRecords();
    }

    public TourLocations getById(Context ctx){
        TourLocations newLoc = tourlocationsDAO.getById(Integer.parseInt(ctx.pathParam("id")));
        return newLoc;
    }

    public void addNew(Context ctx){
        tourlocationsDAO.add(ctx.bodyAsClass(TourLocations.class));
    }

    public void deleteAll(Context ctx){
        tourlocationsDAO.resetToEmptyTable();
    }

    public void deleteById(Context ctx){
        tourlocationsDAO.deleteById(Integer.parseInt(ctx.pathParam("id")));
    }

    public void editById(Context ctx){
        tourlocationsDAO.edit(ctx.bodyAsClass(TourLocations.class));
    }
}
