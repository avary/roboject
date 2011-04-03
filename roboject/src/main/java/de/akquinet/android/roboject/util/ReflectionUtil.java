package de.akquinet.android.roboject.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Service;


/**
 * Utility class to do common reflection tasks.
 * 
 * @author Philipp Kumar
 */
public class ReflectionUtil
{
    /**
     * Checks the given class and all super classes for a certain class
     * annotation and returns the first one that matches. If no such annotation
     * is found, returns null.
     * 
     * @param clazz
     *            the class to check for the annotation
     * @param annotationClass
     *            the annotation type to look for
     * @return the annotation, if found, null otherwise
     */
    public static <T extends Annotation> T getAnnotation(
            Class<?> clazz, Class<T> annotationClass) {
        if (isTopMostClass(clazz)) {
            return null;
        }

        T annotation = clazz.getAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        return getAnnotation(clazz.getSuperclass(), annotationClass);
    }

    /**
     * Returns all declared fields of a class and all its super classes,
     * regardless of visibility modifier.
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> results = new ArrayList<Field>();
        getFields(results, clazz);
        return results;
    }

    private static void getFields(List<Field> fields, Class<?> clazz) {
        if (isTopMostClass(clazz)) {
            return;
        }

        for (Field field : clazz.getDeclaredFields()) {
            fields.add(field);
        }
        getFields(fields, clazz.getSuperclass());
    }

    private static boolean isTopMostClass(Class<?> clazz) {
        return clazz == null || clazz.equals(Activity.class) || clazz.equals(Service.class)
                || clazz.equals(Object.class);
    }
}