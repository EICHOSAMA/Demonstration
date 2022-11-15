package per.eicho.demo.jdk.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@TestTypeAnnotation()
public final class ReflectionAPISample {
    
    /**
     * <p>反射基本用例: 获取实例的类信息</p>
     * @param obj non-null
     * @return
     */
    public static Class<?> getClassInfo(Object obj) {
        return obj.getClass();
    }

    /**
     * <p>反射基本用例: 获取实例的类全名</p>
     * <pre>
     *  如 String → "java.lang.String" 
     * </pre>
     * @param obj non-null
     * @return
     */
    public static String getClassName(Object obj) {
        return obj.getClass().getCanonicalName();
    }

    /**
     * <p>反射基本用例: 获取实例的包名</p>
     * <pre>
     *  如 String → "java.lang" 
     * </pre>
     * @param obj non-null
     * @return
     */    
    public static String getPackageName(Object obj) {
        return obj.getClass().getPackageName();
    }

    /**
     * <p>反射基本用例: 获取类级别的Java注解</p>
     * <pre>
     *  
     * </pre>
     * @param obj
     * @return
     */
    public static Annotation[] getAnnotations(Object obj) {
        return obj.getClass().getAnnotations(); // 已经经历过防御性拷贝
    }

    public static boolean isAnnotated(Object obj, Class<? extends Annotation> annotation) {
        return isAnnotated(obj.getClass(), annotation);
    }

    public static boolean isAnnotated(Class<?> clazz, Class<? extends Annotation> annotation) {
        return clazz.isAnnotationPresent(annotation);
    }

    public static List<Method> findAllPublicAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        final List<Method> result = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            System.out.println(method);
            if (!method.isAnnotationPresent(annotation)) continue;
            result.add(method);
        }
        return result;
    } 

    public static void main(String[] args) {
        String obj = "123";
        System.out.println(getClassInfo(obj));
        System.out.println(getClassName(obj));
        System.out.println(getPackageName(obj));

        System.out.println(isAnnotated(obj, TestTypeAnnotation.class));
        System.out.println(isAnnotated(ReflectionAPISample.class, TestTypeAnnotation.class));
    
        System.out.println(findAllPublicAnnotatedMethod(ReflectionAPISample.class, TestMethodAnnotation.class));
    }

    @TestMethodAnnotation
    public void annotatedPublicInstanceMethod() {
        System.out.println("这是一个被注解了的public实例方法");
    }

    @TestMethodAnnotation
    private void annotatedPrivateInstanceMethod() {
        System.out.println("这是一个被注解了的private实例方法");
    }

    @TestMethodAnnotation
    public static void annotatedPublicClassMethod() {
        System.out.println("这是一个被注解了的public类方法");
    }

    @TestMethodAnnotation
    private static void annotatedPrivateClassMethod() {
        System.out.println("这是一个被注解了的private类方法");
    }
}

@Documented
@Retention(RetentionPolicy.RUNTIME) // RUNTIME 是运行时能获取到信息的关键
@Target(ElementType.TYPE)
@interface TestTypeAnnotation {}

@Documented
@Retention(RetentionPolicy.RUNTIME) // RUNTIME 是运行时能获取到信息的关键
@Target(ElementType.METHOD)
@interface TestMethodAnnotation {}