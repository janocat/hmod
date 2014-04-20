
package hmod.common.heuristic.components;

/**
 *
 * @author Enrique Urra C.
 */
public final class FinishHandlerFactory
{
    private class InnerHandler implements FinishHandler
    {
        private boolean finished;

        public InnerHandler()
        {
        }   

        @Override
        public void finish()
        {
            finished = true;
        }

        @Override
        public boolean isFinished()
        {
            return finished;
        }
    }
    
    private static FinishHandlerFactory instance;

    public static FinishHandlerFactory getInstance()
    {
        if(instance == null)
            instance = new FinishHandlerFactory();
        
        return instance;
    }

    private FinishHandlerFactory()
    {
    }
    
    public FinishHandler createFinishHandler()
    {
        return new InnerHandler();
    }
}
