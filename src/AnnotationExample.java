
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RolesAllowed {

    String[] value();

}

class UserService {

    @RolesAllowed({"ADMIN", "MANAGER"})
    public void deleteUser(int userId) {
        System.out.println("User with userId %d deleted!".formatted(userId));
    }

    @RolesAllowed("user")
    public void printUserInfo(int userId) {
        System.out.println("Info for user id %d printed!".formatted(userId));
    }

}

public class AnnotationExample {

    public static void main(String[] args) {
        UserService userService = new UserService();
        System.out.println("Entering..");

        try {
            Method method = userService.getClass().getMethod("deleteUser", int.class);
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed annotations = method.getAnnotation(RolesAllowed.class);

                String[] allowedRoles = annotations.value();
                System.out.println("Roles allowed to access method \"%s\" are %s".formatted(method.getName(), Arrays.toString(allowedRoles)));

            }

            method = userService.getClass().getMethod("printUserInfo", int.class);
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed annotations = method.getAnnotation(RolesAllowed.class);

                String[] allowedRoles = annotations.value();
                System.out.println("Roles allowed to access method \"%s\" are %s".formatted(method.getName(), Arrays.toString(allowedRoles)));

            }
        } catch (Exception e) {
            System.out.println("Some exception occurred:" + e);
        }

    }

}
