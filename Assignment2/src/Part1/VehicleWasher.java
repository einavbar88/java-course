package Part1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class VehicleWasher {

    private LinkedList<Vehicle> waitingQueue;
    private LinkedList<Vehicle> washingQueue;
    private LinkedList<Vehicle> suvQueue;
    private LinkedList<Vehicle> carQueue;
    private LinkedList<Vehicle> truckQueue;
    private LinkedList<Vehicle> minibusQueue;

    private VehicleLogger logger;
    private Semaphore semaphore;
    private long startTime;

    public VehicleWasher(int numOfWashingStations) throws IOException {
        this.waitingQueue = new LinkedList<Vehicle>();
        this.washingQueue = new LinkedList<Vehicle>();
        this.carQueue = new LinkedList<Vehicle>();
        this.suvQueue = new LinkedList<Vehicle>();
        this.truckQueue = new LinkedList<Vehicle>();
        this.minibusQueue = new LinkedList<Vehicle>();
        this.semaphore = new Semaphore(numOfWashingStations);
        this.logger = new VehicleLogger();
    }

    public synchronized void addToWaitingQueue(Vehicle vehicle) throws IOException {
        if (this.startTime == 0) {
            this.startTime = System.currentTimeMillis();
        }

        vehicle.setStartTime(System.currentTimeMillis());
        String line = "";

        this.waitingQueue.add(vehicle);
        line += String.format("%s entered the queue, %s since start time\n", vehicle.toString(), timeConverter(System.currentTimeMillis() - startTime));
        this.logger.write(line);
    }

    private synchronized Boolean removeFromWaitingQueue(Vehicle vehicle) {
        if (!this.waitingQueue.isEmpty()) {
            return this.waitingQueue.remove(vehicle);
        }
        return false;
    }

    public void moveToWashingQueue(Vehicle vehicle) throws InterruptedException, IOException {
        semaphore.acquire();

        if (removeFromWaitingQueue(vehicle)) {
            washingQueue.add(vehicle);

            String washing = String.format("%s is being washed, %s since start time\n", vehicle.toString(), timeConverter(System.currentTimeMillis() - startTime));

            this.logger.write(washing);

        }
    }

    public void checkOut(Vehicle vehicle) throws IOException {
        if (this.washingQueue.remove(vehicle)) {
            String vehicleType = vehicle.getType();
            vehicle.setFinishTime(System.currentTimeMillis());
            switch (vehicleType) {
                case "Car":
                    carQueue.add(vehicle);
                    break;
                case "SUV":
                    suvQueue.add(vehicle);
                    break;
                case "MiniBus":
                    minibusQueue.add(vehicle);
                    break;
                case "Truck":
                    truckQueue.add(vehicle);
                    break;
            }

            this.logger.write(String.format("%s is now clean, %s since entered queue\n", vehicle.toString(), timeConverter(vehicle.getFinishTime() - vehicle.getStartTime())));

        }
        semaphore.release();
    }

    private long calculateAverageTime(LinkedList<Vehicle> list){
        int counter = 0;
        long sum = 0;
        while (!list.isEmpty()){
            Vehicle vehicle = list.remove();
            counter++;
            sum += vehicle.getFinishTime() - vehicle.getStartTime();
        }
        if(counter == 0) return 0;
        return sum/counter;
    }

    public void averageCleaningTimes() throws IOException {
        long carAverageTime = calculateAverageTime(carQueue);
        long suvAverageTime = calculateAverageTime(suvQueue);
        long minibusAverageTime = calculateAverageTime(minibusQueue);
        long truckAverageTime = calculateAverageTime(truckQueue);

        if(carAverageTime != 0) logger.write(String.format("\n\nCar cleaning average time is %s", timeConverter(carAverageTime)));
        if(suvAverageTime != 0) logger.write(String.format("\n\nSUV cleaning average time is %s", timeConverter(suvAverageTime)));
        if(minibusAverageTime != 0) logger.write(String.format("\n\nMiniBus cleaning average time is %s", timeConverter(minibusAverageTime)));
        if(truckAverageTime != 0) logger.write(String.format("\n\nTruck cleaning average time is %s", timeConverter(truckAverageTime)));

    }

    public void close(){
        this.logger.close();
    }

    private String timeConverter(long ms){
        long seconds = (ms / 1000) % 60;
        long mili = ms % 1000;

        return String.format("%d.%d seconds", seconds, mili );
    }
}
