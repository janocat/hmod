
package hmod.parser.builders;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.core.Builder;
import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface DataProxyBuilder extends Builder, Buildable<DataHandler>, DataProxyInput
{
    @Override DataProxyBuilder addHandler(Class<? extends DataHandler> handlerType, Object handlerFactory, Buildable... creationArgs) throws BuildException;
}
