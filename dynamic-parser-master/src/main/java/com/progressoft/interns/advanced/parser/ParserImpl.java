package com.progressoft.interns.advanced.parser;

import com.progressoft.interns.advanced.exception.ParserException;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserImpl implements Parser<List<HashMap<String, String>>> {



    @Override
    public List<HashMap<String, String>> parse(String filePath) {
        // TODO, Read about and use NIO API -> New Input Output API
        File file = validateFile(filePath);

        List<HashMap<String, String>> parsedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            String[] headers = null;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] values = line.split(",");

                if (headers == null) {
                    headers = values;
                } else {
                    if (values.length != headers.length) {
                        throw new ParserException("Missing Data on line: " + lineNumber);
                    }

                    HashMap<String, String> dataMap = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        dataMap.put(headers[i], values[i]);
                    }
                    parsedData.add(dataMap);
                }
            }
            if (parsedData.isEmpty() && headers == null) {
                throw new ParserException("File is empty");
            } else if(parsedData.isEmpty()) {
                throw new ParserException("Missing Data on line: 1");
            }

            return parsedData;

        } catch (IOException e) {
            throw new ParserException("Unable to parse the file");
        }
    }

    private static File validateFile(String filePath) {
        if (filePath == null) {
            throw new ParserException("File Path Cannot be null");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new ParserException("File not found: " + filePath);
        }
        return file;
    }
}
