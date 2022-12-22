// Einav Bar
// Lior Poterman

package Part1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        LinkedList<Vehicle> threads = new LinkedList<Vehicle>();
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter num of washing lanes");
        int numStations = input.nextInt();
        System.out.println("Please enter num of cars");
        int numOfCars = input.nextInt();

        VehicleWasher vehicleWasher = new VehicleWasher(numStations);

        for (int i = 0; i < numOfCars; i++) {
            Random random = new Random();
            Vehicle vehicle = null;
            switch (random.nextInt(4)) {
                case 0:
                    vehicle = new Car(vehicleWasher, i);
                    break;
                case 1:
                    vehicle = new SUV(vehicleWasher, i);
                    break;
                case 2:
                    vehicle = new MiniBus(vehicleWasher, i);
                    break;
                case 3:
                    vehicle = new Truck(vehicleWasher, i);
                    break;
            }

            if (vehicle == null) continue;

            threads.add(vehicle);
            vehicle.start();
        }


        for (int i = 0; i < numOfCars; i++) {
            threads.get(i).join();
        }

        vehicleWasher.averageCleaningTimes();
        vehicleWasher.close();
        input.close();
    }
}
