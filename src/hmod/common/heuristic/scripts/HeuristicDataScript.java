
package hmod.common.heuristic.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.common.heuristic.components.FinishHandler;
import hmod.common.heuristic.components.FinishHandlerFactory;
import hmod.common.heuristic.components.IterationHandler;
import hmod.common.heuristic.components.IterationHandlerFactory;
import hmod.common.heuristic.components.TimeElapsedHandler;
import hmod.common.heuristic.components.TimeElapsedHandlerFactory;
import static hmod.parser.builders.AlgorithmBuilders.dataProxy;

/**
 *
 * @author Enrique Urra C.
 */
public class HeuristicDataScript extends BuildScript
{
    public HeuristicDataScript(TreeHandler input) throws BuildException
    {
        super(input);
    }

    @Override
    public void process() throws BuildException
    {
        branch(HeuristicRefsIds.COMMON_DATA_MAIN).setBuildable(
            dataProxy().
            addHandler(IterationHandler.class, IterationHandlerFactory.getInstance(), ref(HeuristicRefsIds.COMMON_CONFIG_MAX_ITERATION)).
            addHandler(TimeElapsedHandler.class, TimeElapsedHandlerFactory.getInstance(), ref(HeuristicRefsIds.COMMON_CONFIG_MAX_SECONDS)).
            addHandler(FinishHandler.class, FinishHandlerFactory.getInstance())
        );
    }
}
