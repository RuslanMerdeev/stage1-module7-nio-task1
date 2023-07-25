package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        Map<String, String> map = fileToMap(file);
        profile.setAge(Integer.parseInt(map.get("Age")));
        profile.setName(map.get("Name"));
        profile.setEmail(map.get("Email"));
        profile.setPhone(Long.parseLong(map.get("Phone")));
        return profile;
    }

    private Map<String, String> fileToMap(File file) {
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath())))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(": ");
                map.put(split[0], split[1]);
            }
        } catch (IOException e) {
            throw new FileParsingException(e);
        }
        return map;
    }

    public static class FileParsingException extends RuntimeException {

        public FileParsingException(Throwable cause) {
            super(cause);
        }
    }
}
