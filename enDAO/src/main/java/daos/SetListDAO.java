package daos;

import tablemodels.SetItem;
import util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SetListDAO implements DAOInterface<SetItem>{

    private static ConnectionUtility connectObj = ConnectionUtility.getConnectionObject();

    @Override
    public void add(SetItem s) {

        String sql = "insert into setlist values (?,?,?)";

        try(Connection connection = connectObj.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,s.getSong_id());
            ps.setString(2, s.getTitle());
            ps.setInt(3, s.getShow_id());
            ps.execute();
            System.out.println("Set item successfully added");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "delete from setlist where id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Set item successfully deleted by ID : " + String.valueOf(id));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void edit(SetItem s) {

        String sql = "select * from setlist where song_id=? and show_id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, s.getSong_id());
            ps.setInt(2,s.getShow_id());
            ps.execute();
            System.out.println("Set item edited by song_id : '" + s.getSong_id() + "', show_id : '" + s.getShow_id() + "'");

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<SetItem> getAllRecords() {

        List<SetItem> allSetPieces = new ArrayList<>();

        String sql = "select * from setlist";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SetItem n = new SetItem(rs.getInt("song_id"), rs.getString("title"), rs.getInt("show_id"));
                allSetPieces.add(n);
            }
            return allSetPieces;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SetItem getById(SetItem s) {

        String sql = "select * from setlist where song_id=?, show_id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, s.getSong_id());
            ps.setInt(2, s.getShow_id());
            ResultSet rs = ps.executeQuery();

            SetItem n = new SetItem(rs.getInt("song_id"), rs.getString("title"), rs.getInt("show_id"));
            return n;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void resetToEmptyTable(){
        String sql = "delete from setlist";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("Set list table has been emptied");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
