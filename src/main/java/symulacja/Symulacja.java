package symulacja;

import java.util.Scanner;

public class Symulacja
{

    private int LiczbaLudzi;
    private int LiczbaZaraz;
    private int rozmiarMapy;
    private Mapa Plansza;

    private void getRozmiar()
    {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Podaj rozmiar mapy wiekszy od 0: ");
            rozmiarMapy = scan.nextInt();
            if(rozmiarMapy<=0) System.out.println("Zly rozmiar!");
        }
        while(rozmiarMapy<=0);
    }

    private void getLiczbaLudzi()
    {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Podaj liczbe ludzi mniejsza niz " + (rozmiarMapy*rozmiarMapy) + ": ");
            LiczbaLudzi = scan.nextInt();
            if(LiczbaLudzi>(rozmiarMapy*rozmiarMapy)) System.out.println("Zla ilosc ludzi!");
        }
        while(LiczbaLudzi>(rozmiarMapy*rozmiarMapy));
    }

    private void getLiczbaZaraz()
    {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Podaj liczbe zaraz mniejsza/rowna " + ((rozmiarMapy*rozmiarMapy)-LiczbaLudzi) + ": ");
            LiczbaZaraz = scan.nextInt();
            if(LiczbaZaraz>((rozmiarMapy*rozmiarMapy)-LiczbaLudzi)) System.out.println("Zla ilosc zaraz!");
        }
        while(LiczbaZaraz>((rozmiarMapy*rozmiarMapy)-LiczbaLudzi));
    }

    private void makePlansza()
    {
        Plansza = new Mapa(rozmiarMapy, LiczbaLudzi, LiczbaZaraz);
    }

    private void wlaczGre()
    {
        getRozmiar();
        getLiczbaLudzi();
        getLiczbaZaraz();
        makePlansza();
        Plansza.ustawNaPlanszy();
        int i = 1;
        do {
            Plansza.move();
            System.out.println("Runda: " + i);
            System.out.println("Liczba ludzi: " + Plansza.ileLudzi());
            System.out.println("Liczba zaraz: " + Plansza.ileZaraz());
            System.out.println("-------------------------");
            i++;
        }
        while(Plansza.ileLudzi()!=0 && Plansza.ileZaraz()!=0);
    }

    public static void main(String[] args) {
        Symulacja gra = new Symulacja();
        gra.wlaczGre();
    }
}
