package gra.item;

import gra.czlowiek.Czlowiek;
import gra.zaraza.Wirus;

public class Szczepionka extends AbstractItem
{
    public Szczepionka() {}

    public void wyleczWirus(Czlowiek aczlowiek)
    {
        if(aczlowiek.getChoroba() instanceof Wirus) aczlowiek.setChoroba(null);
    }

    public Czlowiek uzyjSzczepionka(Czlowiek aczlowiek)
    {
        dodajHp(aczlowiek);
        dodajOdpornosc(aczlowiek);
        wyleczWirus(aczlowiek);
        return aczlowiek;
    }
}

