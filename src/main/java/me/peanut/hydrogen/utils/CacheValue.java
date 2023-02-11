package me.peanut.hydrogen.utils;

/**
 * Util class to cache values from expensive actions.
 */
public class CacheValue <T> extends Cache {
    protected T value;

    /**
     * @param maxAge in seconds
     */
    public CacheValue(long maxAge) {
        super(maxAge);
    }

    /**
     *  True when it's time to update the cashed value.
     */
    @Override()
    public boolean isRefreshNeeded() {
        return this.value == null || this.lastRefresh + this.maxAge < System.currentTimeMillis();
    }

    /**
     * Update the cache and "reset the timer".
     */
    public void update(T value) {
        this.value = value;
        this.lastRefresh = System.currentTimeMillis();
    }


    public T getValue() {
        return  value;
    }
}
