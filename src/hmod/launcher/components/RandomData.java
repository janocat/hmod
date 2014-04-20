
package hmod.launcher.components;

import hmod.core.DataHandler;

/**
 *
 * @author Enrique Urra C.
 */
public interface RandomData extends DataHandler
{    
    void setSeed(long seed);
    long getSeed();
}
