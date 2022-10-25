package com.oleh.view;

import com.oleh.controller.EventController;
import com.oleh.controller.LoginAndPasswordController;
import com.oleh.controller.SeatsController;
import com.oleh.controller.UserController;
import com.oleh.domain.Event;
import com.oleh.domain.LoginAndPassword;
import com.oleh.domain.Seats;
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
    final User nullUser = new User(null ,null, null, null, null, null, null, null);
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

        methodsMenu.put("11", this::createUser);
        methodsMenu.put("12", this::updateUser);
        methodsMenu.put("13", this::deleteFromUser);
        methodsMenu.put("14", this::findAllUsers);
        methodsMenu.put("15", this::findUserById);
        methodsMenu.put("16", this::findAllEventsByUserId);

        methodsMenu.put("21", this::createEvent);
        methodsMenu.put("22", this::updateEvent);
        methodsMenu.put("23", this::deleteFromEvent);
        methodsMenu.put("24", this::findAllEvent);
        methodsMenu.put("25", this::findEventById);

        methodsMenu.put("31", this::createSeats);
        methodsMenu.put("32", this::updateSeats);
        methodsMenu.put("33", this::deleteFromSeats);
        methodsMenu.put("34", this::findAllSeats);
        methodsMenu.put("35", this::findSeatsById);

        methodsMenu.put("41", this::createLoginAndPassword);
        methodsMenu.put("42", this::updateLoginAndPassword);

    }

    private void selectAllTable() {
        findAllUsers();
        findAllSeats();
        findAllEvent();

    }

    // region USER ---------------------------------------------------
    private void createLoginAndPassword() {

        System.out.println("Input login: ");
        String login = input.nextLine();
        System.out.println("Input password: ");
        String password = input.nextLine();

        LoginAndPassword loginAndPassword = new LoginAndPassword(null, login, password);


        int count = loginAndPasswordController.create(loginAndPassword);

        System.out.printf("There are created %d rows\n", count);
    }

    private void updateLoginAndPassword() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input login: ");
        String login = input.nextLine();
        System.out.println("Input password: ");
        String password = input.nextLine();

        LoginAndPassword loginAndPassword = new LoginAndPassword(null, login, password);

        int count = loginAndPasswordController.update(id, loginAndPassword);
        System.out.printf("There are updated %d rows\n", count);
    }


    //endregion CAR


    // region USER ------------------------------------------
    private void createUser() {
        System.out.println("Input user name: ");
        String name = input.nextLine();
        System.out.println("Input second name: ");
        String secondName = input.nextLine();
        System.out.println("Input phone number: ");
        String phone = input.nextLine();
        System.out.println("Input email: ");
        String email = input.nextLine();
        System.out.println("Input city: ");
        String city = input.nextLine();
        System.out.println("Input region: ");
        String region = input.nextLine();
        System.out.println("Input login id: ");
        Integer loginAndPasswordId = Integer.valueOf((input.nextLine()));

        User user = new User(null, name, secondName, phone, email, city, region, loginAndPasswordId);

        int count = userController.create(user);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateUser() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));
        System.out.println("Input user name: ");
        String name = input.nextLine();
        System.out.println("Input second name: ");
        String secondName = input.nextLine();
        System.out.println("Input phone number: ");
        String phone = input.nextLine();
        System.out.println("Input email: ");
        String email = input.nextLine();
        System.out.println("Input city: ");
        String city = input.nextLine();
        System.out.println("Input region: ");
        String region = input.nextLine();
        Integer loginAndPasswordId = Integer.valueOf((input.nextLine()));
        User user = new User(id, name, secondName, phone, email, city, region, loginAndPasswordId);
        int count = userController.update(id, user);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromUser() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = userController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllUsers() {
        System.out.println("\nTable: USER");
        List<User> users = userController.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private void findAllEventsByUserId() {
        System.out.println("Input user id: ");
        Integer id = Integer.valueOf((input.nextLine()));
        List<Event> events = userController.findAllEventsBy(id);
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private void findUserById() {
        System.out.println("Input id: ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<User> user = userController.findById(id);
        System.out.println(user.orElse(nullUser));
    }
    //endregion USER


    // region Event -------------------------------------------------
    private void createEvent() {
        System.out.println("Input name of Event: ");
        String name = input.nextLine();
        System.out.println("Input organizer: ");
        String organizer = input.nextLine();
        System.out.println("Input phone number: ");
        String phoneNumber = input.nextLine();
        System.out.println("Input date: ");
        String date = input.nextLine();
        System.out.println("Input address: ");
        String address = input.nextLine();
        System.out.println("Input duration: ");
        String duration = input.nextLine();
        System.out.println("Input event information: ");
        String eventInformation = input.nextLine();


        Event event = new Event(null, organizer, phoneNumber,
                address, date, duration, eventInformation, name);

        int count = eventController.create(event);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateEvent() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input name of Event: ");
        String name = input.nextLine();
        System.out.println("Input organizer: ");
        String organizer = input.nextLine();
        System.out.println("Input phone number: ");
        String phoneNumber = input.nextLine();
        System.out.println("Input date: ");
        String date = input.nextLine();
        System.out.println("Input address: ");
        String address = input.nextLine();
        System.out.println("Input duration: ");
        String duration = input.nextLine();
        System.out.println("Input event information: ");
        String eventInformation = input.nextLine();

        Event event = new Event(null, organizer, phoneNumber,
                address, date, duration, eventInformation, name);

        int count = eventController.update(id, event);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromEvent() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = eventController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllEvent() {
        System.out.println("\nTable: EVENT");
        List<Event> events = eventController.findAll();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private void findEventById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Event> event = eventController.findById(id);
        System.out.println(event.orElse(nullEvent));

    }


    //endregion DRIVER


    // region Seats
    private void createSeats() {
        System.out.println("Input Section: ");
        String section = input.nextLine();
        System.out.println("Input number: ");
        String number = input.nextLine();
        System.out.println("Input price: ");
        Integer price = Integer.valueOf(input.nextLine());
        System.out.println("Input reservation status: ");
        String reservationStatus = input.nextLine();
        System.out.println("Input event id: ");
        Integer eventId = Integer.valueOf(input.nextLine());

        Seats seats = new Seats(null, section, number, price, reservationStatus, eventId);

        int count = seatsController.create(seats);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateSeats() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input Section: ");
        String section = input.nextLine();
        System.out.println("Input number: ");
        String number = input.nextLine();
        System.out.println("Input price: ");
        Integer price = Integer.valueOf(input.nextLine());
        System.out.println("Input reservation status: ");
        String reservationStatus = input.nextLine();
        System.out.println("Input event id: ");
        Integer eventId = Integer.valueOf(input.nextLine());

        Seats seats = new Seats(null, section, number, price, reservationStatus, eventId);

        int count = seatsController.update(id, seats);
        System.out.printf("There are created %d rows\n", count);
    }

    private void deleteFromSeats() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = seatsController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllSeats() {
        System.out.println("\nTable: SEATS");
        List<Seats> seatses = seatsController.findAll();
        for (Seats seats : seatses) {
            System.out.println(seats);
        }
    }

    private void findSeatsById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Seats> trip = seatsController.findById(id);
        System.out.println(trip.orElse(nullSeats));
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
