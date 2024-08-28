package org.ibm.functionsinspringai;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExternalApiResponse {
    @JsonProperty("_embedded")
    private Embedded embedded;

    // Getters and Setters

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public static class Embedded {
        private List<Building> buildings;

        // Getters and Setters

        public List<Building> getBuildings() {
            return buildings;
        }

        public void setBuildings(List<Building> buildings) {
            this.buildings = buildings;
        }
    }
}

