
package hmod.common.heuristic.components;

/**
 *
 * @author Enrique Urra C.
 */
public final class TimeElapsedHandlerFactory
{
    private class InnerHandler implements TimeElapsedHandler
    {
        private final double maxSeconds;
        private double initTime;

        public InnerHandler(double maxSeconds)
        {
            this.maxSeconds = maxSeconds;
        }
        
        @Override
        public void startElapsedTime()
        {
            initTime = System.currentTimeMillis();
        }

        @Override
        public double getMaxSeconds()
        {
            return maxSeconds;
        }

        @Override
        public double getElapsedSeconds()
        {
            long currTime = System.currentTimeMillis();
            double diffTime = currTime - initTime;
            
            return diffTime / 1000.0;
        }

        @Override
        public boolean isElapsedTimeFinished()
        {
            return maxSeconds != -1.0 && getElapsedSeconds() >= maxSeconds;
        }
    }
    
    private static TimeElapsedHandlerFactory instance;

    public static TimeElapsedHandlerFactory getInstance()
    {
        if(instance == null)
            instance = new TimeElapsedHandlerFactory();
        
        return instance;
    }

    private TimeElapsedHandlerFactory()
    {
    }
    
    public TimeElapsedHandler createTimeElapsedHandler(double maxSeconds)
    {
        return new InnerHandler(maxSeconds);
    }
}
