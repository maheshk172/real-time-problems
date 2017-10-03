package org.refresher.fileutil;

// Read file and add each Line to the reader logic
// add reapted words to HashMap with count

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ReadAndCountFromFile {
    final AtomicReference<Map<String, AtomicInteger>> wordMap = new AtomicReference<>(new ConcurrentHashMap<>());

    public void addLinesFromFile(String file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            String line = bufferedReader.readLine();
            List<String> lines = new ArrayList<>();

            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();

            // Populate Word Counts
            lines.parallelStream().forEach(lineFromFile -> new WordCountRunner(lineFromFile, wordMap.get()).fetchAndCountWords());

            // Print ket and value tokens
            wordMap.get().keySet().stream().forEach(key -> System.out.println("Key: " + key + ", NoOfTimes: "  + wordMap.get().get(key).intValue()));


        } catch (IOException e) {
            e.printStackTrace();
        }
     }

     public void readLinesFromFileForkWay(String file) throws FileNotFoundException {
         FileReader fileReader = new FileReader(file);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         try {
             String line = bufferedReader.readLine();
             List<String> lines = new ArrayList<>();

             while(line != null) {
                 lines.add(line);
                 line = bufferedReader.readLine();
             }
             bufferedReader.close();
             fileReader.close();



             // Populate Word Counts
             lines.parallelStream().forEach(lineFromFile -> new WordCountRunner(lineFromFile, wordMap.get()).fetchAndCountWords());

             // Print ket and value tokens
             wordMap.get().keySet().stream().forEach(key -> System.out.println("Key: " + key + ", NoOfTimes: "  + wordMap.get().get(key).intValue()));


         } catch (IOException e) {
             e.printStackTrace();
         }
     }



}
