
package hmod.launcher.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface WindowsData extends DataHandler
{    
    void setWindowAutoClose(boolean debug);
    boolean getWindowAutoClose();
}
