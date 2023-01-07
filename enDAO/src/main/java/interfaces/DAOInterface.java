package interfaces;

import java.util.List;

//created to ensure all DAOs extending this implement CRUD methods
public interface DAOInterface<PlaceholderForType> {

    void add(PlaceholderForType p);

    void deleteById(int id);

    void edit(PlaceholderForType p);

    List<PlaceholderForType> getAllRecords();

    PlaceholderForType getById(int id);

}
