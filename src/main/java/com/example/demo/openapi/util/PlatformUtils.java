package com.example.demo.openapi.util;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class contains all useful methods for the application
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
public class PlatformUtils {

    /**
     * Read content of a file and return content
     *
     * @param fileName    File location
     * @param classLoader Parameter to get address based on the context of the class
     * @return {@link String} File content
     * @throws IOException when this method found errors to access the file
     */
    public static String fileToString(String fileName, ClassLoader classLoader) throws IOException {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(classLoader.getResourceAsStream(fileName))))) {
            return buff.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Parse json string to Json structure object, returns null if the input parameter is null or empty
     *
     * @param input json as string
     * @return {@link JsonStructure} or null
     */
    public static JsonStructure parseToJson(String input) {
        JsonStructure jsonStructure = null;
        if (Objects.nonNull(input) && !blankString(input)) {
            JsonReader jsonReader = Json.createReader(new StringReader(input));
            jsonStructure = jsonReader.read();
            jsonReader.close();
        }
        return jsonStructure;
    }

    /**
     * Check if value is empty, regardless of whether it is a string with pure spaces.
     * Examples
     *
     * <ul>
     *     <li>input: null, output: true</li>
     *     <li>input: "", output: true</li>
     *     <li>input: "&nbsp;&nbsp;&nbsp;", output: true</li>
     *     <li>input: "&nbsp;&nbsp;x&nbsp;&nbsp;", output: false</li>
     * </ul>
     *
     * @param string value to process
     * @return {@link Boolean}
     */
    public static boolean blankString(String string) {
        return string == null || string.replaceAll("\\s+", "").isEmpty();
    }
}
