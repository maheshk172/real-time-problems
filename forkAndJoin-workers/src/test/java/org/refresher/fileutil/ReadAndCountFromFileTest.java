package org.refresher.fileutil;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ReadAndCountFromFileTest {

    ReadAndCountFromFile target = new ReadAndCountFromFile();

    @Before
    public void setup() {

    }

    @Test
    public void test_readFileAndPrintCountOfTimesEachWordHasAppeared() throws FileNotFoundException {
        String filePath = "c://backup//testfile.txt";
        target.addLinesFromFile(filePath);
    }
}
