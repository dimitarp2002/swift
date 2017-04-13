
import java.util.Scanner;

public class Task4_EmployeeSalarySort {

    public static void main(String[] args) {

        String name = "";
        String position = "";
        String department = "";
        double salary = 0.0;

        int age = 0;
        String email = "";

        int[] idx = {0, 0, 0};
        double[] max_salary = {0.0, 0.0, 0.0};

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit:  ");
        int number = sc.nextInt();
        sc.nextLine();
        Employee[] employees = new Employee[number];
        for (int i = 0; i < number; i++) {
            String[] split = sc.nextLine().split(",");
            for (int j = 0; j < split.length; j++) {
                email = "";
                age = 0;
                if (j == 0) {
                    name = split[j];
                } else if (j == 1) {
                    salary = Double.parseDouble(split[j].trim());
                } else if (j == 2) {
                    position = split[2];
                } else if (j == 3) {
                    department = split[j];
                } else if (j == 4) {

                    if (split[j].contains("@")) {
                        email = split[j];
                    } else {
                        age = Integer.parseInt(split[j].trim());
                    }

                } else if (split[j].contains("@")) {
                    email = split[j];
                } else {
                    age = Integer.parseInt(split[j].trim());
                }
            }
            employees[i] = new Employee(name, position, department, salary, age, email);

            //sorting the salaries and keeping the indexes of the highest 3
            for (int k = 0; k < 3; k++) {
                if (employees[i].getSalary() > max_salary[k]) {
                    int n = 2 - k;
                    while (n > k) {
                        max_salary[n] = max_salary[n - 1];
                        idx[n] = idx[n - 1];
                        n--;
                    }

                    max_salary[k] = employees[i].getSalary();
                    idx[k] = i;
                    break;
                }

            }
        }

        System.out.println();
        for (int i = 0; i < 3; i++) {
            employees[idx[i]].printInfo();
        }

    }
}
