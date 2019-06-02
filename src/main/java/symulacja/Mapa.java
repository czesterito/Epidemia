package symulacja;

import gra.czlowiek.AntySzcz;
import gra.czlowiek.Czlowiek;
import gra.czlowiek.Doktor;
import gra.item.AbstractItem;
import gra.item.Syrop;
import gra.item.Szczepionka;
import gra.item.Tabletka;
import gra.zaraza.AbstractZaraza;
import gra.zaraza.Bakteria;
import gra.zaraza.Pasozyt;
import gra.zaraza.Wirus;

import java.util.Random;

public class Mapa
{
    private int rozmiar;
    private int ludzie;
    private int zarazki;
    private int X;
    private int Y;
    private Object[][] planszaGry;
    private Random generator = new Random();

    public Mapa(int rozmiarMapy, int LiczbaLudzi, int LiczbaZaraz)
    {
        this.rozmiar = rozmiarMapy;
        this.ludzie = LiczbaLudzi;
        this.zarazki = LiczbaZaraz;
    }

    public void makePlanszaGry()
    {
        planszaGry = new Object[rozmiar][rozmiar];
    }

    private AbstractZaraza losowaZaraza()
    {
     int a;
     a = generator.nextInt(3);
             if(a==0) return new Bakteria();
             else if(a==1) return new Pasozyt();
             else return new Wirus();
    }

    public void ustawCzlowiek()
    {
        Czlowiek[] czlowiek = new Czlowiek[ludzie];
        for(int i=0; i<ludzie;i++)
        {
            czlowiek[i] = new Czlowiek();
        }

        if (ludzie>1) czlowiek[ludzie-2] = new Doktor();
        if (ludzie>2) czlowiek[ludzie-1] = new AntySzcz();

        for(int i=0;i<ludzie;i++)
        {
            X = generator.nextInt(rozmiar);
            Y = generator.nextInt(rozmiar);

            if(planszaGry[X][Y]==null) planszaGry[X][Y] = czlowiek[i];
            else i--;
        }
    }

    public void ustawZaraza()
    {
        AbstractZaraza[] zaraza = new AbstractZaraza[zarazki];
        for(int i=0; i<zarazki;i++)
        {
            zaraza[i] = losowaZaraza();
        }

        for(int i=0;i<zarazki;i++)
        {
            X = generator.nextInt(rozmiar);
            Y = generator.nextInt(rozmiar);

            if(planszaGry[X][Y]==null) planszaGry[X][Y] = zaraza[i];
            else i--;
        }
    }

    public void move()
    {
        for(int i=0;i<rozmiar;i++)
        {
            for(int j=0;j<rozmiar;j++)
            {
                X = generator.nextInt(rozmiar);
                Y = generator.nextInt(rozmiar);
                    if(planszaGry[i][j] instanceof Czlowiek)
                    {
                        Czlowiek b = (Czlowiek) planszaGry[i][j];
                        planszaGry[i][j] = b.tura(b);
                        if(b.getHp()<=0) planszaGry[i][j] = b.sprawdzZaraze(b);
                        else
                        {
                            // Doktor i puste pole
                            if(planszaGry[i][j] instanceof Doktor && planszaGry [X][Y]==null)
                            {
                                Doktor c = (Doktor) planszaGry [i][j];
                                planszaGry[X][Y] = planszaGry [i][j];
                                planszaGry[i][j] = c.makeItem();
                            }
                            // Czlowiek i puste pole
                            else if(planszaGry[i][j] instanceof Czlowiek && planszaGry[X][Y]==null)
                            {
                                planszaGry[X][Y] = planszaGry[i][j];
                                planszaGry[i][j] = null;
                            }
                            // Czlowiek i doktor
                            else if(planszaGry[i][j] instanceof Czlowiek && planszaGry[X][Y] instanceof Doktor)
                            {
                                Doktor c = (Doktor) planszaGry [X][Y];
                                planszaGry[i][j] = c.porozmawiajDoktor(b);
                            }
                            // Czlowiek i antyszcz
                            else if(planszaGry[i][j] instanceof Czlowiek && planszaGry[X][Y] instanceof AntySzcz)
                            {
                                AntySzcz c = (AntySzcz) planszaGry[X][Y];
                                planszaGry[i][j] = c.porozmawiajAntyszcz(b);;
                            }
                            // Czlowiek i item
                            else if(planszaGry[i][j] instanceof Czlowiek && planszaGry[X][Y] instanceof AbstractItem)
                            {
                                if(planszaGry[X][Y] instanceof Syrop)
                                {
                                    Syrop c = (Syrop) planszaGry[X][Y];
                                    planszaGry[i][j] = c.uzyjSyrop(b);
                                    planszaGry[X][Y] = planszaGry[i][j];
                                    planszaGry[i][j] = null;
                                }
                                else if(planszaGry[X][Y] instanceof Szczepionka)
                                {
                                    Szczepionka c = (Szczepionka) planszaGry[X][Y];
                                    planszaGry[i][j] = c.uzyjSzczepionka(b);
                                    planszaGry[X][Y] = planszaGry[i][j];
                                    planszaGry[i][j] = null;
                                }
                                else
                                {
                                    Tabletka c = (Tabletka) planszaGry[X][Y];
                                    planszaGry[i][j] = c.uzyjTabletka(b);
                                    planszaGry[X][Y] = planszaGry[i][j];
                                    planszaGry[i][j] = null;
                                }
                            }
                            // Czlowiek i zaraza
                            else if(planszaGry[i][j] instanceof Czlowiek && planszaGry[X][Y] instanceof AbstractZaraza)
                            {
                                AbstractZaraza c = (AbstractZaraza) planszaGry[X][Y];
                                if(b.getChoroba()==null)
                                {
                                    planszaGry[i][j] = b.zarazenie(b,c);
                                    if(b.getChoroba()!=null)
                                    {
                                        planszaGry[X][Y] = planszaGry [i][j];
                                        planszaGry[i][j] = null;
                                    }
                                }
                            }
                        }
                    }
                    else if (planszaGry[i][j] instanceof AbstractZaraza)
                    {
                    // Zaraza i puste pole
                    if(planszaGry[i][j] instanceof AbstractZaraza && planszaGry[X][Y]==null)
                    {
                        planszaGry[X][Y] = planszaGry[i][j];
                        planszaGry[i][j] = null;
                    }
                    // Zaraza i czlowiek
                    else if(planszaGry[i][j] instanceof AbstractZaraza && planszaGry[X][Y] instanceof Czlowiek)
                    {
                        Czlowiek b = (Czlowiek) planszaGry[X][Y];
                        AbstractZaraza c = (AbstractZaraza) planszaGry[i][j];
                        if(b.getChoroba()==null)
                        {
                            planszaGry[X][Y] = b.zarazenie(b,c);
                            if(b.getChoroba()!=null)
                            {
                                planszaGry[i][j] = null;
                            }
                        }
                    }
                    }
            }
        }
    }

    public int ileLudzi(){
        int iloscLudzi = 0;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (planszaGry[i][j] instanceof Czlowiek) {
                    iloscLudzi++;
                }
            }
        }
        return iloscLudzi;
    }

    public int ileZaraz(){
        int iloscZaraz = 0;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (planszaGry[i][j] instanceof AbstractZaraza) iloscZaraz++;
                else if(planszaGry[i][j] instanceof Czlowiek)
                {
                    Czlowiek a = (Czlowiek) planszaGry[i][j];
                    if(a.getChoroba() instanceof AbstractZaraza) iloscZaraz++;
                }
            }
        }
        return iloscZaraz;
    }

    public int ileDoktorw(){
        int iloscDoktorow = 0;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (planszaGry[i][j] instanceof Doktor) {
                    iloscDoktorow++;
                }
            }
        }
        return iloscDoktorow;
    }

    public int ileAntyszcz(){
        int iloscAntyszcz = 0;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (planszaGry[i][j] instanceof AntySzcz) {
                    iloscAntyszcz++;
                }
            }
        }
        return iloscAntyszcz;
    }

    public int ileItemow(){
        int iloscItemow = 0;
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                if (planszaGry[i][j] instanceof AbstractItem) {
                    iloscItemow++;
                }
            }
        }
        return iloscItemow;
    }

}


