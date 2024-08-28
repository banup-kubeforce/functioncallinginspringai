package org.ibm.functionsinspringai;

public class Building {
    private String realPropertyAssetName;
    private String installationName;
    private String ownedOrLeased;
    private String gsaRegion;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private double latitude;
    private double longitude;
    private double buildingRentableSquareFeet;
    private double availableSquareFeet;
    private int constructionDate;
    private String congressionalDistrict;
    private String congressionalDistrictRepresentativeName;
    private String buildingStatus;
    private String realPropertyAssetType;

    public String getRealPropertyAssetName() {
        return realPropertyAssetName;
    }

    public void setRealPropertyAssetName(String realPropertyAssetName) {
        this.realPropertyAssetName = realPropertyAssetName;
    }

    public String getInstallationName() {
        return installationName;
    }

    public void setInstallationName(String installationName) {
        this.installationName = installationName;
    }

    public String getOwnedOrLeased() {
        return ownedOrLeased;
    }

    public void setOwnedOrLeased(String ownedOrLeased) {
        this.ownedOrLeased = ownedOrLeased;
    }

    public String getGsaRegion() {
        return gsaRegion;
    }

    public void setGsaRegion(String gsaRegion) {
        this.gsaRegion = gsaRegion;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getBuildingRentableSquareFeet() {
        return buildingRentableSquareFeet;
    }

    public void setBuildingRentableSquareFeet(double buildingRentableSquareFeet) {
        this.buildingRentableSquareFeet = buildingRentableSquareFeet;
    }

    public double getAvailableSquareFeet() {
        return availableSquareFeet;
    }

    public void setAvailableSquareFeet(double availableSquareFeet) {
        this.availableSquareFeet = availableSquareFeet;
    }

    public int getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(int constructionDate) {
        this.constructionDate = constructionDate;
    }

    public String getCongressionalDistrict() {
        return congressionalDistrict;
    }

    public void setCongressionalDistrict(String congressionalDistrict) {
        this.congressionalDistrict = congressionalDistrict;
    }

    public String getCongressionalDistrictRepresentativeName() {
        return congressionalDistrictRepresentativeName;
    }

    public void setCongressionalDistrictRepresentativeName(String congressionalDistrictRepresentativeName) {
        this.congressionalDistrictRepresentativeName = congressionalDistrictRepresentativeName;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public String getRealPropertyAssetType() {
        return realPropertyAssetType;
    }

    public void setRealPropertyAssetType(String realPropertyAssetType) {
        this.realPropertyAssetType = realPropertyAssetType;
    }
}
