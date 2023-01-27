package pl.ryzykowski.radiohistory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import pl.ryzykowski.radiohistory.dto.StationDTO;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "odsluchane")
public class ConfigOdsluchane {

    private List<StationDTO> stationDTOS;

    public List<StationDTO> getStations() {
        return stationDTOS;
    }

    public void setStations(List<StationDTO> stationDTOS) {
        this.stationDTOS = stationDTOS;
    }

}
