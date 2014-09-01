
package hmod.solvers.common.ac;

import static flexbuilders.graph.GraphFactory.nodeId;
import flexbuilders.graph.NodeLoaderData;
import flexbuilders.graph.NodeGroup;
import flexbuilders.graph.NodeId;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public final class HeuristicIds implements NodeGroup
{
    private HeuristicIds()
    {
    }
    
    public static final NodeId<Integer> MAX_ITERATION_CONFIG = nodeId(
        "HeuristicIds.MAX_ITERATION_CONFIG"
    );    
    
    public static final NodeId<Double> MAX_SECONDS_CONFIG = nodeId(
        "HeuristicIds.MAX_SECONDS_CONFIG"
    );
    
    public static final NodeId<IterationHandler> ITERATION_DATA = nodeId(
        "HeuristicIds.ITERATION_DATA",
        new NodeLoaderData<>(HeuristicDataLoaders::loadIterationData)
    );
    
    public static final NodeId<TimeElapsedHandler> TIME_ELAPSED_DATA = nodeId(
        "HeuristicIds.TIME_ELAPSED_DATA",
        new NodeLoaderData<>(HeuristicDataLoaders::loadTimeElapsedData)
    );
    
    public static final NodeId<FinishHandler> FINISH_DATA = nodeId(
        "HeuristicIds.FINISH_DATA",
        new NodeLoaderData<>(HeuristicDataLoaders::loadFinishData)
    );

    public static final NodeId<Step> MAIN_START = nodeId(
        "HeuristicIds.MAIN_START",
        new NodeLoaderData<>(HeuristicStartLoader::load)
    );
    
    public static final NodeId<Step> INIT_START = nodeId(
        "HeuristicIds.INIT_START"
    );
    
    public static final NodeId<Step> ITERATION_START = nodeId(
        "HeuristicIds.ITERATION_START"
    );
    
    public static final NodeId<Step> FINISH_START = nodeId(
        "HeuristicIds.FINISH_START"
    );
}
