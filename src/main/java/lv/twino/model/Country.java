package lv.twino.model;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "countryName", nullable = false)
    private String countryName;


    public Country() {

    }

    public Country(String name) {
        super();
        this.countryName = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return String.format(
          "Country [id=%s, country=%s]", id, countryName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
