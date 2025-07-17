package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBuscar {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual Nome do Filme: ");
        var buscar = scanner.nextLine();
        System.out.println("Chave para concluir a requisição: ");
        var chave = scanner.nextLine();
        String endereco = "http://www.omdbapi.com/?t="+buscar+"&apikey="+ chave;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);


        Gson gson = new Gson();
        Titulo meuTitulo = gson.fromJson(json, Titulo.class);
        System.out.println(meuTitulo);

    }
}
