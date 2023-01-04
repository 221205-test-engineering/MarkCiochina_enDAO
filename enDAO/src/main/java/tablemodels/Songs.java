package tablemodels;

public class Songs {

    private int song_id = 0;
    private String title = "";
    private int duration = 0;

    public Songs(){}

    public Songs(int song_id, String title, int duration){
        this.song_id = song_id;
        this.title = title;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "song_id=" + song_id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
