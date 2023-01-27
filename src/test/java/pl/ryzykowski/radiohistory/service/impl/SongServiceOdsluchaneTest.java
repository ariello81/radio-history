package pl.ryzykowski.radiohistory.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ryzykowski.radiohistory.parser.HtmlParserOdsluchane;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.SongService;
import pl.ryzykowski.radiohistory.util.DatesUtilOdsluchane;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SongServiceOdsluchaneTest {

    @InjectMocks
    SongServiceOdsluchane songServiceOdsluchane;

    @Mock
    HtmlParserOdsluchane htmlParserOdsluchane;

    @Mock
    DatesUtilOdsluchane datesUtilOdsluchane;

    @Mock
    StationRepository stationRepository;

    @Test
    public void songsStationForDateRangeTest(){
    }

}