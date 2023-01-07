package controllers;

import interfaces.ControllerInterface;
import io.javalin.http.Context;
import services.SetListService;
import tablemodels.SetItem;

import java.util.List;

public class SetListController implements ControllerInterface<SetItem> {

    private SetListService setService;

    public SetListController(SetListService setService){
        this.setService = setService;
    }

    public void getAllRecords(Context ctx){
        List<SetItem> setItems = setService.getAllRecords();
        ctx.status(200);
        ctx.json(setItems);
    }

    public void getById(Context ctx){
        ctx.json(setService.getById(ctx));
    }
    public void getSetListItemById(Context ctx){
        try{
            ctx.json(setService.getById(ctx));
        } catch (NullPointerException e){
            ctx.result("That ID is not on the list");
        }

    }

    public void addNew(Context ctx){
        setService.addNew(ctx);
    }

    public void editById(Context ctx){
        setService.editById(ctx);
    }

    public void deleteById(Context ctx){
        setService.deleteById(ctx);
    }
    public void deleteAll(Context ctx){
        setService.deleteAll(ctx);
    }

}
