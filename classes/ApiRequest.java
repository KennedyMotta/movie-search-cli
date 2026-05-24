package classes;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequest {

    public void searchMovie(String movieName,String searchType) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://www.omdbapi.com/?"
                                + searchType
                                + "="
                                + movieName
                                + "&apikey=1a0ef844"
                ))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        String body = response.body();

        Gson gson = new Gson();

        System.out.println("\n==================================");
        System.out.println("           RESULT");
        System.out.println("==================================");

        if (body.contains("\"Response\":\"False\"")) {

            System.out.println("Movie not found.");

        } else if("t".equals(searchType) || "i".equals(searchType)){

            Movie movie = gson.fromJson(body,Movie.class);

            System.out.println(
                    movie.Title + " "
                            + movie.Year + " "
                            + movie.Genre + " "
                            + movie.Released + " "
                            + movie.Runtime
            ); } else if ("t".equals(searchType) || "i".equals(searchType)) {

            Movie movie = gson.fromJson(body, Movie.class);

            System.out.println(
                    movie.Title + " "
                            + movie.Year + " "
                            + movie.Genre + " "
                            + movie.Released + " "
                            + movie.Runtime
            );

        } else {
            String cleanBody = body.replace("–", "-");
            MovieSearchResponse result =
                    gson.fromJson(cleanBody, MovieSearchResponse.class);

            for (Movie m : result.Search) {

                System.out.println(
                        m.Title + " " + m.Year
                );
            }
        }}}
