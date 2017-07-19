package com.example.kdeek.hw6;

/**
 * Created by kdeek on 10/19/2016.
 */
public class Weather {
    String time;
    String temperature;
    String dewpoint;
    String clouds;
    String iconUrl;
    String windSpeed;
    String windDirection;
    String climateType;
    String humidity;
    String feelsLike;
    String maximumTemp;
    String minimumTemp;
    String pressure;
    String date;
    String city;
    String country;
    String locID;

    public Weather() {
    }

    public Weather(String time, String temperature, String dewpoint, String clouds, String iconUrl, String windSpeed, String windDirection, String climateType, String humidity, String feelsLike, String maximumTemp, String minimumTemp, String pressure) {
        this.time = time;
        this.temperature = temperature;
        this.dewpoint = dewpoint;
        this.clouds = clouds;
        this.iconUrl = iconUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.climateType = climateType;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
        this.maximumTemp = maximumTemp;
        this.minimumTemp = minimumTemp;
        this.pressure = pressure;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(String minimumTemp) {
        this.minimumTemp = minimumTemp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(String maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
