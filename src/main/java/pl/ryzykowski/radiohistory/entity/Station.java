package pl.ryzykowski.radiohistory.entity;

import javax.persistence.*;

@Entity(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="url_id")
    private String urlId;
    @Column(name="name")
    private String name;

    public Station() {
    }

    public Station(Long id, String urlId, String name) {
        this.id = id;
        this.urlId = urlId;
        this.name = name;
    }

    public Station(String urlId, String name) {
        this.urlId = urlId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
