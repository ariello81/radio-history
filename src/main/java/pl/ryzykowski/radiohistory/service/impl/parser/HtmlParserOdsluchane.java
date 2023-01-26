package pl.ryzykowski.radiohistory.service.impl.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryzykowski.radiohistory.config.ConfigOdsluchane;
import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.service.impl.parser.util.DatesUtilOdsluchane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class HtmlParserOdsluchane {

    private static final String URL = "https://www.odsluchane.eu/szukaj.php?";
    private static final String ARTIST_TITLE_SEPARATOR = " - ";

    private ConfigOdsluchane configOdsluchane;
    private DatesUtilOdsluchane datesUtilOdsluchane;

    @Autowired
    public HtmlParserOdsluchane(ConfigOdsluchane configOdsluchane, DatesUtilOdsluchane datesUtilOdsluchane) {
        this.configOdsluchane = configOdsluchane;
        this.datesUtilOdsluchane = datesUtilOdsluchane;
    }


    public List<Song> getSongsForStationAndDate(String stationId, LocalDate date, String timeFrom, String timeTo) {
        List<Song> songs = new ArrayList<>();
        try {
            songs = tryToGetSongsForStationAndDate(stationId, date, timeFrom, timeTo);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }


    private List<Song> tryToGetSongsForStationAndDate(String stationId, LocalDate date, String timeFrom, String timeTo) throws IOException {
        List<Song> songs = new ArrayList<>();
        String odsluchaneDate = datesUtilOdsluchane.odsluchaneDate(date);
        Document doc = Jsoup.connect(URL+"r="+stationId+"&date="+odsluchaneDate+"&time_from="+timeFrom+"&time_to="+timeTo).get();
        Iterator<Element> iterator = doc.getElementsByClass("title-link").iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.text().contains(ARTIST_TITLE_SEPARATOR)) {
                String artist = element.text().substring(0, element.text().indexOf(ARTIST_TITLE_SEPARATOR));
                String title = element.text().substring(element.text().indexOf(ARTIST_TITLE_SEPARATOR) + ARTIST_TITLE_SEPARATOR.length());
                String time = element.parent().firstElementSibling().text();
                songs.add(new Song(title, artist, configOdsluchane.getStation(stationId), date + " " + time));
            }
        }
        return songs;
    }

}
