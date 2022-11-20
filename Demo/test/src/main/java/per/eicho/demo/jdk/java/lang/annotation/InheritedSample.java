package per.eicho.demo.jdk.java.lang.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

/**
 * <p>@Inherited注解的样例类</p>
 * <pre>
 *  现在各主流框架几乎都提供Java Config，而Java Config的根本就是注解。
 *  {@link java.lang.annotation.Inherited @Inherited}注解也是经常用到的。
 *  不过鉴于其javadoc理解起来有些费劲，一些专有名词的解释并不清楚。所以使用本样例类来进行
 *  一些测试。
 * </pre>
 */
final class InheritedSample {

    @Target(ElementType.ANNOTATION_TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    private static @interface BasicAnnotation { }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @BasicAnnotation
    private static @interface InhertiedAnnotatedAnnotation { }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @BasicAnnotation    
    private static @interface NonInhertiedAnnotatedAnnotation { }


    @InhertiedAnnotatedAnnotation
    private static class InhertiedAnnotatedClass { }

    private static final class InhertiedAnnotatedSubclass extends InhertiedAnnotatedClass {}

    @NonInhertiedAnnotatedAnnotation
    private static class NonInhertiedAnnotatedClass { }

    private static final class NonInhertiedAnnotatedSubclass extends NonInhertiedAnnotatedClass { }

    public static void main(String[] args) {
        System.out.println(isAnnotated(InhertiedAnnotatedClass.class, BasicAnnotation.class));
        System.out.println(isAnnotated(InhertiedAnnotatedClass.class, InhertiedAnnotatedAnnotation.class));
        System.out.println(isAnnotated(InhertiedAnnotatedSubclass.class, InhertiedAnnotatedAnnotation.class));

        System.out.println(isAnnotated(NonInhertiedAnnotatedClass.class, BasicAnnotation.class));
        System.out.println(isAnnotated(NonInhertiedAnnotatedClass.class, NonInhertiedAnnotatedAnnotation.class));
        System.out.println(isAnnotated(NonInhertiedAnnotatedSubclass.class, NonInhertiedAnnotatedAnnotation.class));


        printAnnotations(InhertiedAnnotatedClass.class);
    }

    private static boolean isAnnotated(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        Objects.nonNull(clazz);
        Objects.nonNull(annotationClass);
        return clazz.getAnnotation(annotationClass) != null;
    }

    private static void printAnnotations(Class<?> clazz) {
        for (Annotation annotation : clazz.getAnnotations()) {
            System.out.println(annotation);
        }
    }
    
    private InheritedSample() {}
}
