package org.ibm.functionsinspringai;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.function.Function;
@Service
public class LeasingService implements Function<LeasingService.Request, LeasingService.Response> {
    private static final Logger log = LoggerFactory.getLogger(LeasingService.class);
    private final RestClient restClient;

    public LeasingService() {
        this.restClient = RestClient.create("http://localhost:8081/buildings");
    }

    @Override
    public Response apply( LeasingService.Request request) {
        log.info("Building Request: {}",request);
        LeasingService.Response response = restClient.get()
                .uri("/search/byCity?city={question}", request.city)
                .retrieve()
                .body(Response.class);
        System.out.println(restClient.get().uri("/search/byCity?city={question}", request.city));
        log.info("Building API Response: {}", response);
        return response;

    }

    // mapping the response of the Building API to records. I only mapped the information I was interested in.
    public record Request(String city) {}
    public record Response(Embedded _embedded) {}
    public record Embedded(List<Building> buildings) {}

    public record Building(
            String realPropertyAssetName,
            String installationName,
            String ownedOrLeased,
            String gsaRegion,
            String streetAddress,
            String city,
            String state,
            String zipcode,
            double latitude,
            double longitude,
            double buildingRentableSquareFeet,
            double availableSquareFeet,
            int constructionDate,
            String congressionalDistrict,
            String congressionalDistrictRepresentativeName,
            String buildingStatus,
            String realPropertyAssetType
    ) {}
}
