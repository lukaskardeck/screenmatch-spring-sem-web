package br.com.lukas.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
        @JsonAlias("Season") Integer season,
        @JsonAlias("Episodes") List<EpisodeData> episodes
) {
    @Override
    public String toString() {
        int totalEpisodes = episodes != null ? episodes.size() : 0;
        return "SeasonData{" +
                "season=" + season +
                ", totalEpisodes=" + totalEpisodes +
                ", episodes=" + episodes +
                '}';
    }
}
