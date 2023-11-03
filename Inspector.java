import java.lang.reflect.Method;

public class Inspector<T> {

        private Class<T> inspectedClass;

        public Inspector(Class<T> inspectedClass) {
            this.inspectedClass = inspectedClass;
        }

        public void displayInformations() {
            System.out.println("Information of the \"" + inspectedClass.getName() + "\" class:");
            System.out.println("Superclass: " + inspectedClass.getSuperclass().getName());
            System.out.println(inspectedClass.getDeclaredMethods().length + " methods:");
            for (Method method : inspectedClass.getDeclaredMethods()) {
                System.out.println("- " + method.getName());
            }
            System.out.println(inspectedClass.getDeclaredFields().length + " fields:");
            for (java.lang.reflect.Field field : inspectedClass.getDeclaredFields()) {
                System.out.println("- " + field.getName());
            }

        }

        public T createInstance() throws InstantiationException, IllegalAccessException {
            return inspectedClass.newInstance();
        }

}
