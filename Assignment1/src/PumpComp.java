// Einav Bar
// Lior Poterman

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PumpComp {
    public static void main(String[] args) {

        int maxHeight = 30;
        high h = new high(maxHeight);
        String[] ids = {"First", "Second", "Third", "Fourth", "Fifth"};

        for(int i = 0; i < 5; i++){
            int pumpkinHeight = (int)(Math.random() * 10 + 1);
            Pumpkin pumpkin = new Pumpkin(ids[i], h, pumpkinHeight);
            pumpkin.start();
        }
    }
}

class Pumpkin extends Thread{
    private String pumpID;
    private high h;
    private int height;
    private boolean finished;

    public Pumpkin(String pumpID, high h, int height){
        this.pumpID = pumpID;
        this.h = h;
        this.height = height;
        this.finished = false;
    }

    @Override
    public void run(){
        while (!this.finished){
            this.grow();
        }
    }

    public void grow(){
        try{
            Thread.sleep(2000);
        }catch (Exception e) {}

        System.out.printf("%s: %d cm to max Height\n", this.GetpumpID(), this.h.maxHeight - this.height);

        int random = (int)(Math.random() * 10 + 1);

        this.height += random;

        int toMaxHeight = this.h.maxHeight - this.height;

        if(toMaxHeight < 1) {
            this.finished = true;
            this.h.TheBigPumpkin(this);
        }
    }
    public String GetpumpID(){
        return this.pumpID;
    }
}

class high {
    public int maxHeight;
    private int finishedThreads;
    private Lock lock = new ReentrantLock();
    public high (int maxHeight) {
        this.maxHeight = maxHeight;
        this.finishedThreads = 1;
    }

    public void TheBigPumpkin(Pumpkin p){
        lock.lock();
        System.out.println("\n**********************************\n" + p.GetpumpID() + " is " + this.getRankStr(this.finishedThreads) + "\n**********************************\n");
        this.finishedThreads++;
        lock.unlock();
    }

    private String getRankStr(int rank){
        switch (rank){
            case 1:
                return "the Winner";
            case 2:
                return  "in 2nd place";
            case 3:
                return "in 3rd place";
            case 4:
                return "in 4th place";
            default:
                return "in 5th place";
        }
    }
}