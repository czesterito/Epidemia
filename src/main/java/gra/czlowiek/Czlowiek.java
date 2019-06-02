package gra.czlowiek;

import gra.zaraza.AbstractZaraza;
import gra.zaraza.Bakteria;
import gra.zaraza.Pasozyt;
import gra.zaraza.Wirus;

import java.util.Random;

public class Czlowiek
{

    private int Hp;
    private int Odpornosc;
    private int Wiek;
    private Object[] Choroby;
    private Random generator = new Random();

    public Czlowiek()
    {
        Make();
    }

    public void Make()
    {
        Choroby = new Object[1];
        Choroby[0] = null;
        this.Wiek = generator.nextInt(3);

        if(Wiek==0) this.Hp = 80;
        else if(Wiek==1) this.Hp = 100;
        else this.Hp = 60;

        if(Wiek==0) this.Odpornosc = 60;
        else if(Wiek==1) this.Odpornosc = 100;
        else this.Odpornosc = 80;
    }

    public void setHp(int hp)
    {
        this.Hp = hp;
    }

    public void setOdpornosc(int odpornosc)
    {
        this.Odpornosc = odpornosc;
    }

    public void setWiek (int wiek) {this.Wiek = wiek;}

    public void setChoroba(Object choroba)
    {
        this.Choroby[0] = choroba;
    }

    public int getHp()
    {
        return this.Hp;
    }

    public int getOdpornosc()
    {
        return this.Odpornosc;
    }

    public int getWiek()
    {
        return this.Wiek;
    }

    public Object getChoroba()
    {
        return this.Choroby[0];
    }

    public Czlowiek zarazenie(Czlowiek aczlowiek, AbstractZaraza Zarazek)
    {
        int a = generator.nextInt(100);
        if(a>=aczlowiek.getOdpornosc())
        {
            aczlowiek.Choroby[0] = Zarazek;
            return aczlowiek;
        }
        else return Zarazek.attackOdpornosc(aczlowiek);
    }

    public Czlowiek tura(Czlowiek aczlowiek)
    {
        if(aczlowiek.getChoroba() == null) return aczlowiek;
        else if(aczlowiek.getChoroba() instanceof Bakteria)
        {
            Bakteria bak = new Bakteria();
            bak.Attack(aczlowiek);
            return aczlowiek;
        }
        else if(aczlowiek.getChoroba() instanceof Wirus)
        {
            Wirus wir = new Wirus();
            wir.Attack(aczlowiek);
            return aczlowiek;
        }
        else
        {
            Pasozyt pas = new Pasozyt();
            pas.Attack(aczlowiek);
            return aczlowiek;
        }
    }

    public AbstractZaraza sprawdzZaraze(Czlowiek aczlowiek)
    {
        if(aczlowiek.getChoroba() instanceof Bakteria) return new Bakteria();
        else if(aczlowiek.getChoroba() instanceof Wirus) return new Wirus();
        else return new Pasozyt();
    }

}
