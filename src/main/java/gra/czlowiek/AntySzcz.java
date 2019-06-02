package gra.czlowiek;

import java.util.Random;

public class AntySzcz extends Czlowiek
{
    private Random generator = new Random();

    public AntySzcz()
    {
        Make();
    }

    public void zmnniejszOdp(Czlowiek aczlowiek)
    {
        aczlowiek.setOdpornosc(0);
    }

    public Czlowiek convertAntySzcz(Czlowiek aczlowiek)
    {
        int a = generator.nextInt(100);
        if(a<10)
        {
            AntySzcz nowy = new AntySzcz();
            nowy.setHp(aczlowiek.getHp());
            nowy.setOdpornosc(aczlowiek.getOdpornosc());
            nowy.setWiek(aczlowiek.getWiek());
            nowy.setChoroba(aczlowiek.getChoroba());
            return nowy;
        }
        else return aczlowiek;
    }

    public Czlowiek porozmawiajAntyszcz(Czlowiek aczlowiek)
    {
        zmnniejszOdp(aczlowiek);
        return convertAntySzcz(aczlowiek);
    }

}
