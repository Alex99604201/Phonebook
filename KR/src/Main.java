//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Map<String, Set<String>> phoneBook = new HashMap<>();
    private static final Scanner scanner;

    public Main() {
    }

    public static void main(String[] args) {
        boolean running = true;

        while(running) {
            hr();
            System.out.println("Телефонная книга:\n");
            System.out.println("1. Просмотреть записи");
            System.out.println("2. Добавить запись");
            System.out.println("3. Удалить запись");
            System.out.println("4. Редактировать запись");
            System.out.println("5. Выйти");
            System.out.print("\nВыберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            clearConsole();
            switch (choice) {
                case 1:
                    displayItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    editItem();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("\nНекорректный выбор! Попробуйте снова.\n");
            }
        }

        System.out.println("\nРабота приложения завершена.\n");
    }

    private static void displayItems() {
        if (phoneBook.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.\n");
        } else {
            List<Map.Entry<String, Set<String>>> sortedEntries = new ArrayList(phoneBook.entrySet());
            sortedEntries.sort((entry1, entry2) -> {
                return Integer.compare(((Set)entry2.getValue()).size(), ((Set)entry1.getValue()).size());
            });
            hr();
            System.out.println("Записи в телефонной книге (отсортированы по убыванию числа телефонов):\n");
            Iterator var1 = sortedEntries.iterator();

            while(var1.hasNext()) {
                Map.Entry<String, Set<String>> entry = (Map.Entry)var1.next();
                PrintStream var10000 = System.out;
                String var10001 = (String)entry.getKey();
                var10000.println(var10001 + ": " + String.valueOf(entry.getValue()));
            }
        }

        waitEnter();
    }

    private static void addItem() {
        hr();
        System.out.print("ДОБАВЛЕНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();
        ((Set)phoneBook.computeIfAbsent(name, (k) -> {
            return new HashSet();
        })).add(phoneNumber);
        System.out.println("\nЗапись добавлена: " + name + ": " + phoneNumber);
        waitEnter();
    }

    private static void deleteItem() {
        hr();
        System.out.print("УДАЛЕНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя для удаления: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            Set<String> phoneNumbers = (Set)phoneBook.remove(name);
            System.out.println("Запись удалена: " + name + ": " + String.valueOf(phoneNumbers));
        } else {
            System.out.println("Запись с таким именем не найдена.");
        }

        waitEnter();
    }

    private static void editItem() {
        hr();
        System.out.print("РЕДАКТИРОВАНИЕ ЗАПИСИ\n\n");
        System.out.print("Введите имя для редактирования: ");
        String name = scanner.nextLine();
        if (phoneBook.containsKey(name)) {
            System.out.print("Введите новый номер телефона: ");
            String newPhoneNumber = scanner.nextLine();
            ((Set)phoneBook.get(name)).add(newPhoneNumber);
            System.out.println("Запись отредактирована: " + name + ": " + String.valueOf(phoneBook.get(name)));
        } else {
            System.out.println("Запись с таким именем не найдена.");
        }

        waitEnter();
    }

    private static void waitEnter() {
        System.out.print("\nНажмите ENTER для продолжения...");
        scanner.nextLine();
        clearConsole();
    }

    private static void hr() {
        System.out.println("\n--------------------------------");
    }

    public static void clearConsole() {
        for(int i = 0; i < 20; ++i) {
            System.out.println();
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}
