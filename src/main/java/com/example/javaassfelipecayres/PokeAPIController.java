package com.example.javaassfelipecayres;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PokeAPIController {

    @FXML
    private TextArea resultTextArea;

    public void fetchDataFromAPI(ActionEvent actionEvent) {
        try {
            String apiUrl = "https://pokeapi.co/api/v2/pokemon/1"; // Example URL for Pokemon #1 (Bulbasaur)
            String jsonData = APIUtility.fetchDataFromAPI(apiUrl);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            // Convert JSON to Java object
            Pokemon pokemon = objectMapper.treeToValue(jsonNode, Pokemon.class);

            // Display data in TextArea or update UI components
            resultTextArea.setText("Name: " + pokemon.getName() + "\nURL: " + pokemon.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}