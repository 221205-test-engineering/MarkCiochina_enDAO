package services;

import daos.SetListDAO;
import interfaces.ServicesInterface;
import io.javalin.http.Context;
import tablemodels.SetItem;

import java.util.List;

public class SetListService implements ServicesInterface<SetItem> {

    private SetListDAO setDAO;

    public SetListService(SetListDAO setDAO){
        this.setDAO = setDAO;
    }

    public List<SetItem> getAllRecords() {
        return setDAO.getAllRecords();
    }

    public SetItem getById(Context ctx){
        return setDAO.getById(Integer.parseInt(ctx.pathParam("id")));
    }

    public void addNew(Context ctx){
        setDAO.add(ctx.bodyAsClass(SetItem.class));
    }

    public void editById(Context ctx){
        setDAO.edit(ctx.bodyAsClass(SetItem.class));
    }

    public void deleteById(Context ctx){
        setDAO.deleteById(Integer.parseInt(ctx.pathParam("id")));
    }

    public void deleteAll(Context ctx){
        setDAO.resetToEmptyTable();
    }

}
