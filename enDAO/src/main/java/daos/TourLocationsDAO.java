package daos;

import tablemodels.TourLocations;
import util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TourLocationsDAO implements DAOInterface<TourLocations> {

    private static ConnectionUtility connectObj = ConnectionUtility.getConnectionObject();

    public int generateNextId(){ //use to generate next highest ID so IDs maintain uniqueness
        String sql = "select id from tourlocations order by id desc";
        int newID = 0;
        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                newID = rs.getInt(1); //get first row value
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ++newID;
    }
    @Override
    public void add(TourLocations location){
        String sql = "insert into tourlocations values(?, ?, ?)";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, location.getId());
            ps.setString(2, location.getCity());
            ps.setInt(3, location.getDay_and_month());
            System.out.println(" inside add function Id, City and Date are : " + location.getId() + location.getCity() + location.getDay_and_month());
            ps.execute();
            System.out.println(("New location added to tour with ID : " + location.getId()));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "delete from tourlocations where id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Location successfully deleted by ID : " + String.valueOf(id));
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Exception trying to delete location with id" + String.valueOf(id));
        }

    }

    @Override
    public void edit(TourLocations location) {

        String sql = "update tourlocations city=?, day_and_month=? where id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, location.getCity());
            ps.setInt(2, location.getDay_and_month());
            ps.setInt(3, location.getId());
            ps.execute();
            System.out.println("Updated tour location with ID : " + location.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<TourLocations> getAllRecords() {
        List<TourLocations> allLocations = new ArrayList<>();

        String sql = "select * from tourlocations";

        System.out.println("Inside TourLocations.getAllRecords()");
        try(Connection connection = connectObj.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String city = rs.getString("city");
                int day_and_month = rs.getInt("day_and_month");

                TourLocations newLoc = new TourLocations(id, city, day_and_month);
                allLocations.add(newLoc);
            }
            System.out.println("All tour location records returned");
            return allLocations;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TourLocations getById(TourLocations location) {
        TourLocations targetLocation = new TourLocations();
        String sql = "select * from tourlocations where id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,location.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                targetLocation.setId(rs.getInt("id"));
                targetLocation.setCity(rs.getString("city"));
                targetLocation.setDay_and_month(rs.getInt("day_and_month"));
            }

            System.out.println("Tour location returned by ID : " + location.getId());
            return targetLocation;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //setlist == all songs for all tours, many rows that get added

    public void resetToEmptyTable(){
        String sql = "delete from tourlocations";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("Tour Locations table has been emptied");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
