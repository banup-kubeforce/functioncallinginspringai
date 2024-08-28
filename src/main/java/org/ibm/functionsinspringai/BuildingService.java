package org.ibm.functionsinspringai;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Service
public class BuildingService implements Function<BuildingService.Request, BuildingService.Response> {

    private final WebClient webClient;


    public BuildingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/buildings/search").build();
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonClassDescription("Buildings API Request")
    public static class Request {
        @JsonProperty(required = true, value = "states")
        @JsonPropertyDescription("The states like TX,CA,NY")
        private List<String> states;
        private String availability;

        // Getters and Setters
        public List<String> getStates() {
            return states;
        }

        public void setStates(List<String> states) {
            this.states = states;
        }
        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }
    }

    public static class Response {
        private String outputStr;

        public Response(String outputStr) {
            this.outputStr = outputStr;
        }

        // Getter and Setter
        public String getOutputStr() {
            return outputStr;
        }

        public void setOutputStr(String outputStr) {
            this.outputStr = outputStr;
        }
    }

    @Override
    public Response apply(Request request) {
        List<String> states = request.getStates();
        String availability = request.getAvailability();
        Mono<List<Building>> buildingsMono = getBuildingsByStates(states,availability).collectList();

        List<Building> buildings = buildingsMono.block(); // Blocking call to get the response
        String asset = "";

        if (buildings != null && !buildings.isEmpty()) {
            asset = buildings.stream()
                    .map(Building::getRealPropertyAssetName)
                    .reduce((first, second) -> first + ", " + second)
                    .orElse("");
        }

        return new Response(asset);
    }

    public Flux<Building> getBuildingsByStates(List<String> states,String availability) {
        return Flux.fromIterable(states)
                .flatMap(state -> webClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/byState")
                                .queryParam("state", state)
                                .queryParam("size", 10)  // adjust size as needed
                                .build())
                        .retrieve()
                        .bodyToMono(ExternalApiResponse.class)
                        .flatMapMany(response -> Flux.fromIterable(response.getEmbedded().getBuildings()))
                );
    }



    // ExternalApiResponse and Building classes should be defined below or in separate files
}
