
package hmod.common.heuristic.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckHeuristicFinished extends BooleanOperator
{
    private IterationHandler iterationHandler;
    private TimeElapsedHandler timeElapsedHandler;
    private FinishHandler finishHandler;

    public void setIterationHandler(IterationHandler iterationHandler)
    {
        this.iterationHandler = iterationHandler;
    }

    public void setTimeElapsedHandler(TimeElapsedHandler timeElapsedHandler)
    {
        this.timeElapsedHandler = timeElapsedHandler;
    }

    public void setFinishHandler(FinishHandler finishHandler)
    {
        this.finishHandler = finishHandler;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        return finishHandler.isFinished() || iterationHandler.isIterationsFinished() || timeElapsedHandler.isElapsedTimeFinished();
    }
}
