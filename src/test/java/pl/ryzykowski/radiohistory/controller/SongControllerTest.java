package pl.ryzykowski.radiohistory.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.ryzykowski.radiohistory.dto.SongDTO;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.service.SongService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(SongController.class)
public class SongControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SongService songServiceOdsluchane;

    private List<SongDTO> songDTOS;

    @BeforeEach
    public void init(){
        songDTOS = List.of(
                new SongDTO("Fix You", "Coldplay", new StationDTO("2", "RMF FM"), "2022-12-03 10:06"),
                new SongDTO("Beautiful Day", "U2", new StationDTO("2", "RMF FM"), "2022-12-05 12:04")
        );
    }

    @Test
    public void songsStationForDateRangeTest() throws Exception {

        String stationId = "2";
        String dateFrom = "2022-12-01";
        String dateTo = "2022-12-15";

        //given
        given(songServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo))
                .willReturn(songDTOS);

        //when
        mvc.perform(
                get("/songs/{stationId}/{dateFrom}/{dateTo}", stationId, dateFrom, dateTo)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(songDTOS.size()));
    }

}
