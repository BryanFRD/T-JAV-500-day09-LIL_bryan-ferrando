import java.lang.reflect.Method;

public class TestRunner {

    public void runTests(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            if(method.isAnnotationPresent(Test.class)){
                Test test = method.getAnnotation(Test.class);
                System.out.println(test.name());
                if(test.enabled()){
                    try {
                        method.invoke(clazz.newInstance());
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

}
