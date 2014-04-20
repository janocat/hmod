
package hmod.common.heuristic.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.parser.builders.AlgorithmBuilder;

/**
 *
 * @author Enrique Urra C.
 */
public class SetStartIdScript extends BuildScript
{
    private String id;

    public SetStartIdScript(TreeHandler input, String id)
    {
        super(input);
        
        if(id == null)
            throw new NullPointerException("Null id");
        
        this.id = id;
    }
    
    @Override
    public void process() throws BuildException
    {
        root().getBuildableAs(AlgorithmBuilder.class).startWith(ref(id));
    }
}
