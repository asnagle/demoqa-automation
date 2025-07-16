package customAnnotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface CaptureOnSuccess {
	
	String description() default "";
    String screenshotMode() default "full"; // or "viewport"

}
