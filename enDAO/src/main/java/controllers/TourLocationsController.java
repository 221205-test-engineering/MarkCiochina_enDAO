package controllers;

import interfaces.ControllerInterface;
import io.javalin.http.Context;
import services.TourLocationsService;
import tablemodels.TourLocations;

import java.util.List;


public class TourLocationsController implements ControllerInterface<TourLocations> {
    private TourLocationsService tourService;
    public TourLocationsController(TourLocationsService tourService){
        this.tourService = tourService;
    }

    public void getAllRecords(Context ctx){ //Context class from JAVALIN
        List<TourLocations> tourLocations = tourService.getAllRecords();
        ctx.status(200);
        ctx.json(tourLocations);
    }
    public void getById(Context ctx){
            ctx.json(tourService.getById(ctx));
    }
    public void addNew(Context ctx){
        tourService.addNew(ctx);
        ctx.result("new location posted");
    }
    public void deleteAll(Context ctx){
        tourService.deleteAll(ctx);
    }

    public void deleteById(Context ctx){
        tourService.deleteById(ctx);
    }
    public void editById(Context ctx){
        tourService.editById(ctx);
    }
}
