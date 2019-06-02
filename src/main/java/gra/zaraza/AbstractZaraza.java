package gra.zaraza;

import gra.czlowiek.Czlowiek;

public abstract class AbstractZaraza
{
    private int Dmg;

    public void setDmg(int dmg) { this.Dmg = dmg; }

    public int getDmg() {return Dmg;}

    public void Attack(Czlowiek aczlowiek)
    {
    aczlowiek.setHp((aczlowiek.getHp() - getDmg()));
    }

    public Czlowiek attackOdpornosc(Czlowiek aczlowiek)
    {
        aczlowiek.setOdpornosc((aczlowiek.getOdpornosc()-getDmg()));
        return aczlowiek;
    }

}
