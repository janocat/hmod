
package hmod.common.heuristic.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface TimeElapsedData extends DataHandler
{
    void setInitTime(double time);
    double getInitTime();
    
    void setMaxSeconds(double seconds);
    double getMaxSeconds();
    
    void setElapsedTime(double seconds);
    double getElapsedTime();
    
    void setTimeFinished(boolean finished);
    boolean getTimeFinished();
}
