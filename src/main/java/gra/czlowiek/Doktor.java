package gra.czlowiek;

import gra.item.AbstractItem;
import gra.item.Syrop;
import gra.item.Szczepionka;
import gra.item.Tabletka;

import java.util.Random;

public class Doktor extends Czlowiek
{
    private Random generator = new Random();

    public Doktor()
    {
        Make();
    }


    public void ulecz(Czlowiek aczlowiek)

    {
        aczlowiek.setHp(100);
    }

    public void wylecz(Czlowiek aczlowiek)
    {
        int a = generator.nextInt(100);
        if(a<25)
        {
            aczlowiek.setChoroba(null);
        }
    }

    public AbstractItem makeItem()
    {

       if(generator.nextInt(100)<30)
       {
           int a;
           a = generator.nextInt(3);
           if (a == 0) return new Syrop();
           else if (a == 1) return new Szczepionka();
           else return new Tabletka();
       }
       else return null;
    }

    public Czlowiek convertDoktor(Czlowiek aczlowiek)
    {
        int a = generator.nextInt(100);
        if(a<30)
        {
            Doktor nowy = new Doktor();
            nowy.setHp(aczlowiek.getHp());
            nowy.setOdpornosc(aczlowiek.getOdpornosc());
            nowy.setWiek(aczlowiek.getWiek());
            nowy.setChoroba(aczlowiek.getChoroba());
            return nowy;
        }
        else return aczlowiek;
    }

    public Czlowiek porozmawiajDoktor(Czlowiek aczlowiek)
    {
        ulecz(aczlowiek);
        wylecz(aczlowiek);
        return convertDoktor(aczlowiek);
    }
}
