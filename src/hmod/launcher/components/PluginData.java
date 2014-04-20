
package hmod.launcher.components;

import hmod.core.DataHandler;
import hmod.launcher.Plugin;

/**
 *
 * @author Enrique Urra C.
 */
public interface PluginData extends DataHandler
{    
    void setPlugins(Plugin[] plugins);
    Plugin[] getPlugins();
}
