
package hmod.common.heuristic.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class StartElapsedTime implements Operator
{
    private TimeElapsedHandler timeElapsedHandler;

    public void setTimeElapsedHandler(TimeElapsedHandler timeElapsedHandler)
    {
        this.timeElapsedHandler = timeElapsedHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        timeElapsedHandler.startElapsedTime();
    }
}
