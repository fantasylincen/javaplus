package util.timewheel;




public interface ITimeout {

	  /**
     * Returns the {@link ITimer} that created this handle.
     */
    ITimer getTimer();

    /**
     * Returns the {@link ITimerTask} which is associated with this handle.
     */
    ITimerTask getTask();

    /**
     * Returns {@code true} if and only if the {@link ITimerTask} associated
     * with this handle has been expired.
     */
    boolean isExpired();

    /**
     * Returns {@code true} if and only if the {@link ITimerTask} associated
     * with this handle has been cancelled.
     */
    boolean isCancelled();

    /**
     * Cancels the {@link ITimerTask} associated with this handle.  It the
     * task has been executed or cancelled already, it will return with no
     * side effect.
     */
    void cancel();
}
