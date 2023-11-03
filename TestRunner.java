import java.lang.reflect.Method;

public class TestRunner {

    public void runTests(Class<?> testClass) {
        Object testObject;
        try {
            testObject = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Execute @BeforeClass method if exists
        executeBeforeClass(testClass, testObject);

        Method[] methods = testClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                if (test.enabled()) {
                    // Execute @Before method before each test
                    executeBefore(testClass, testObject);

                    System.out.println(test.name());
                    try {
                        method.invoke(testObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Execute @After method after each test
                    executeAfter(testClass, testObject);
                }
            }
        }

        // Execute @AfterClass method if exists
        executeAfterClass(testClass, testObject);
    }

    private void executeBeforeClass(Class<?> testClass, Object testObject) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeClass.class)) {
                try {
                    method.invoke(testObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void executeAfterClass(Class<?> testClass, Object testObject) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterClass.class)) {
                try {
                    method.invoke(testObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void executeBefore(Class<?> testClass, Object testObject) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                try {
                    method.invoke(testObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void executeAfter(Class<?> testClass, Object testObject) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(After.class)) {
                try {
                    method.invoke(testObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
