package Tomasz_Goszcz_Test_Nr3_Poprawka_Nr1_Zadanie01;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Mother> motherList = Mother.loadMothers("src/Tomasz_Goszcz_Test_Nr3_Poprawka_Nr1_Zadanie01/mamy.txt");
        List<Newborn> newbornList = Newborn.loadNewborns("src/Tomasz_Goszcz_Test_Nr3_Poprawka_Nr1_Zadanie01/noworodki.txt", motherList);


//        a) Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
        System.out.println("\nNajwyższy chłopczyk: " + Newborn.findMaxBySex(newbornList, 's'));
        System.out.println("Najwyższa dziewczynka: " + Newborn.findMaxBySex(newbornList, 'c'));


//        b) W którym dniu tygodnia urodziło się najwięcej dzieci? Podaj dzien tygodnia i liczbe dzieci.
        System.out.println("\nNajwięcej dzieci urodzonych jest: " + Newborn.findMostPopularDays(newbornList));

//        c) Podaj imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
        System.out.println("\nKobiety poniżej 25lat z dziećmi powyżej 4000g: " + Mother.findYoungMothers(motherList, 25, 4000));

//        d) Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.
        System.out.println("\nTo samo imię jak mama: " + Newborn.daughtersWithMothersName(newbornList));

//        e) Znajdz matki które urodziły bliźnięta.
        System.out.println("\nMamy, które urodziły bliźnięta: " + Mother.findMotherWithTwins(newbornList));

        System.out.println("DUPA");
        System.out.println("adas");


    }
}
