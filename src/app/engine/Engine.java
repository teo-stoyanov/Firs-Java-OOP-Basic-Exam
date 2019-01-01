package app.engine;

import app.core.CarManager;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;
import app.utilities.Constants;

import java.io.IOException;

public class Engine {
    private ConsoleReader reader;
    private ConsoleWriter writer;
    private CarManager manager;

    public Engine(ConsoleReader reader, ConsoleWriter writer, CarManager manager) {
        this.reader = reader;
        this.writer = writer;
        this.manager = manager;
    }

    public void run() throws IOException {

        while (true) {
            String tokens = reader.readLine();
            if (Constants.END_LOOP.equals(tokens)) {
                break;
            }

            String[] input = tokens.split("\\s+");
            String command = input[0];
            String result;

            switch (command) {
                case Constants.REGISTER_COMMAND:
                    int id = Integer.parseInt(input[1]);
                    String type = input[2];
                    String brand = input[3];
                    String model = input[4];
                    int yearsOfProduction = Integer.parseInt(input[5]);
                    int horsePower = Integer.parseInt(input[6]);
                    int acceleration = Integer.parseInt(input[7]);
                    int suspension = Integer.parseInt(input[8]);
                    int durability = Integer.parseInt(input[9]);
                    this.manager.register(id, type, brand, model, yearsOfProduction, horsePower, acceleration, suspension, durability);
                    break;
                case Constants.CHECK_COMMAND:
                    result = this.manager.check(Integer.parseInt(input[1]));
                    if (!result.equals(Constants.EMPTY_STRING)) {
                        writer.writeLine(result);
                    }
                    break;
                case Constants.OPEN_COMMAND:
                    if (input.length > 6) {
                        this.manager.openTimeLimit(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), input[4], Integer.parseInt(input[5]), Integer.parseInt(input[6]));
                    } else {
                        this.manager.open(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), input[4], Integer.parseInt(input[5]));
                    }
                    break;
                case Constants.PARTICIPATE_COMMAND:
                    this.manager.participate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    break;
                case Constants.START_COMMAND:
                    result = this.manager.start(Integer.parseInt(input[1]));
                    if (!result.equals(Constants.EMPTY_STRING)) {
                        writer.write(result);
                    }
                    break;
                case Constants.PARK_COMMAND:
                    this.manager.park(Integer.parseInt(input[1]));
                    break;
                case Constants.UNPARK_COMMAND:
                    this.manager.unpark(Integer.parseInt(input[1]));
                    break;
                case Constants.TUNE_COMMAND:
                    this.manager.tune(Integer.parseInt(input[1]), input[2]);
                    break;
            }
        }
    }
}
