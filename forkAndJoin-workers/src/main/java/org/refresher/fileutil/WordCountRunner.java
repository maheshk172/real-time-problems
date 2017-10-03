package org.refresher.fileutil;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCountRunner implements Runnable, CountRunner {
    private String line;
    private Map<String, AtomicInteger> wordMap;

    public WordCountRunner(String line, Map<String, AtomicInteger> wordMap) {
        this.line = line;
        this.wordMap = wordMap;
    }

    @Override
    public void run() {
        fetchAndCountWords();
    }

    public void fetchAndCountWords(){
        StringTokenizer tokenizer = new StringTokenizer(this.line);
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if(wordMap.containsKey(token)) {
                AtomicInteger count = wordMap.get(token);
                count.set(count.incrementAndGet());
                wordMap.put(token, count);
            } else {
                wordMap.put(token, new AtomicInteger(1));
            }
        }
    }
}

