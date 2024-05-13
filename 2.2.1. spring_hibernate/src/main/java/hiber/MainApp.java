package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car c1 = new Car("MERSEDES", 140);
        Car c2 = new Car("BMW", 525);
        Car c3 = new Car("Lada", 2112);
        Car c4 = new Car("MAZDA", 6);
        userService.add(new User("Василий", "Петров", "petrov@mail.ru", c1));
        userService.add(new User("Aндрей", "Сидоров", "sidorov@mail.ru", c2));
        userService.add(new User("Анна", "Ветрова", "vetrova@mail.ru", c3));
        userService.add(new User("Светлана", "Иванова", "ivanova@mail.ru", c4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().getModel() + "-" + user.getCar().getSeries());
            System.out.println();
        }
        System.out.println(userService.getUserCar("MAZDA", 6).getFirstName());
        context.close();

    }

}

