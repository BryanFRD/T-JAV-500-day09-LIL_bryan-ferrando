import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public void runTests(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();

        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> beforeClassMethods = new ArrayList<>();
        List<Method> afterClassMethods = new ArrayList<>();

        for (Method method : methods){
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations){
                if (annotation instanceof Before){
                    beforeMethods.add(method);
                } else if (annotation instanceof After){
                    afterMethods.add(method);
                } else if (annotation instanceof BeforeClass){
                    beforeClassMethods.add(method);
                } else if (annotation instanceof AfterClass){
                    afterClassMethods.add(method);
                }
            }
        }

        for(Method method : beforeClassMethods){
            try {
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (Method method : methods){
            if(method.isAnnotationPresent(Test.class)){
                for (Method beforeMethod : beforeMethods){
                    try {
                        beforeMethod.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Test test = method.getAnnotation(Test.class);
                if(test.enabled()){
                    System.out.println("Test executed");
                    try {
                        method.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for(Method afterMethod : afterMethods){
                    try {
                        afterMethod.invoke(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for(Method method : afterClassMethods){
            try {
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
