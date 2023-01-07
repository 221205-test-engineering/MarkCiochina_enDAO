package manager;

import daos.SetListDAO;
import daos.SongsDAO;
import daos.TourLocationsDAO;
import tablemodels.SetItem;
import tablemodels.Songs;
import tablemodels.TourLocations;

import java.util.Scanner;

public class ManagerApplication { //trial application for commandline

    public static void main(String[] args){
        TourLocationsDAO tldao = new TourLocationsDAO();
        SongsDAO sdao = new SongsDAO();
        SetListDAO setDAO = new SetListDAO();


        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
//        while(!userInput.equals("exit")){
//
//        }

        TourLocations newLoc = new TourLocations(tldao.generateNextId(), "denver", 11282022);
        tldao.add(newLoc);
        TourLocations secondStop = new TourLocations(tldao.generateNextId(), "memphis", 12012022);
        tldao.add(secondStop);
        System.out.println("newLoc id: " + newLoc.getId() + ". secondStop id: " + secondStop.getId());

        Songs s = new Songs(1, "Thunderstruck", 5);
        Songs s2 = new Songs(2, "Beast of Burden", 7);

        sdao.add(s);
        sdao.add(s2);


        SetItem i = new SetItem(s, newLoc);
        SetItem i2 = new SetItem(s, secondStop);
        SetItem i3 = new SetItem(s2, newLoc);
        SetItem i4 = new SetItem(s2, secondStop);
        setDAO.add(i);
        setDAO.add(i2);
        setDAO.add(i3);
        setDAO.add(i4);

    }
}
