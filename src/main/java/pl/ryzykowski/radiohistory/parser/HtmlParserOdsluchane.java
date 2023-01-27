package pl.ryzykowski.radiohistory.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ryzykowski.radiohistory.config.ConfigOdsluchane;
import pl.ryzykowski.radiohistory.dto.SongDTO;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.util.DatesUtilOdsluchane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class HtmlParserOdsluchane {

    private static final String URL = "https://www.odsluchane.eu/szukaj.php?";
    private static final String ARTIST_TITLE_SEPARATOR = " - ";

    private StationRepository stationRepository;
    private DatesUtilOdsluchane datesUtilOdsluchane;

    @Autowired
    public HtmlParserOdsluchane(StationRepository stationRepository, DatesUtilOdsluchane datesUtilOdsluchane) {
        this.stationRepository = stationRepository;
        this.datesUtilOdsluchane = datesUtilOdsluchane;
    }


    public List<SongDTO> getSongsForStationAndDate(String stationId, LocalDate date, String timeFrom, String timeTo) {
        List<SongDTO> songDTOS = new ArrayList<>();
        try {
            songDTOS = tryToGetSongsForStationAndDate(stationId, date, timeFrom, timeTo);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return songDTOS;
    }


    private List<SongDTO> tryToGetSongsForStationAndDate(String stationId, LocalDate date, String timeFrom, String timeTo) throws IOException {
        List<SongDTO> songDTOS = new ArrayList<>();
        String odsluchaneDate = datesUtilOdsluchane.odsluchaneDate(date);
        Document doc = Jsoup.connect(URL+"r="+stationId+"&date="+odsluchaneDate+"&time_from="+timeFrom+"&time_to="+timeTo).get();
        Iterator<Element> iterator = doc.getElementsByClass("title-link").iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.text().contains(ARTIST_TITLE_SEPARATOR)) {
                String artist = element.text().substring(0, element.text().indexOf(ARTIST_TITLE_SEPARATOR));
                String title = element.text().substring(element.text().indexOf(ARTIST_TITLE_SEPARATOR) + ARTIST_TITLE_SEPARATOR.length());
                String time = element.parent().firstElementSibling().text();
                Station station = stationRepository.findOneByUrlId(stationId);
                songDTOS.add(new SongDTO(title, artist, new StationDTO(stationId, station.getName()),
                        date + " " + time));
            }
        }
        return songDTOS;
    }

}
