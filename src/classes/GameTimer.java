package classes;

public class GameTimer {
    private long startTime = 0;

    public void start() {
        if (startTime == 0) startTime = System.nanoTime();
    }

    public double getElapsedTimeSeconds() {
        return (System.nanoTime() - startTime) / 1_000_000_000.0;
    }

    public void stop() {
        // Do nothing
    }

    public void reset() {
        startTime = 0;
    }


}
