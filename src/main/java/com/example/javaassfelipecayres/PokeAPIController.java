package com.example.javaassfelipecayres;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PokeAPIController {

    @FXML private TextField searchTextField;
    @FXML private TextArea resultTextArea;

    public void fetchDataFromAPI(ActionEvent actionEvent) {
        try {
            String searchTerm = searchTextField.getText().trim();
            if (searchTerm.isEmpty()) {
                // Handle empty search term
                return;
            }

            // Use the PokeAPI URL with the search query
            String apiUrl = "https://pokeapi.co/api/v2/pokemon?limit=151"; // Limit to first 151 Pokemon (first generation)
            String jsonData = APIUtility.fetchDataFromAPI(apiUrl);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode resultsNode = objectMapper.readTree(jsonData).get("results");

            // Display data in TextArea or update UI components
            StringBuilder resultText = new StringBuilder();
            for (JsonNode result : resultsNode) {
                String pokemonName = result.get("name").asText();
                if (pokemonName.contains(searchTerm.toLowerCase())) {
                    String url = result.get("url").asText();
                    // Extract the Pokemon index from the URL
                    int index = Integer.parseInt(url.split("/")[6]);
                    resultText.append("Name: ").append(pokemonName).append(", Index: ").append(index).append("\n");
                }
            }

            resultTextArea.setText(resultText.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}