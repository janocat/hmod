
package hmod.common.heuristic.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;

/**
 *
 * @author Enrique Urra C.
 */
public class NextIteration implements Operator
{
    private IterationHandler iterationHandler;

    public void setIterationHandler(IterationHandler iterationHandler)
    {
        this.iterationHandler = iterationHandler;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        iterationHandler.advanceIteration();
    }
}
