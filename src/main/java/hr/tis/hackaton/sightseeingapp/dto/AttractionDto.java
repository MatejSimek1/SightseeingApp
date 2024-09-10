package hr.tis.hackaton.sightseeingapp.dto;

import lombok.*;

import java.util.Objects;

public class AttractionDto {
    private String name;
    private String description;
    private String type;

    public AttractionDto(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public AttractionDto() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttractionDto that = (AttractionDto) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type);
    }

    @Override
    public String toString() {
        return "AttractionDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
