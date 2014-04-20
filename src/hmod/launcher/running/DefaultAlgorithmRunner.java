
package hmod.launcher.running;

import hmod.core.Algorithm;
import hmod.core.AlgorithmException;
import hmod.launcher.Launcher;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
class DefaultAlgorithmRunner extends AlgorithmRunner
{
    @Override
    public void runAlgorithm(Algorithm algorithm)
    {
        try
        {
            OutputManager.println(OutputManager.DEFAULT_ID, "Starting execution...");
            long start = System.currentTimeMillis();

            algorithm.start();

            long end = System.currentTimeMillis();
            OutputManager.println(OutputManager.DEFAULT_ID, "Execution Finished (total time: " + ((end - start) / 1000.0f) + "s).");
        }
        catch(AlgorithmException ex)
        {
            OutputManager.print(OutputManager.DEFAULT_ERROR_ID, "Algorithm Error: ");
            ex.printStackTrace(OutputManager.getCurrent().getOutput(OutputManager.DEFAULT_ERROR_ID));
        }
    }
}