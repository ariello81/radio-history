package pl.ryzykowski.radiohistory.dto;

public class Song {

    private String title;
    private String artist;
    private StationDTO stationDTO;
    private String dateTime;

    public Song() {
    }

    public Song(String title, String artist, StationDTO stationDTO, String dateTime) {
        this.title = title;
        this.artist = artist;
        this.stationDTO = stationDTO;
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

    public StationDTO getStation() {
        return stationDTO;
    }

    public void setStation(StationDTO stationDTO) {
        this.stationDTO = stationDTO;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
