
package hmod.common.heuristic.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface TimeElapsedHandler extends DataHandler
{
    void startElapsedTime();
    double getMaxSeconds();
    double getElapsedSeconds();
    boolean isElapsedTimeFinished();
}
