package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "Picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ATTRACTION_ID", referencedColumnName = "ID")
    private Attraction attraction;

    @Column(name = "PICTURE_PATH")
    private String  picturePath;

    public Picture(Long id, Attraction attraction, String picturePath) {
        this.id = id;
        this.attraction = attraction;
        this.picturePath = picturePath;
    }

    public Picture() {
    }


    public Long getId() {
        return id;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public String getPicturePath() {
        return picturePath;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) && Objects.equals(attraction, picture.attraction) && Objects.equals(picturePath, picture.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attraction, picturePath);
    }
}
