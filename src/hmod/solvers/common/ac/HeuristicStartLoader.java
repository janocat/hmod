
package hmod.solvers.common.ac;

import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.BuilderGraph;
import hmod.core.Step;
import static hmod.loader.graph.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public final class HeuristicStartLoader
{
    public static NestedBuilder<Step> load(BuilderGraph graph) throws BuildException
    {
        NestedBuilder iterationData = graph.node(HeuristicIds.ITERATION_DATA);
        NestedBuilder timeElapsedData = graph.node(HeuristicIds.TIME_ELAPSED_DATA);
        NestedBuilder finishData = graph.node(HeuristicIds.FINISH_DATA);
        HeuristicOperatorFactory operators = HeuristicOperatorFactory.getInstance();
        
        return algorithmBlock().
            run(operators::startElapsedTime, timeElapsedData).
            call(graph.node(HeuristicIds.INIT_START)).
            While(AND(
                NOT(condition(operators::checkIterationsFinished, iterationData)),
                NOT(condition(operators::checkTimeFinished, timeElapsedData)),
                NOT(condition(operators::checkFinished, finishData))
            )).
                call(graph.node(HeuristicIds.ITERATION_START)).
                run(operators::nextIteration, iterationData).
            end().
            call(graph.node(HeuristicIds.FINISH_START));
    }
}
