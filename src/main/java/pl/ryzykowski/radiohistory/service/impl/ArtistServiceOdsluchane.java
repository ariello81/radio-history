package pl.ryzykowski.radiohistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.aop.Timed;
import pl.ryzykowski.radiohistory.dto.SongDTO;
import pl.ryzykowski.radiohistory.dto.ArtistDTO;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.ArtistService;
import pl.ryzykowski.radiohistory.service.SongService;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ArtistServiceOdsluchane implements ArtistService {

    SongService songServiceOdsluchane;
    StationRepository stationRepository;

    @Autowired
    public ArtistServiceOdsluchane(SongService songServiceOdsluchane, StationRepository stationRepository) {
        this.songServiceOdsluchane = songServiceOdsluchane;
        this.stationRepository = stationRepository;
    }

    @Override
    @Timed
    public ArtistDTO artistSongsForStationAndDateRange(String stationId, String dateFrom, String dateTo, String artist) {
        List<SongDTO> artistSongDTOS = songServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo)
                .stream()
                .filter(song -> song.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setSongDTOS(artistSongDTOS);
        artistDTO.setArtist(artist);
        artistDTO.setDateFrom(dateFrom);
        artistDTO.setDateTo(dateTo);
        Station station = stationRepository.findOneByUrlId(stationId);
        artistDTO.setStation(new StationDTO(stationId, station.getName()));
        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();
        artistSongDTOS
                .stream()
                .collect(Collectors.groupingBy(song -> song.getTitle(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        artistDTO.setDistinctTitles(sortedMap);
        return artistDTO;
    }

    @Override
    @Timed
    public ArtistDTO artistSongsForStationYearAndMonth(String stationId, String artist, String year, String month) {
        LocalDate localDateFrom = LocalDate.of (Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate localDateTo = localDateFrom.withDayOfMonth(localDateFrom.lengthOfMonth());
        return artistSongsForStationAndDateRange(stationId, localDateFrom.toString(), localDateTo.toString(), artist);
    }

    @Override
    @Timed
    public void artistSongsForStationAndYears(String stationId, String artist, String yearStart, String yearStop) {
        int yearStartInt = Integer.parseInt(yearStart);
        int yearStopInt = Integer.parseInt(yearStop);
        for (int y=yearStartInt; y<=yearStopInt; y++){
            for (int m=1; m<=12; m++) {
                LocalDate localDateFrom = LocalDate.of (y, m, 1);
                if (localDateFrom.isAfter(LocalDate.now())) {
                    return;
                }
                LocalDate localDateTo = localDateFrom.withDayOfMonth(localDateFrom.lengthOfMonth());
                ArtistDTO summary = artistSongsForStationAndDateRange(stationId, localDateFrom.toString(), localDateTo.toString(), artist);
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

}
