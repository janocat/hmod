
package hmod.launcher.components;

import hmod.core.DataHandler;
import hmod.launcher.Command;
import hmod.launcher.CommandParser;
import hmod.launcher.CommandRegister;
import hmod.launcher.CommandBufferManager;
import hmod.launcher.CommandExecInfo;

/**
 *
 * @author Enrique Urra C.
 */
public interface CommandData extends DataHandler
{    
    void setCommands(Command[] cmds);
    Command[] getCommands();
    
    void setCurrentCommand(CommandExecInfo cmd);
    CommandExecInfo getCurrentCommand();
    
    void setCommandRegister(CommandRegister register);
    CommandRegister getCommandRegister();
    
    void setCommandBufferManager(CommandBufferManager buffer);
    CommandBufferManager getCommandBufferManager();
    
    void setCommandParser(CommandParser parser);
    CommandParser getCommandParser();
}