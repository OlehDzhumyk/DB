package com.oleh.view;

import com.oleh.controller.EventController;
import com.oleh.controller.LoginAndPasswordController;
import com.oleh.controller.SeatsController;
import com.oleh.controller.UserController;
import com.oleh.domain.LoginAndPassword;
import com.oleh.domain.Seats;
import com.oleh.domain.Event;
import com.oleh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {
    @Autowired
    private EventController eventController;

    @Autowired
    private UserController userController;

    @Autowired
    private LoginAndPasswordController loginAndPasswordController;

    @Autowired
    private SeatsController seatsController;

    final Map<String, String> menu;
    final Map<String, Printable> methodsMenu;
    final Scanner input = new Scanner(System.in);
    final LoginAndPassword nullLoginAndPassword = new LoginAndPassword(null, null, null);
    final Seats nullSeats = new Seats(null, null, null, null, null, null);
    final User nullUser = new User(null, null, null, null, null, null, null);
    final Event nullEvent = new Event(null, null, null, null, null, null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();

        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: User");
        menu.put("11", "  11 - Create User");
        menu.put("12", "  12 - Update User");
        menu.put("13", "  13 - Delete from User");
        menu.put("14", "  14 - Find all Users");
        menu.put("15", "  15 - Find User by ID");
        menu.put("16", "  16 - Find all Events by id");

        menu.put("2", "   2 - Table: Event");
        menu.put("21", "  21 - Create Event");
        menu.put("22", "  22 - Update Event");
        menu.put("23", "  23 - Delete from Event");
        menu.put("24", "  24 - Find all Events");
        menu.put("25", "  25 - Find Event by ID");
        menu.put("26", "  26 - Find Event by organizer");

        menu.put("3", "   3 - Table: Seats");
        menu.put("31", "  31 - Create Seats");
        menu.put("32", "  32 - Update Seats");
        menu.put("33", "  33 - Delete from Seats");
        menu.put("34", "  34 - Find all Seats");
        menu.put("35", "  35 - Find Seat by ID");
        menu.put("36", "  36 - Find Seat by price");

        menu.put("4", "   4 - Table: Login And Password");
        menu.put("41", "  41 - Create Login And Password");
        menu.put("42", "  42 - Update Login And Password");
        menu.put("43", "  43 - Delete from Login And Password");
        menu.put("44", "  44 - Find all Trips");
        menu.put("45", "  45 - Find Login And Password by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createCar);
        methodsMenu.put("12", this::updateCar);
        methodsMenu.put("13", this::deleteFromCar);
        methodsMenu.put("14", this::findAllCars);
        methodsMenu.put("15", this::findCarById);
        methodsMenu.put("16", this::findCarByCarBrand);
        methodsMenu.put("17", this::findCarByCarClas);

        methodsMenu.put("21", this::createUser);
        methodsMenu.put("22", this::updateUser);
        methodsMenu.put("23", this::deleteFromUser);
        methodsMenu.put("24", this::findAllUsers);
        methodsMenu.put("25", this::findUserById);

        methodsMenu.put("31", this::createDriver);
        methodsMenu.put("32", this::updateDriver);
        methodsMenu.put("33", this::deleteFromDriver);
        methodsMenu.put("34", this::findAllDrivers);
        methodsMenu.put("35", this::findDriverById);
        methodsMenu.put("36", this::findAllCarsById);

        methodsMenu.put("41", this::createTrip);
        methodsMenu.put("42", this::updateTrip);
        methodsMenu.put("43", this::deleteFromTrip);
        methodsMenu.put("44", this::findAllTrips);
        methodsMenu.put("45", this::findTripById);
    }

    private void selectAllTable() {
        findAllCars();
        findAllUsers();
        findAllDrivers();
        findAllTrips();
    }

    // region USER ---------------------------------------------------
    private void createUser() {
        System.out.println("Input 'car brand': ");
        String brand = input.nextLine();
        System.out.println("Input 'clas': ");
        String clas = input.nextLine();
        LoginAndPassword loginAndPassword = new LoginAndPassword(null, brand, clas);

        int count = carController.create(loginAndPassword);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCar() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'brand': ");
        String brand = input.nextLine();
        System.out.println("Input new 'clas': ");
        String clas = input.nextLine();
        LoginAndPassword loginAndPassword = new LoginAndPassword(null, brand, clas);

        int count = carController.update(id, loginAndPassword);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCar() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = carController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCars() {
        System.out.println("\nTable: CAR");
        List<LoginAndPassword> loginAndPasswords = carController.findAll();
        for (LoginAndPassword loginAndPassword : loginAndPasswords) {
            System.out.println(loginAndPassword);
        }
    }

    private void findCarById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<LoginAndPassword> car = carController.findById(id);
        System.out.println(car.orElse(nullLoginAndPassword));
    }

    private void findCarByCarBrand() {
        System.out.println("Input 'car brand': ");
        String brand = input.nextLine();

        Optional<LoginAndPassword> car = carController.findByCarBrand(brand);
        System.out.println(car.orElse(nullLoginAndPassword));
    }

    private void findCarByCarClas() {
        System.out.println("Input 'clas': ");
        String clas = input.nextLine();

        Optional<LoginAndPassword> car = carController.findByCarClas(clas);
        System.out.println(car.orElse(nullLoginAndPassword));
    }
    //endregion CAR


    // region USER ------------------------------------------
    private void createUser() {
        System.out.println("Input 'user name': ");
        String name = input.nextLine();
        System.out.println("Input 'user rating': ");
        Integer rating = Integer.valueOf((input.nextLine()));

        User user = new User(name, rating);

        int count = userController.create(user);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateUser() {
        System.out.println("Input 'user new name': ");
        String name = input.nextLine();

        System.out.println("Input 'user new rating': ");
        Integer rating = Integer.valueOf((input.nextLine()));

        User user = new User(name, rating);

        int count = userController.update(name, user);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromUser() {
        System.out.println("Input 'user name': ");
        String name = input.nextLine();

        int count = userController.delete(name);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllUsers() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findUserById() {
        System.out.println("Input 'user name': ");
        String name = input.nextLine();

        Optional<User> user = userController.findById(name);
        System.out.println(user.orElse(nullUser));
    }
    //endregion USER


    // region DRIVER -------------------------------------------------
    private void createDriver() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'rating': ");
        Integer rating = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'completed orders': ");
        Integer completedOrders = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'is driver vacant(0 - false, 1 - true)': ");
        Integer isVacant = Integer.valueOf((input.nextLine()));

        Seats seats = new Seats(null, name, rating,
                completedOrders, isVacant);

        int count = driverController.create(seats);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateDriver() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'rating': ");
        Integer rating = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'completed orders': ");
        Integer completedOrders = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'is driver vacant(0 - false, 1 - true)': ");
        Integer isVacant = Integer.valueOf((input.nextLine()));

        Seats seats = new Seats(null, name, rating, completedOrders, isVacant);

        int count = driverController.update(id, seats);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromDriver() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = driverController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllDrivers() {
        System.out.println("\nTable: DRIVER");
        List<Seats> seats = driverController.findAll();
        for (Seats seats : seats) {
            System.out.println(seats);
        }
    }

    private void findDriverById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Seats> driver = driverController.findById(id);
        System.out.println(driver.orElse(nullSeats));
    }

    private void findAllCarsById() {
        System.out.println("Input 'driver id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        List<LoginAndPassword> loginAndPasswords = driverController.findAllCarsBy(id);
        for (LoginAndPassword loginAndPassword : loginAndPasswords) {
            System.out.println(loginAndPassword);
        }
    }
    //endregion DRIVER


    // region TRIP
    private void createTrip() {
        System.out.println("Input 'start_point': ");
        String startPoint = input.nextLine();
        System.out.println("Input 'end_point': ");
        String endPoint = input.nextLine();
        System.out.println("Input 'driver_id': ");
        Integer driverId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'user name the driver drives with': ");
        String userName = input.nextLine();

        Event event = new Event(null, startPoint, endPoint, driverId, userName);

        int count = tripController.create(event);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateTrip() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'start_point': ");
        String startPoint = input.nextLine();
        System.out.println("Input 'end_point': ");
        String endPoint = input.nextLine();
        System.out.println("Input 'driver_id': ");
        Integer driverId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'user name the driver drives with': ");
        String userName = input.nextLine();

        Event event = new Event(null, startPoint, endPoint, driverId, userName);

        int count = tripController.update(id, event);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteFromTrip() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = tripController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllTrips() {
        System.out.println("\nTable: TRIP");
        List<Event> events = tripController.findAll();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private void findTripById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Event> trip = tripController.findById(id);
        System.out.println(trip.orElse(nullEvent));
    }
    // endregion TRIP

    //-------------------------------------------------------------------------

    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {
        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!keyMenu.equals("Q"));
    }
    //endregion output

}
