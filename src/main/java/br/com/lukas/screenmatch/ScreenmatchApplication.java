package br.com.lukas.screenmatch;

import br.com.lukas.screenmatch.model.SerieData;
import br.com.lukas.screenmatch.service.APIConsumer;
import br.com.lukas.screenmatch.service.DataConvert;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		APIConsumer apiConsumer = new APIConsumer();
		var urlReq = "https://www.omdbapi.com/?t=gilmore+girls&apikey=a5200b91";
		var json = apiConsumer.getData(urlReq);
		//System.out.println(json);

		DataConvert dataConvert = new DataConvert();
		var data = dataConvert.getData(json, SerieData.class);
		System.out.println(data);
	}
}
