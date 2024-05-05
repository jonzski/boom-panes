package classes;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTimer {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;

    public GameTimer() {
        reset();
    }

    public void start() {
        if (!isRunning) {
            startTime = TimeUtils.millis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            elapsedTime += TimeUtils.timeSinceMillis(startTime);
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        elapsedTime = 0;
        isRunning = false;
    }

    public long getElapsedTimeMillis() {
        if (isRunning) {
            return elapsedTime + TimeUtils.timeSinceMillis(startTime);
        } else {
            return elapsedTime;
        }
    }

    public float getElapsedTimeSeconds() {
        return getElapsedTimeMillis() / 1000f;
    }
}
