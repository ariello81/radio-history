package pl.ryzykowski.radiohistory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import pl.ryzykowski.radiohistory.dto.Station;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "odsluchane")
public class ConfigOdsluchane {

    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Station getStation(String stationId){
        return stations
                .stream()
                .filter(station -> station.getId().equals(stationId))
                .findFirst()
                .orElse(null);
    }
}
