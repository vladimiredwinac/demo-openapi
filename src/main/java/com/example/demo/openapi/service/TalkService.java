package com.example.demo.openapi.service;

import com.example.demo.openapi.entity.Talk;
import com.example.demo.openapi.exception.PlatformException;
import com.example.demo.openapi.util.PlatformUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class to expose services and interact with data
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
@ApplicationScoped
public class TalkService {

    private static Logger logger = Logger.getLogger(TalkService.class.getName());
    private static final String TALKS_FILE_PATH = "/data/talks.json";
    private List<Talk> talks = new ArrayList<>();
    private Integer minPeriod;
    private Integer maxPeriod;

    /**
     * Add values to initial variables
     */
    @PostConstruct
    public void init() {
        try {
            String contentFile = PlatformUtils.fileToString(TALKS_FILE_PATH, getClass().getClassLoader());
            loadTalks(PlatformUtils.parseToJson(contentFile));
        } catch (IOException e) {
            logger.log(Level.WARNING, "An error occurred while loading data", e);
        }
    }

    /**
     * returns all the talks stored in memory
     *
     * @return {@link List}&lt;{@link Talk}&gt;
     */
    public List<Talk> getAllTalks() {
        return talks;
    }

    /**
     * returns all the talks stored in memory filtered by speaker
     *
     * @param speaker name
     * @return {@link List}&lt;{@link Talk}&gt;
     */
    public List<Talk> getTalksBySpeaker(String speaker) {
        List<Talk> talksFiltered = talks.stream()
                .filter(t -> t.speaker.equalsIgnoreCase(speaker))
                .collect(Collectors.toList());
        if (talksFiltered.isEmpty()) {
            logger.log(Level.WARNING, "No results found, with speaker: " + speaker);
            throw new PlatformException(404, "No results found");
        }
        return talksFiltered;
    }

    /**
     * returns all the talks stored in memory filtered by a range of period
     *
     * @param from initial period
     * @param to   final period
     * @return {@link List}&lt;{@link Talk}&gt;
     */
    public List<Talk> getTalksByRangeOfPeriod(Integer from, Integer to) {
        // comment
        if (from < minPeriod) {
            logger.log(Level.SEVERE, "Invalid value [" + from + "] in variable 'FROM'");
            throw new PlatformException("Parameter 'FROM' is out of range");
        }
        if (to > maxPeriod) {
            logger.log(Level.SEVERE, "Invalid value [" + to + "] in variable 'TO'");
            throw new PlatformException("Parameter 'TO' is out of range");
        }
        if (from > to) {
            logger.log(Level.SEVERE, "Parameter 'FROM'[" + from + "] is greater than 'TO'[" + to + "]");
            throw new PlatformException("Parameter 'FROM' is greater than 'TO'");
        }
        List<Talk> talksFiltered = talks.stream()
                .filter(t -> t.date >= from && t.date <= to)
                .collect(Collectors.toList());
        if (talksFiltered.isEmpty()) {
            throw new PlatformException(404, "No results found");
        }
        return talksFiltered;
    }

    /**
     * returns all speakers
     *
     * @return {@link List}&lt;{@link String}&gt;
     */
    public List<String> getSpeakers() {
        return talks.stream().map(t -> t.speaker)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * load json content to objects
     *
     * @param jsonStructure
     */
    private void loadTalks(JsonStructure jsonStructure) {
        if (Objects.isNull(jsonStructure)) {
            logger.log(Level.SEVERE, "An error occurred while loading data from json, please check file content in " + TALKS_FILE_PATH);
            return;
        }
        JsonArray jsonValues = jsonStructure.asJsonArray();
        jsonValues.forEach(jsonValue -> {
            JsonObject jsonObject = jsonValue.asJsonObject();
            talks.add(new Talk(
                    jsonObject.getString("title"),
                    jsonObject.getString("speaker"),
                    jsonObject.getString("description"),
                    jsonObject.getInt("date")
            ));
        });
        // max and min period
        maxPeriod = talks.stream().mapToInt(t -> t.date).max().orElse(202012);
        minPeriod = talks.stream().mapToInt(t -> t.date).min().orElse(201911);
    }
}
