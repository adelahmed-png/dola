package com.mycompany.parkinglot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriter {
    private BufferedWriter writer;

    public BufferWriter(FileWriter fw) {
        this.writer = new BufferedWriter(fw);
    }

    public void write(String data) throws IOException {
        writer.write(data);
        writer.newLine();
    }

    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
