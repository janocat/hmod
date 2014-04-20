
package hmod.common.heuristic.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckMaxIteration extends BooleanOperator
{
    private IterationData iterationData;

    public void setIterationData(IterationData IterationData)
    {
        this.iterationData = IterationData;
    }
    
    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        return iterationData.getIterationFinished();
    }
}
