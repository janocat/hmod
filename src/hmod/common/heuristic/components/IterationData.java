
package hmod.common.heuristic.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface IterationData extends DataHandler
{
    void setMaxIteration(int maxIteration);
    int getMaxIteration();
    
    void setCurrentIteration(int iteration);
    int getCurrentIteration();
    
    void setIterationFinished(boolean finished);
    boolean getIterationFinished();
}
