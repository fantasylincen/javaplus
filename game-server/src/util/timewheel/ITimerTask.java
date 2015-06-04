package util.timewheel;

public interface ITimerTask {

	void run( ITimeout timeout ) throws Exception;

}
