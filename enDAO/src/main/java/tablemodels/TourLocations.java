package tablemodels;

public class TourLocations {
    //instance vars
    private int id;

    private String city;

    //format ddmmyyyy
    private int day_and_month;

    //constructors
    public TourLocations(int id, String city, int day_and_month) {
        this.id = id;
        this.city = city;
        this.day_and_month = day_and_month;
    }

    public TourLocations(){}

    //getters and setters
    //format ddmmyyyy
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDay_and_month() {
        return day_and_month;
    }

    public void setDay_and_month(int day_and_month) {
        this.day_and_month = day_and_month;
    }

    @Override
    public String toString() {
        return "TourLocations{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", day_and_month=" + day_and_month +
                '}';
    }
}
