package me.peanut.hydrogen.utils;

/**
 * Base class for caching without actually storing a value.
 */
public class Cache {
    protected long lastRefresh = 0;
    protected long maxAge;

    /**
     * @param maxAge in seconds
     */
    public Cache(long maxAge) {
        this.maxAge = maxAge * 1000;
    }

    /**
     *  True when it's time to update the cashed value.
     */
    public boolean isRefreshNeeded() {
        return this.lastRefresh + this.maxAge < System.currentTimeMillis();
    }

    /**
     * Update the cache and "reset the timer".
     */
    public void update() {
        this.lastRefresh = System.currentTimeMillis();
    }

}
