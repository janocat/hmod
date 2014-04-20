
package hmod.parser.builders;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.core.StackBuildable;
import hmod.core.DataHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import optefx.util.reflection.ReflectionTool;

/**
 *
 * @author Enrique Urra C.
 */
class DataProxyBuilderImpl extends StackBuildable<DataHandler> implements DataProxyBuilder
{
    private class InnerProxy implements InvocationHandler
    {
        Map<Method, Object> proxyMap;

        public InnerProxy(Map<Method, Object> proxyMap)
        {
            this.proxyMap = proxyMap;
        }
        
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws BuildException
        {
            Object handler = proxyMap.get(method);
            
            if(handler == null)
                throw new BuildException("The method '" + method + "' is not executable in this proxy");
            
            try
            {
                return method.invoke(handler, args);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
            {
                throw new BuildException("Cannot execute the method '" + method + "' in this proxy");
            }
        }
    }
    
    private class HandlerEntry
    {
        private final Object factory;
        private final Buildable[] creationArgs;

        public HandlerEntry(Object factory, Buildable[] creationArgs)
        {
            this.factory = factory;
            this.creationArgs = creationArgs;
        }

        public Object getFactory()
        {
            return factory;
        }

        public Buildable[] getCreationArgs()
        {
            return creationArgs;
        }
    }
    
    private DataHandler result;
    private Set<Object> implementors;
    private Map<Method, Object> proxyMethodsMap;
    private final Map<Class, HandlerEntry> entries;

    public DataProxyBuilderImpl()
    {
        entries = new HashMap<>();
    }
    
    @Override
    public DataProxyBuilder addHandler(Class<? extends DataHandler> handlerType, Object handlerFactory, Buildable[] creationArgs) throws BuildException
    {
        if(handlerType == null)
            throw new BuildException("Null handler type");
        
        if(handlerFactory == null)
            throw new BuildException("Null factory");
        
        if(creationArgs == null)
            creationArgs = new Buildable[0];
        
        entries.put(handlerType, new HandlerEntry(handlerFactory, creationArgs));        
        return this;
    }

    @Override
    public void buildInstance() throws BuildException
    {
        Class[] handlerTypes = entries.keySet().toArray(new Class[0]);
        Map<Method, Object> proxyMap = new HashMap<>();
        
        for(Class handlerType : handlerTypes)
        {
            HandlerEntry entry = entries.get(handlerType);
            String creationMethodName = "create" + handlerType.getSimpleName();
            
            Object factory = entry.getFactory();
            Buildable[] creationArgsBuilders = entry.getCreationArgs();
            Object[] creationArgs = new Object[creationArgsBuilders.length];
            Class[] creationArgsTypes = new Class[creationArgsBuilders.length];
            
            for(int i = 0; i < creationArgsBuilders.length; i++)
            {
                creationArgs[i] = creationArgsBuilders[i].build();
                creationArgsTypes[i] = creationArgs[i] == null ? null : ReflectionTool.getClassFromObject(creationArgs[i]);
            }
            
            Method factoryMethod = null;
            
            try 
            {
                factoryMethod = factory.getClass().getMethod(creationMethodName, creationArgsTypes);
            }
            catch (NoSuchMethodException | SecurityException ex)
            {
                throw new BuildException("Cannot retrieve a factory method '" + creationMethodName + "' from type '" + factory.getClass() + "' to create an instance of '" + handlerType + "'", ex);
            }
            
            if(!handlerType.isAssignableFrom(factoryMethod.getReturnType()))
                throw new BuildException("The return type of the factory method '" + creationMethodName + "' of type '" + factory.getClass() + "' is not compatible with '" + handlerType + "'");
            
            Object handlerInstance = null;
            
            try
            {
                handlerInstance = (DataHandler)factoryMethod.invoke(factory, creationArgs);
            }
            catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
            {
                throw new BuildException("Cannot invoke the factory method '" + creationMethodName + "' of type '" + factory.getClass() + "' to create an instance of '" + handlerType + "'");
            }
            
            Method[] handlerTypeMethods = handlerType.getDeclaredMethods();
            
            for(Method handlerMethod : handlerTypeMethods)
                proxyMap.put(handlerMethod, handlerInstance);
        }
        
        result = (DataHandler)Proxy.newProxyInstance(
            handlerTypes[0].getClassLoader(),
            handlerTypes,
            new InnerProxy(proxyMap)
        );
    }

    @Override
    public DataHandler getInstance() throws BuildException
    {
        return result;
    }    
}
