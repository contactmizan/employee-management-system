import java.util.ArrayList;
import java.util.Scanner;

// Abstraction
abstract class Employee {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Encapsulation: Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Abstract method
    public abstract void displayDetails();
}

// Inheritance
class FullTimeEmployee extends Employee {
    private double salary;

    public FullTimeEmployee(int id, String name, String department, double salary) {
        super(id, name, department);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Full-Time Employee]");
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Department: " + getDepartment() + ", Salary: " + salary);
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, String department, double hourlyRate) {
        super(id, name, department);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Part-Time Employee]");
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Department: " + getDepartment() + ", Hourly Rate: " + hourlyRate);
    }
}

// User class for login and register
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// Polymorphism
public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;
    private ArrayList<User> users;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        users.add(new User(username, password));
        System.out.println("User registered successfully!");
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully!");
    }

    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("\n--- Employee List ---");
        for (Employee employee : employees) {
            employee.displayDetails();
        }
    }

    // Update Employee Details
    public void updateEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new department: ");
                String department = scanner.nextLine();
                employee.setName(name);
                employee.setDepartment(department);
                System.out.println("Employee details updated successfully!");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManagementSystem system = new EmployeeManagementSystem();

        System.out.println("--- Welcome to Employee Management System ---");
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                system.registerUser(username, password);
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (system.loginUser(username, password)) {
                    break;
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. List Employees");
            System.out.println("4. Update Employee Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int ftId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String ftName = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String ftDept = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();

                    FullTimeEmployee ftEmployee = new FullTimeEmployee(ftId, ftName, ftDept, salary);
                    system.addEmployee(ftEmployee);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int ptId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String ptName = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String ptDept = scanner.nextLine();
                    System.out.print("Enter Hourly Rate: ");
                    double hourlyRate = scanner.nextDouble();

                    PartTimeEmployee ptEmployee = new PartTimeEmployee(ptId, ptName, ptDept, hourlyRate);
                    system.addEmployee(ptEmployee);
                    break;

                case 3:
                    system.listEmployees();
                    break;

                case 4:
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    system.updateEmployee(updateId);
                    break;

                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
