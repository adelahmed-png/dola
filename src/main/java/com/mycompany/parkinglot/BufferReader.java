package com.mycompany.parkinglot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReader {
    private BufferedReader reader;

    public BufferReader(FileReader fr) {
        this.reader = new BufferedReader(fr);
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }
}
