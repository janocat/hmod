
package hmod.launcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a buffer for command entries, whose actual index can be marked and
 * restored conveniently.
 * @author Enrique Urra C.
 */
public class CommandBuffer
{
    private class MarkInfo
    {
        int pos;
        int resetLimit;
        int resetCount;
        String desc;

        public MarkInfo(int pos, int resetLimit, String desc)
        {
            this.pos = pos;
            this.resetLimit = resetLimit;
            this.resetCount = 1;
            this.desc = desc;
        }
    }
    
    /**
     * The stack of executable commands.
     */
    private List<CommandExecInfo> cmdStack;
    /**
     * The stack of marks.
     */
    private List<MarkInfo> markStack;
    /**
     * The buffer current position.
     */
    private int currIndex;

    /**
     * Default constructor.
     */
    public CommandBuffer()
    {
        this.cmdStack = new ArrayList<>();
        this.markStack = new ArrayList<>();
        this.currIndex = 0;
    }
    
    /**
     * Checks if the buffer is empty.
     * @return true if is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return cmdStack.isEmpty();
    }

    /**
     * Push a new command execution into the buffer.
     * @param cmd The info bean.
     */
    public void pushCommand(CommandExecInfo cmd)
    {
        if(cmd == null)
            return;
        
        cmdStack.add(cmd);
    }
    
    /**
     * Checks if the buffer's current position is at end.
     * @return true if is finished, false otherwise.
     */
    public boolean isFinished()
    {
        return currIndex == cmdStack.size();
    }
    
    /**
     * Gets the current command execution and advances a position within the
     * buffer.
     * @return The current command execution, or null if the position is at the
     * buffer's end.
     */
    public CommandExecInfo next()
    {
        if(isFinished())
            return null;
        
        CommandExecInfo cmdInfo = cmdStack.get(currIndex++);
        return cmdInfo;
    }
    
    /**
     * Push a new mark into the buffer with no reset limit and no description.
     * @return true if the mark was successful pushed, false if the buffer is
     * empty.
     */
    public boolean pushMark()
    {
        return pushMark(0, null);
    }
    
    /**
     * Push a new mark into the buffer. The mark stores the current position, 
     * then the latter can be restores by using the reset() method.
     * A number of max. resets can be specified for the mark so when the reset 
     * number is reached, the last reset does not have effect. If the limit is
     * not specified (value zero) then the reset will always work.
     * @param resetLimit The max. resets available for the mark. Use 0 for
     *  specify no limit on this value.
     * @return true if the mark was successful pushed, false if the buffer is
     * empty.
     */
    public boolean pushMark(int resetLimit, String desc)
    {
        if(isEmpty())
            return false;
        
        if(resetLimit < 0)
            throw new IllegalArgumentException("The reset limit must be greater or equal to zero.");
        
        markStack.add(new MarkInfo(currIndex, resetLimit, desc));
        return true;
    }
    
    /**
     * Gets the description of the active mark.
     * @return The description as String, or null if it has not been specified.
     */
    public String getMarkDescription()
    {
        if(!isMarked())
            return null;
        
        return markStack.get(markStack.size() - 1).desc;
    }
    
    /**
     * Gets the max. reset of the active mark.
     * @return the max. as integer, or -1 if the buffer is not marked.
     */
    public int getMarkResetLimit()
    {
        if(!isMarked())
            return -1;
        
        return markStack.get(markStack.size() - 1).resetLimit;
    }
    
    /**
     * Gets the reset count of the active mark.
     * @return the count as integer, or -1 if the buffer is not marked.
     */
    public int getMarkResetCount()
    {
        if(!isMarked())
            return -1;
        
        return markStack.get(markStack.size() - 1).resetCount;
    }
    
    /**
     * Pops the latter mark added into de buffer.
     * @return true if the mark was successful poped, false if there are no 
     * marks added.
     */
    public boolean popMark()
    {
        if(!isMarked())
            return false;
        
        markStack.remove(markStack.size() - 1);
        return true;
    }
    
    /**
     * Reset the buffer's current position to the last mark added if its max. 
     * reset count has not been reached, or to the first position if no mark has
     * been added.
     * @return true if the reposition was successful, or false if there is a
     *  current mark and the reset limit has been already reached.
     */
    public boolean reset()
    {
        if(!isMarked())
        {
            currIndex = 0;
            return true;
        }
        
        MarkInfo info = markStack.get(markStack.size() - 1);
        
        if(info.resetLimit > 0)
        {            
            if(info.resetCount >= info.resetLimit)
                return false;
            else
                info.resetCount++;
        }
        
        currIndex = info.pos;
        return true;
    }
    
    /**
     * Checks if there is an active mark on the buffer.
     * @return true if a mark exists, false otherwise.
     */
    public boolean isMarked()
    {
        return !markStack.isEmpty();
    }
    
    /**
     * Gets the number of added commands.
     * @return the number as int.
     */
    public int count()
    {
        return cmdStack.size();
    }
}
