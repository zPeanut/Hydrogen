package tk.peanut.phosphor.utils;

public class TimeHelper {
    private long lastMS = 0L;
    private long resetMS = 0L;

    public int convertToMS(int d) {
        return 1000 / d;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public static long getCurrentTime()
    {
        return (long) (System.nanoTime() / 1000000.0D);
    }

    public static boolean hasDelayRun(long resetMS, int delay)
    {
        return getCurrentTime() >= resetMS + delay;
    }

    public boolean hasDelayRun(double d)
    {
        return getCurrentTime() >= this.resetMS + d;
    }

    public void resetAndAdd(long reset)
    {
        this.resetMS = (getCurrentTime() + reset);
    }

    public boolean hasReached(long milliseconds) {
        return getCurrentMS() - lastMS >= milliseconds;
    }

    public boolean hasTimeReached(long delay) {
        return System.currentTimeMillis() - lastMS >= delay;
    }

    public long getDelay() {
        return System.currentTimeMillis() - lastMS;
    }

    public void reset() {
        lastMS = getCurrentMS();
    }

    public void setLastMS() {
        lastMS = System.currentTimeMillis();
    }

    public void setLastMS(long lastMS) {
        this.lastMS = lastMS;
    }
}