package pl.ryzykowski.radiohistory.entity;

import javax.persistence.*;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;

    public Station() {
    }

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
