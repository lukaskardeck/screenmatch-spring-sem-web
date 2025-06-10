package br.com.lukas.screenmatch.principal;

import br.com.lukas.screenmatch.model.SeasonData;
import br.com.lukas.screenmatch.model.SerieData;
import br.com.lukas.screenmatch.service.APIConsumer;
import br.com.lukas.screenmatch.service.DataConvert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.lukas.screenmatch.utils.FormatUtils.toParamURL;

public class Principal {
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConvert dataConvert = new DataConvert();

    private final String URL = "https://www.omdbapi.com/?";
    private final String API_KEY = "apikey=a5200b91";

    public void showMenu() {
        System.out.print("Informe o título da série: ");
        Scanner scanner = new Scanner(System.in);
        var serieTitle = scanner.nextLine();
        scanner.close();

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

        seasons.forEach(season -> season.episodes().forEach(episodio -> System.out.println(episodio.title())));

    }

}
