
package hmod.launcher.commands;

import hmod.parser.AlgorithmParser;
import hmod.core.Algorithm;
import hmod.launcher.LauncherException;
import hmod.launcher.CommandInfo;
import hmod.launcher.Command;
import hmod.launcher.components.LauncherData;
import hmod.launcher.components.RandomData;
import hmod.launcher.components.RunData;
import hmod.launcher.running.AlgorithmInterface;
import hmod.launcher.running.AlgorithmRunner;
import hmod.parser.AlgorithmParserException;
import optefx.util.output.DefaultOutputConfigBuilder;
import optefx.util.output.OutputConfig;
import optefx.util.output.OutputManager;
import optefx.util.random.RandomTool;

/**
 * Implements the 'run' launcher command
 * @author Enrique Urra C.
 */
@CommandInfo(
    word="run",
    usage="run [name]",
    description="Runs the currently assembled algorithm.\n"
        + "name: An optional name for the executed algorithm."
)
public class RunCommand extends Command
{
    private LauncherData launcherHandler;
    private RunData runHandler;
    private RandomData randomHandler;
    
    public void setLauncherData(LauncherData handler)
    {
        this.launcherHandler = handler;
    }

    public void setRunData(RunData handler)
    {
        this.runHandler = handler;
    }

    public void setRandomData(RandomData handler)
    {
        this.randomHandler = handler;
    }
    
    @Override
    public void executeCommand(String[] args) throws LauncherException
    {      
        String name = "(no name)";
        
        if(args.length > 0)
            name = args[0];
        
        AlgorithmParser parser = launcherHandler.getParser();
        Algorithm algorithm;

        try
        {
            algorithm = parser.getAlgorithm();
        }
        catch(AlgorithmParserException ex)
        {
            if(ex.requireRestart())
                parser.restart();
            
            throw new LauncherException(ex.getLocalizedMessage(), ex);
        }
           
        AlgorithmRunner runner = runHandler.getRunManager().getCurrentRunnerFactory().createRunner();
        AlgorithmInterface ui = runHandler.getRunManager().getCurrentInterfaceFactory().createInterface(name);
        
        DefaultOutputConfigBuilder outputConfigBuilder = launcherHandler.getOutputConfigBuilder();
        ui.configOutputs(outputConfigBuilder);
        
        Thread algorithmThread = runner.getAlgorithmThread();
        
        OutputConfig outputConfig = outputConfigBuilder.
            addSystemOutputId(OutputManager.DEFAULT_ID).
            addSystemErrorOutputId(OutputManager.DEFAULT_ERROR_ID).
            build();
        
        launcherHandler.setOutputConfigBuilder(new DefaultOutputConfigBuilder());
        long randomSeed = randomHandler.getSeed();

        runner.init(algorithm, outputConfig, randomSeed);
        ui.init(algorithm, algorithmThread);

        randomHandler.setSeed(RandomTool.getInstance().createRandomSeed());
    }
}