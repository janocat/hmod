
package hmod.parser.builders;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.core.Delegate;
import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface DataProxyInput extends Delegate
{
    DataProxyInput addHandler(Class<? extends DataHandler> handlerType, Object handlerFactory, Buildable... creationArgs) throws BuildException;
}
