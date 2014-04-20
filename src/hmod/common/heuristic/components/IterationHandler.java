
package hmod.common.heuristic.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C
 */
public interface IterationHandler extends DataHandler
{
    boolean isIterationsFinished();
    int getMaxIteration();
    int getCurrentIteration();
    void advanceIteration();
    void resetIterations();
}
