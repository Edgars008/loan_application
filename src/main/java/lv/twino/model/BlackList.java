package lv.twino.model;

import javax.persistence.*;

@Entity
@Table(name = "blacklist")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public BlackList() {

    }

    public BlackList(Client client) {
        super();
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return String.format(
          "BlackList [id=%s, name=%s, surname=%s]", id, getClient().getName(), getClient().getSurname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlackList blackList = (BlackList) o;
        return id == blackList.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
