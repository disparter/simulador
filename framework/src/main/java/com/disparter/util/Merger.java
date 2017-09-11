package com.disparter.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disparter.exception.BusinessException;

@Singleton
public final class Merger {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Merger.class);
    
    public <S> S merge(S target, S source) {
        
        Method[] methods = source.getClass().getDeclaredMethods();
        
        Arrays.asList(methods).stream().filter(m -> Modifier.isPublic(m.getModifiers())).filter(m -> m.getName().startsWith("get")).forEach(getterMethod -> {
            
            String gettername = getterMethod.getName();
            String setterName = gettername.replace("get", "set");
            
            try {
                Method setterMethod = target.getClass().getMethod(setterName, getterMethod.getReturnType());
                Object value = getterMethod.invoke(source, (Object[])null);
                if(value != null){
                    setterMethod.invoke(target, value);
                }
            }catch (NoSuchMethodException e){
                LOGGER.debug(e.getMessage(), e);
            }catch (Exception e) {
                BusinessException.throwError(e.getMessage());
            } 
 
        });
        
        return target;
        
    }

}