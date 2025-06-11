package br.com.lukas.screenmatch.principal;

import br.com.lukas.screenmatch.model.Episode;
import br.com.lukas.screenmatch.model.EpisodeData;
import br.com.lukas.screenmatch.model.SeasonData;
import br.com.lukas.screenmatch.model.SerieData;
import br.com.lukas.screenmatch.service.APIConsumer;
import br.com.lukas.screenmatch.service.DataConvert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static br.com.lukas.screenmatch.utils.FormatUtils.toParamURL;

public class Principal {
    private final APIConsumer apiConsumer = new APIConsumer();
    private final DataConvert dataConvert = new DataConvert();

    private final String URL = "https://www.omdbapi.com/?";
    private final String API_KEY = "apikey=a5200b91";

    private static final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.print("Informe o título da série: ");
        var serieTitle = scanner.nextLine();

        var urlReq = URL + "t=" + toParamURL(serieTitle) + "&" + API_KEY;
        var json = apiConsumer.getData(urlReq);
        var serieData = dataConvert.getData(json, SerieData.class);


        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= serieData.totalSeasons(); i++) {
            urlReq = URL + "t=" + toParamURL(serieTitle) + "&" + "&season=" + i + "&" + API_KEY;
            json = apiConsumer.getData(urlReq);
            var seasonData = dataConvert.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }

//        seasons.forEach(season -> season.episodes().forEach(episodio -> System.out.println(episodio.title())));

        var episodeDatas = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\n5 episódios mais bem avaliados! ↓↓↓");
        episodeDatas.stream()
                .filter(ep -> !ep.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);


        System.out.println("\nTodos os episodios da série! ↓↓↓");
        List<Episode> episodes = seasons.stream()
                .flatMap( season -> season.episodes().stream()
                        .map(epData -> new Episode(season.season(), epData))
                ).collect(Collectors.toList());
        episodes.forEach(System.out::println);

        System.out.println("\nA partir de qual ano você deseja ver os episodios");
        var searchYear = scanner.nextInt();
        scanner.nextLine();

        LocalDate startDate = LocalDate.of(searchYear, 1, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nEpisodios a partir de: " + startDate.format(formatter));
        episodes.stream()
                .filter(e -> e.getDateOfRelease() != null && e.getDateOfRelease().isAfter(startDate))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getSeason() +
                        " Episodio: " + e.getNumber() +
                        " Data de Lançamento: " + e.getDateOfRelease().format(formatter)
                ));
    }

}
