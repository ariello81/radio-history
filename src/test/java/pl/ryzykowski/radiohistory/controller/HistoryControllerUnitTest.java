package pl.ryzykowski.radiohistory.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.dto.Station;
import pl.ryzykowski.radiohistory.service.HistoryService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(HistoryController.class)
public class HistoryControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HistoryService historyServiceOdsluchane;

    private List<Song> songs;

    @BeforeEach
    public void init(){
        songs = List.of(
                new Song("Fix You", "Coldplay", new Station("2", "RMF FM"), "2022-12-03 10:06"),
                new Song("Beautiful Day", "U2", new Station("2", "RMF FM"), "2022-12-05 12:04")
        );
    }

    @Test
    public void songsStationForDateRangeTest() throws Exception {

        String stationId = "2";
        String dateFrom = "2022-12-01";
        String dateTo = "2022-12-15";

        //given
        given(historyServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo))
                .willReturn(songs);

        //when
        mvc.perform(
                get("/api/{stationId}/{dateFrom}/{dateTo}", stationId, dateFrom, dateTo)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(songs.size()));
    }

}
