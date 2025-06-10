package br.com.lukas.screenmatch;

import br.com.lukas.screenmatch.model.EpisodeData;
import br.com.lukas.screenmatch.model.SeasonData;
import br.com.lukas.screenmatch.model.SerieData;
import br.com.lukas.screenmatch.service.APIConsumer;
import br.com.lukas.screenmatch.service.DataConvert;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		APIConsumer apiConsumer = new APIConsumer();
		DataConvert dataConvert = new DataConvert();

		var urlReq = "https://www.omdbapi.com/?t=the+big+bang+theory&apikey=a5200b91";;
		var json = apiConsumer.getData(urlReq);
		var serieData = dataConvert.getData(json, SerieData.class);

		List<SeasonData> seasons = new ArrayList<>();

		for (int i = 1; i <= serieData.totalSeasons(); i++) {
			urlReq = "https://www.omdbapi.com/?t=the+big+bang+theory&season=" + i + "&apikey=a5200b91";
			json = apiConsumer.getData(urlReq);
			var seasonData = dataConvert.getData(json, SeasonData.class);
			seasons.add(seasonData);
		}

		seasons.forEach(System.out::println);
	}
}
