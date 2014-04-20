
package hmod.common.heuristic.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckMaxTime extends BooleanOperator
{
    private TimeElapsedData timeElapsedData;

    public void setTimeElapsedData(TimeElapsedData timeElapsedData)
    {
        this.timeElapsedData = timeElapsedData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        return timeElapsedData.getTimeFinished();
    }
}
