package interfaces;

import io.javalin.http.Context;
import tablemodels.Songs;

import java.util.List;

public interface ControllerInterface<PlaceholderForType> {

    void getAllRecords(Context ctx);

    void getById(Context ctx);

    void editById(Context ctx);

    void deleteAll(Context ctx);

    void deleteById(Context ctx);

    void addNew(Context ctx);

}
