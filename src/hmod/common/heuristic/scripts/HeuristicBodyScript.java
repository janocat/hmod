
package hmod.common.heuristic.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.common.heuristic.components.CheckHeuristicFinished;
import hmod.common.heuristic.components.NextIteration;
import hmod.common.heuristic.components.StartElapsedTime;
import static hmod.parser.builders.AlgorithmBuilders.booleanStep;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import static hmod.parser.builders.AlgorithmBuilders.subProcessStep;

/**
 *
 * @author Enrique Urra C.
 */
public class HeuristicBodyScript extends BuildScript
{
    private final BranchBuilder start, init, checkFinish, iteration, postIteration, finish;
    private final Buildable data;
    
    public HeuristicBodyScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(HeuristicRefsIds.COMMON_MAIN_START);
        init = branch();
        checkFinish = branch();
        iteration = branch();
        postIteration = branch();
        finish = branch();
        
        data = ref(HeuristicRefsIds.COMMON_DATA_MAIN);
    }

    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            sequentialStep().setNextStep(init).
            addOperator(
                operator(StartElapsedTime.class).
                injectData(data)
            )
        );
        
        init.setBuildable(
            subProcessStep().setNextStep(checkFinish).
            setSubStep(ref(HeuristicRefsIds.COMMON_CONFIG_INIT_ID))
        );
        
        checkFinish.setBuildable(
            booleanStep().setTrueStep(finish).setFalseStep(iteration).
            setDecider(
                operator(CheckHeuristicFinished.class).
                injectData(data)
            )
        );
        
        iteration.setBuildable(
            subProcessStep().setNextStep(postIteration).
            setSubStep(ref(HeuristicRefsIds.COMMON_CONFIG_ITERATION_ID))
        );
        
        postIteration.setBuildable(
            sequentialStep().setNextStep(checkFinish).
            addOperator(
                operator(NextIteration.class).
                injectData(data)
            )
        );
        
        finish.setBuildable(
            subProcessStep().
            setSubStep(ref(HeuristicRefsIds.COMMON_CONFIG_FINISH_ID))
        );
    }
}
