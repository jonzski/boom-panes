package classes;

public class GameTimer {
    private long startTime;

    public void start() {
        startTime = System.nanoTime();
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
