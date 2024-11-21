package org.kniit241;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportReader {
    public static List<String[]> readFile(String filePath, String delimiter)throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                String[] components = line.split(delimiter);
                rows.add(components);
            }
        }
        return rows;
    }
}
