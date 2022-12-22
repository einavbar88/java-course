package Part1;

import java.io.*;

public class VehicleLogger {
    public FileWriter file;
    public PrintWriter printWriter;

    public VehicleLogger() throws IOException {
        file = new FileWriter("log.txt");
        printWriter = new PrintWriter(file);
    }

    public void write(String str) throws IOException {
        System.out.println(str);
        this.printWriter.print(str);
    }

    public void close(){
        this.printWriter.close();
    }
}