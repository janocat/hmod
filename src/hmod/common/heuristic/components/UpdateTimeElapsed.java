
package hmod.common.heuristic.components;

import hmod.core.BooleanOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class UpdateTimeElapsed extends BooleanOperator
{
    protected TimeElapsedData timeElapsedHandler;

    public void setTimeElapsedData(TimeElapsedData timeElapsedHandler)
    {
        this.timeElapsedHandler = timeElapsedHandler;
    }

    @Override
    public Boolean evaluate()
    {
        double currTime = System.currentTimeMillis();
        double startTime = timeElapsedHandler.getInitTime();
        double diffTime = currTime - startTime;
        double maxSeconds = timeElapsedHandler.getMaxSeconds();
        timeElapsedHandler.setElapsedTime(diffTime);
        boolean finished = maxSeconds != -1.0 && diffTime >= timeElapsedHandler.getMaxSeconds() * 1000.0;
        timeElapsedHandler.setTimeFinished(finished);
        
        return finished;
    }
}
