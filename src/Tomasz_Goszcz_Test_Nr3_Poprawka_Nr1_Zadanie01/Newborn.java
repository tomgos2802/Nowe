package Tomasz_Goszcz_Test_Nr3_Poprawka_Nr1_Zadanie01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Newborn {
    private int id;
    private char sex;
    private String name;
    private LocalDate date;
    private int weight;
    private int height;
    private Mother mother; // asocjacja

    public Newborn(int id, char sex, String name, LocalDate date, int weight, int height, Mother mother) {
        this.id = id;
        this.sex = sex;
        this.name = name;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.mother = mother;
        mother.addNewborn(this);
    }

    //  a) Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
    public static List<Newborn> findMaxBySex(List<Newborn> newbornList, char sex) {
        if (newbornList == null || newbornList.isEmpty()) {
            throw new IllegalArgumentException("Lista nie może być pusta.");
        }

        int maxHeight = 0;
        for (Newborn n : newbornList) {
            if (n.getSex() == sex && n.getHeight() > maxHeight) {
                maxHeight = n.getHeight();
            }
        }

        List<Newborn> result = new ArrayList<>();
        for (Newborn n : newbornList) {
            if (n.getSex() == sex && n.getHeight() == maxHeight) {
                result.add(n);
            }
        }
        return result;
    }


    //  b) W którym dniu tygodnia urodziło się najwięcej dzieci? Podaj dzien tygodnia i liczbe dzieci.
    public static List<DayOfWeek> findMostPopularDays(List<Newborn> newbornList) {
        if (newbornList == null || newbornList.isEmpty()) {
            throw new IllegalArgumentException("Lista nie może być pusta.");
        }

        int[] counts = new int[7]; /*7dni tygodnia: 0 = poniedziałek, 6 = niedziela*/
        // Zliczanie urodzeń dla każdego dnia tygodnia
        for (Newborn x : newbornList) {
            int index = x.getDate().getDayOfWeek().getValue() - 1; /*przekształcenie dnia tygodnia (1–7) na indeks tablicy (0–6)*/
            counts[index]++;
        }
        // Szukanie maksymalnej liczby urodzeń
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }
        // Zbieramy wszystkie dni tygodnia z maksymalną liczbą urodzeń
        List<DayOfWeek> result = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == max) {
                result.add(DayOfWeek.of(i + 1));
            }
        }
        return result;
    }

    //  d) Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.
    public static List<Newborn> daughtersWithMothersName(List<Newborn> newbornList) {
        if (newbornList == null || newbornList.isEmpty()) {
            throw new IllegalArgumentException("Lista nie może być pusta.");
        }

        List<Newborn> result = new ArrayList<>();
        for (Newborn x : newbornList) {
            if (x.getSex() == 'c') {
                if (x.getName().equals(x.getMother().getName())) {
                    result.add(x);
                }
            }
        }
        return result;
    }

    public static List<Newborn> loadNewborns(String filePath2, List<Mother> motherList) {

        List<Newborn> newbornList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath2))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                /*identyfikator, płeć (c – córka, s – syn), imię, data urodzenia, waga [g], wzrost [cm] oraz identyfikator matki.*/
                int id = Integer.parseInt(parts[0]);
                char sex = parts[1].charAt(0);
                String name = parts[2];
                LocalDate date = LocalDate.parse(parts[3]); // yyyy-MM-dd
                int weight = Integer.parseInt(parts[4]);
                int height = Integer.parseInt(parts[5]);
                int motherId = Integer.parseInt(parts[6]);

                // znajdź matkę po id w liście
                Mother mother = null;

                for (Mother m : motherList) {
                    if (m.getMotherId() == motherId) {
                        mother = m;
                        break;
                    }
                }

                if (mother != null) {
                    newbornList.add(new Newborn(id, sex, name, date, weight, height, mother));
                } else {
                    System.out.println("Nie znaleziono matki o ID: " + motherId);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("plik nie istnieje albo ścieżka jest błędna");
        } catch (IOException e) {
            System.out.println("błąd odczytu/zapisu");
        }

        return newbornList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "{" + name + " urodzony(a): " + date + ", " + height + "cm}";
    }
}
