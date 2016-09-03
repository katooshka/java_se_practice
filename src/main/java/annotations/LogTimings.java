package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface LogTimings {
    String name() default "Katooshka";
    long timeLimit() default 10000;
    boolean aggregate() default true;
}


