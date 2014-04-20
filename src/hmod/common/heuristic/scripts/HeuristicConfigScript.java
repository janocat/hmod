
package hmod.common.heuristic.scripts;

import static flexbuilders.basic.BasicBuilders.value;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class HeuristicConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_START_ID = "hmod.common.heuristic.config.startId";
    public static final String ENTRY_DATA_ID = "hmod.common.heuristic.config.dataId";
    public static final String ENTRY_INIT_ID = "hmod.common.heuristic.config.initId";
    public static final String ENTRY_ITERATION_ID = "hmod.common.heuristic.config.iterationId";
    public static final String ENTRY_FINISH_ID = "hmod.common.heuristic.config.finishId";
    public static final String ENTRY_MAX_ITERATIONS = "hmod.common.heuristic.config.maxIterations";
    public static final String ENTRY_MAX_SECONDS = "hmod.common.heuristic.config.maxSeconds";
            
    private final String file;
    
    public HeuristicConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
        file = propertiesFile;
    }

    @Override
    public void process() throws BuildException
    {
        int maxIteration;
        double maxSeconds;
        
        try
        {
            maxIteration = Integer.parseInt(getEntry(ENTRY_MAX_ITERATIONS));
            maxSeconds = Double.parseDouble(getEntry(ENTRY_MAX_SECONDS));
        }
        catch(NumberFormatException ex)
        {
            throw new BuildException("Error while parsing the '" + file + "' config file: " + ex.getMessage());
        }
        
        branch(getEntry(ENTRY_DATA_ID)).setBuildable(ref(HeuristicRefsIds.COMMON_DATA_MAIN));
        branch(getEntry(ENTRY_START_ID)).setBuildable(ref(HeuristicRefsIds.COMMON_MAIN_START));
        
        branch(HeuristicRefsIds.COMMON_CONFIG_INIT_ID).setBuildable(ref(getEntry(ENTRY_INIT_ID)));
        branch(HeuristicRefsIds.COMMON_CONFIG_ITERATION_ID).setBuildable(ref(getEntry(ENTRY_ITERATION_ID)));
        branch(HeuristicRefsIds.COMMON_CONFIG_FINISH_ID).setBuildable(ref(getEntry(ENTRY_FINISH_ID)));
        
        branch(HeuristicRefsIds.COMMON_CONFIG_MAX_ITERATION).setBuildable(value(maxIteration));
        branch(HeuristicRefsIds.COMMON_CONFIG_MAX_SECONDS).setBuildable(value(maxSeconds));
    }
}
