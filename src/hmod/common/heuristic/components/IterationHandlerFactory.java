
package hmod.common.heuristic.components;

/**
 *
 * @author Enrique Urra C.
 */
public final class IterationHandlerFactory
{
    private class InnerHandler implements IterationHandler
    {
        private final int maxIteration;
        private int currIteration;

        public InnerHandler(int maxIteration)
        {
            this.maxIteration = maxIteration;
        }    

        @Override
        public boolean isIterationsFinished()
        {
            return maxIteration != -1 && currIteration >= maxIteration;
        }

        @Override
        public int getMaxIteration()
        {
            return maxIteration;
        }

        @Override
        public int getCurrentIteration()
        {
            return currIteration + 1;
        }

        @Override
        public void advanceIteration()
        {
            currIteration++;
        }
        
        @Override
        public void resetIterations()
        {
            currIteration = 0;
        }
    }
    
    private static IterationHandlerFactory instance;

    public static IterationHandlerFactory getInstance()
    {
        if(instance == null)
            instance = new IterationHandlerFactory();
        
        return instance;
    }

    private IterationHandlerFactory()
    {
    }
    
    public IterationHandler createIterationHandler(int maxIteration)
    {
        return new InnerHandler(maxIteration);
    }
}
