package gra.item;

import gra.czlowiek.Czlowiek;

public abstract class AbstractItem
{

    public void dodajHp(Czlowiek aczlowiek)
    {
        aczlowiek.setHp(100);
    }

    public void dodajOdpornosc(Czlowiek aczlowiek)
    {
        aczlowiek.setOdpornosc(100);
    }

}
