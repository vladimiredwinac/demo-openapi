package com.example.demo.openapi.entity;

/**
 * Entity to store content of all talks that were given in the BOLIVIANJUG
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
public class Talk {
    public String title;
    public String speaker;
    public String description;
    public Integer date;

    /**
     * Default constructor to load data from json file
     *
     * @param title of the talk
     * @param speaker of the talk
     * @param description of the talk
     * @param date period of the talk
     */
    public Talk(String title, String speaker, String description, Integer date) {
        this.title = title;
        this.speaker = speaker;
        this.description = description;
        this.date = date;
    }
}
