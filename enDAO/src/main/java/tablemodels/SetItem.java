package tablemodels;

public class SetItem {
    private int song_id = 0;
    private String title = "";
    private int show_id = 0;

    public SetItem(){}

    public SetItem(Songs s, TourLocations l) { //overloaded constructor to assemble from a song and location object
        this.song_id = s.getSong_id();
        this.title = s.getTitle();
        this.show_id = l.getId();
    }

    public SetItem(int song_id, String title, int show_id) { //overloaded constructor to assemble from table results (in edit)
        this.song_id = song_id;
        this.title = title;
        this.show_id = show_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    @Override
    public String toString() {
        return "SetItem{" +
                "song_id=" + song_id +
                ", title='" + title + '\'' +
                ", show_id=" + show_id +
                '}';
    }
}
