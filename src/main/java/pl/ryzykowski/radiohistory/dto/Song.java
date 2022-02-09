package pl.ryzykowski.radiohistory.dto;

public class Song {

    private String title;
    private String artist;
    private Station station;
    private String dateTime;

    public Song() {
    }

    public Song(String title, String artist, Station station, String dateTime) {
        this.title = title;
        this.artist = artist;
        this.station = station;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
