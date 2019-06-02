package gra.item;

import gra.czlowiek.Czlowiek;

public class Tabletka extends AbstractItem
{
    public Tabletka() {}

    public void wyleczBakteria(Czlowiek aczlowiek)
    {
        if(aczlowiek.getChoroba() instanceof Tabletka) aczlowiek.setChoroba(null);
    }

    public Czlowiek uzyjTabletka(Czlowiek aczlowiek)
    {
        dodajHp(aczlowiek);
        dodajOdpornosc(aczlowiek);
        wyleczBakteria(aczlowiek);
        return aczlowiek;
    }
}
