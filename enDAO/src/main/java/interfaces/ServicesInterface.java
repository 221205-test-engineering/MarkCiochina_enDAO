package interfaces;

import io.javalin.http.Context;

import java.util.List;

public interface ServicesInterface<PlaceHolderForType> {

    List<PlaceHolderForType> getAllRecords();

    PlaceHolderForType getById(Context ctx);

    void addNew(Context ctx);

    void editById(Context ctx);

    void deleteById(Context ctx);

    void deleteAll(Context ctx);
}
