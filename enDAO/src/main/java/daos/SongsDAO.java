package daos;

import interfaces.DAOInterface;
import tablemodels.Songs;
import util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongsDAO implements DAOInterface<Songs> {

    private static ConnectionUtility connectObj = ConnectionUtility.getConnectionObject();
    @Override
    public void add(Songs s) {
        //song_id, title, duration
        String sql = "insert into songs values (?, ?, ?)";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, s.getSong_id());
            ps.setString(2, s.getTitle());
            ps.setInt(3, s.getDuration());
            ps.execute();
            System.out.println("Song with title '" + s.getTitle() + "' succesfully added");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from songs where song_id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("song deleted by ID : " + id);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Song failed to delete");
        }
    }

    @Override
    public void edit(Songs s) {

        String sql = "update songs set title=?, duration=? where song_id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, s.getTitle());
            ps.setInt(2, s.getDuration());
            ps.setInt(3, s.getSong_id());
            ps.execute();
            System.out.println("Song updated by ID : " + s.getSong_id());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Songs> getAllRecords() {

        String sql = "select * from songs";
        List<Songs> allSongs = new ArrayList<>();

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Songs n = new Songs(rs.getInt("song_id"), rs.getString("title"), rs.getInt("duration"));
                allSongs.add(n);
            }
            return allSongs;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Songs getById(int id) {

        String sql = "select * from songs where song_id=?";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Songs n = new Songs(rs.getInt("song_id"), rs.getString("title"), rs.getInt("duration"));
            return n;
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void resetToEmptyTable(){
        String sql = "delete from songs";

        try(Connection connection = connectObj.getConnection()){

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            System.out.println("Songs table has been emptied");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
