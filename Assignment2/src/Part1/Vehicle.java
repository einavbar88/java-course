// Einav Bar
// Lior Poterman

package Part1;

public abstract class Vehicle extends Thread{
    private VehicleWasher vehicleWasher;
    private String type;
    private int id;
    private long finishTime;
    private long startTime;

    public Vehicle(VehicleWasher vehicleWasher, String type, int id) {
        this.vehicleWasher = vehicleWasher;
        this.type = type;
        this.id = id;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void run() {
        try {
            double log = -Math.log(Math.random()) * 1000;

            Thread.sleep ((long)(log / 1.5));

            vehicleWasher.addToWaitingQueue(this);
            vehicleWasher.moveToWashingQueue(this);

            Thread.sleep ((long)log / 3);

            vehicleWasher.checkOut(this);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getType() {
        return type;
    }

    public String toString(){
        return String.format("%s %d", type, id);
    }
}
