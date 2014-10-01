package clojure.lang;

import java.util.Map;

public class CallbackSet {
    private Atom callbacks = new Atom(PersistentHashMap.EMPTY);

    private final IFn assocFn = new AFn() {
            @Override
            public Object invoke(Object cur, Object key, Object callback) {
                return ((IPersistentMap)cur).assoc(key, callback);
            }
        };

    private final IFn withoutFn = new AFn() {
        @Override
        public Object invoke(Object cur, Object key) {
            return ((IPersistentMap)cur).without(key);
        }
    };

    public void add(final Object key, final IFn callback)
    {
        callbacks.swap(assocFn, key, callback);
    }

    public void remove(Object key)
    {
        callbacks.swap(withoutFn, key);
    }

    public void invokeAll()
    {
        IPersistentMap cbs = (IPersistentMap)callbacks.deref();
        if(cbs.count() > 0)
        {
            for(ISeq s = cbs.seq(); s != null; s = s.next())
            {
                Map.Entry e = (Map.Entry) s.first();
                IFn fn = (IFn) e.getValue();
                if(fn != null)
                    fn.invoke();
            }
        }
    }

    public void invokeAll(Object arg1)
    {
        IPersistentMap cbs = (IPersistentMap)callbacks.deref();
        if(cbs.count() > 0)
        {
            for(ISeq s = cbs.seq(); s != null; s = s.next())
            {
                Map.Entry e = (Map.Entry) s.first();
                IFn fn = (IFn) e.getValue();
                if(fn != null)
                    fn.invoke(arg1);
            }
        }
    }

    public void invokeAll(Object arg1, Object arg2)
    {
        IPersistentMap cbs = (IPersistentMap)callbacks.deref();
        if(cbs.count() > 0)
        {
            for(ISeq s = cbs.seq(); s != null; s = s.next())
            {
                Map.Entry e = (Map.Entry) s.first();
                IFn fn = (IFn) e.getValue();
                if(fn != null)
                    fn.invoke(arg1, arg2);
            }
        }
    }

    public void invokeAll(Object arg1, Object arg2, Object arg3)
    {
        IPersistentMap cbs = (IPersistentMap)callbacks.deref();
        if(cbs.count() > 0)
        {
            for(ISeq s = cbs.seq(); s != null; s = s.next())
            {
                Map.Entry e = (Map.Entry) s.first();
                IFn fn = (IFn) e.getValue();
                if(fn != null)
                    fn.invoke(arg1, arg2, arg3);
            }
        }
    }

    public void invokeAll(Object arg1, Object arg2, Object arg3, Object arg4)
    {
        IPersistentMap cbs = (IPersistentMap)callbacks.deref();
        if(cbs.count() > 0)
        {
            for(ISeq s = cbs.seq(); s != null; s = s.next())
            {
                Map.Entry e = (Map.Entry) s.first();
                IFn fn = (IFn) e.getValue();
                if(fn != null)
                    fn.invoke(arg1, arg2, arg3, arg4);
            }
        }
    }
}