package my.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageFactoryManager {
    private PageFactoryManager() {
    }

    public static <TPage> TPage get(Class<TPage> pageClass) {
        TPage page = null;

        try {
            page = pageClass.newInstance();
        } catch (InstantiationException var3) {
            InstantiationException e = var3;
            e.printStackTrace();
        } catch (IllegalAccessException var4) {
            IllegalAccessException e = var4;
            e.printStackTrace();
        }

        return page;
    }
}
