package app;

import app.core.CarManager;
import app.engine.Engine;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        CarManager manager = new CarManager();

        Engine engine = new Engine(reader,writer,manager);

        engine.run();
    }
}
