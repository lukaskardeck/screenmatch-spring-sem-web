package br.com.lukas.screenmatch.model;

import java.time.LocalDate;

public class Episode {

    private String title;
    private Integer season;
    private Integer number;
    private Double rating;
    private LocalDate dateOfRelease;

    public Episode(Integer season, EpisodeData epData) {
        this.season = season;
        this.title = epData.title();
        this.number = epData.number();
        this.rating = (epData.rating().equalsIgnoreCase("N/A")) ? 0.0 : Double.parseDouble(epData.rating());
        this.dateOfRelease = (epData.dateOfRelease().equalsIgnoreCase("N/A")) ? null : LocalDate.parse(epData.dateOfRelease());
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", season=" + season +
                ", number=" + number +
                ", rating=" + rating +
                ", dateOfRelease=" + dateOfRelease;
    }
}
