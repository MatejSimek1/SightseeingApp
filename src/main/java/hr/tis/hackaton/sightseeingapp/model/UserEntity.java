package hr.tis.hackaton.sightseeingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ENTITY")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "USER_FAVORITE_ATTRACTIONS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTRACTION_ID")
    )
    private List<Attraction> favoriteAttractions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Attraction> getFavoriteAttractions() {
        return favoriteAttractions;
    }

    public void setFavoriteAttractions(List<Attraction> favoriteAttractions) {
        this.favoriteAttractions = favoriteAttractions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(favoriteAttractions, that.favoriteAttractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, favoriteAttractions);
    }
}
