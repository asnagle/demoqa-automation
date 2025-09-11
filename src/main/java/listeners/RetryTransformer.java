package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("rawtypes")
public final class RetryTransformer implements IAnnotationTransformer {

    private static final Logger demoqaLog = LogManager.getLogger(RetryTransformer.class);

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {
        annotation.setRetryAnalyzer(utils.RetryAnalyzer.class);
        demoqaLog.info("🔁 RetryTransformer applied to: {}", testMethod.getName());
    }
}