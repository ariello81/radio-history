package pl.ryzykowski.radiohistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.aop.Timed;
import pl.ryzykowski.radiohistory.config.ConfigOdsluchane;
import pl.ryzykowski.radiohistory.dto.SongDTO;
import pl.ryzykowski.radiohistory.parser.HtmlParserOdsluchane;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.SongService;
import pl.ryzykowski.radiohistory.util.DatesUtilOdsluchane;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongServiceOdsluchane implements SongService {

    private HtmlParserOdsluchane htmlParserOdsluchane;
    private DatesUtilOdsluchane datesUtilOdsluchane;
    private StationRepository stationRepository;

    @Autowired
    public SongServiceOdsluchane(HtmlParserOdsluchane htmlParserOdsluchane, DatesUtilOdsluchane datesUtilOdsluchane, StationRepository stationRepository) {
        this.htmlParserOdsluchane = htmlParserOdsluchane;
        this.datesUtilOdsluchane = datesUtilOdsluchane;
        this.stationRepository = stationRepository;
    }


    @Override
    @Timed
    public List<SongDTO> songsStationForDateRange(String stationId, String dateFrom, String dateTo) {
        List<SongDTO> songDTOS = new ArrayList<>();
        if (stationRepository.findOneByUrlId(stationId)!= null && datesUtilOdsluchane.validateDates(dateFrom, dateTo)) {
            List<LocalDate> dates = datesUtilOdsluchane.getAllDatesBetweenTwoDates(dateFrom, dateTo);
            final List<String> hours = Arrays.asList("0", "10", "20", "0");
            List<Thread> threads = new ArrayList<>();
            for (LocalDate date : dates) {
                for (int i = 0; i < hours.size() - 1; i++) {
                    String startHour = hours.get(i);
                    String stopHour = hours.get(i + 1);
                    Thread thread = new Thread(() -> {
                        songDTOS.addAll(htmlParserOdsluchane.getSongsForStationAndDate(stationId, date, startHour, stopHour));
                    });
                    threads.add(thread);
                    thread.start();
                }
            }
            joinThreads(threads);
        }
        return songDTOS
                .stream()
                .sorted(Comparator.comparing(SongDTO::getDateTime))
                .collect(Collectors.toList());
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
