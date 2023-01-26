package pl.ryzykowski.radiohistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.aop.Timed;
import pl.ryzykowski.radiohistory.config.ConfigOdsluchane;
import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.dto.StationArtistSummary;
import pl.ryzykowski.radiohistory.service.impl.parser.HtmlParserOdsluchane;
import pl.ryzykowski.radiohistory.service.HistoryService;
import pl.ryzykowski.radiohistory.service.impl.parser.util.DatesUtilOdsluchane;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HistoryServiceOdsluchane implements HistoryService {

    private HtmlParserOdsluchane htmlParserOdsluchane;
    private DatesUtilOdsluchane datesUtilOdsluchane;
    private ConfigOdsluchane configOdsluchane;

    @Autowired
    public HistoryServiceOdsluchane(HtmlParserOdsluchane htmlParserOdsluchane, DatesUtilOdsluchane datesUtilOdsluchane, ConfigOdsluchane configOdsluchane) {
        this.htmlParserOdsluchane = htmlParserOdsluchane;
        this.datesUtilOdsluchane = datesUtilOdsluchane;
        this.configOdsluchane = configOdsluchane;
    }


    @Override
    @Timed
    public List<Song> songsStationForDateRange(String stationId, String dateFrom, String dateTo) {
        List<Song> songs = new ArrayList<>();
        if (configOdsluchane.getStation(stationId) != null && datesUtilOdsluchane.validateDates(dateFrom, dateTo)) {
            List<LocalDate> dates = datesUtilOdsluchane.getAllDatesBetweenTwoDates(dateFrom, dateTo);
            final List<String> hours = Arrays.asList("0", "10", "20", "0");
            List<Thread> threads = new ArrayList<>();
            for (LocalDate date : dates) {
                for (int i = 0; i < hours.size() - 1; i++) {
                    String startHour = hours.get(i);
                    String stopHour = hours.get(i + 1);
                    Thread thread = new Thread(() -> {
                        songs.addAll(htmlParserOdsluchane.getSongsForStationAndDate(stationId, date, startHour, stopHour));
                    });
                    threads.add(thread);
                    thread.start();
                }
            }
            joinThreads(threads);
        }
        return songs
                .stream()
                .sorted(Comparator.comparing(Song::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public StationArtistSummary songsStationForDateRangeAndArtist(String stationId, String dateFrom, String dateTo, String artist) {
        List<Song> artistSongs = songsStationForDateRange(stationId, dateFrom, dateTo)
                .stream()
                .filter(song -> song.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
        StationArtistSummary stationArtistSummary = new StationArtistSummary();
        stationArtistSummary.setSongs(artistSongs);
        stationArtistSummary.setArtist(artist);
        stationArtistSummary.setDateFrom(dateFrom);
        stationArtistSummary.setDateTo(dateTo);
        stationArtistSummary.setStation(configOdsluchane.getStation(stationId));
        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
        artistSongs
                .stream()
                .collect(Collectors.groupingBy(song -> song.getTitle(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        stationArtistSummary.setDistinctTitles(sortedMap);
        return stationArtistSummary;
    }

    @Override
    public StationArtistSummary songsStationForArtistYearAndMonth(String stationId, String artist, String year, String month) {
        LocalDate localDateFrom = LocalDate.of (Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate localDateTo = localDateFrom.withDayOfMonth(localDateFrom.lengthOfMonth());
        return songsStationForDateRangeAndArtist(stationId, localDateFrom.toString(), localDateTo.toString(), artist);
    }

    @Override
    public void songsStationForArtistAndYears(String stationId, String artist, String yearStart, String yearStop) {
        int yearStartInt = Integer.parseInt(yearStart);
        int yearStopInt = Integer.parseInt(yearStop);
        for (int y=yearStartInt; y<=yearStopInt; y++){
            for (int m=1; m<=12; m++) {
                LocalDate localDateFrom = LocalDate.of (y, m, 1);
                if (localDateFrom.isAfter(LocalDate.now())) {
                    return;
                }
                LocalDate localDateTo = localDateFrom.withDayOfMonth(localDateFrom.lengthOfMonth());
                StationArtistSummary summary = songsStationForDateRangeAndArtist(stationId, localDateFrom.toString(), localDateTo.toString(), artist);
                System.out.println(y + " - " + m);
                Set<String> keys = summary.getDistinctTitles().keySet();
                for (String key : keys) {
                    System.out.println(key + ": " + summary.getDistinctTitles().get(key));
                }
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    private void joinThreads(List<Thread> threads){
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
