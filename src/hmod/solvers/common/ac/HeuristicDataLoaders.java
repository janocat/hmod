
package hmod.solvers.common.ac;

import static flexbuilders.basic.BasicBuilders.builderFor;
import flexbuilders.core.BuildException;
import flexbuilders.core.NestedBuilder;
import flexbuilders.graph.BuilderGraph;
import static hmod.loader.graph.AlgorithmBuilders.argFactory;

/**
 *
 * @author Enrique Urra C.
 */
public final class HeuristicDataLoaders
{    
    public static NestedBuilder<IterationHandler> loadIterationData(BuilderGraph graph) throws BuildException
    {
        HeuristicHandlerFactory handlers = HeuristicHandlerFactory.getInstance();
        return argFactory(handlers::iteration, graph.node(HeuristicIds.MAX_ITERATION_CONFIG, builderFor(-1)));
    }
    
    public static NestedBuilder<TimeElapsedHandler> loadTimeElapsedData(BuilderGraph graph) throws BuildException
    {
        HeuristicHandlerFactory handlers = HeuristicHandlerFactory.getInstance();
        return argFactory(handlers::timeElapsed, graph.node(HeuristicIds.MAX_SECONDS_CONFIG, builderFor(-1.0)));
    }
    
    public static NestedBuilder<FinishHandler> loadFinishData(BuilderGraph graph) throws BuildException
    {
        HeuristicHandlerFactory handlers = HeuristicHandlerFactory.getInstance();
        return argFactory(handlers::finish);
    }
}
