package gra.item;

import gra.czlowiek.Czlowiek;
import gra.zaraza.Pasozyt;

public class Syrop extends AbstractItem
{
    public Syrop() {}

    public void wyleczPasozyt(Czlowiek aczlowiek)
    {
        if(aczlowiek.getChoroba() instanceof Pasozyt) aczlowiek.setChoroba(null);
    }

    public Czlowiek uzyjSyrop(Czlowiek aczlowiek)
    {
        dodajHp(aczlowiek);
        dodajOdpornosc(aczlowiek);
        wyleczPasozyt(aczlowiek);
        return aczlowiek;
    }
}
