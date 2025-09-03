package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@SuppressWarnings("rawtypes") // Only suppress raw type warnings
public final class RetryTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
        System.out.println("🔁 RetryTransformer applied to: " + testMethod.getName());
    }
}