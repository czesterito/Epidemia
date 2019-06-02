package gra.zaraza;

public class Pasozyt extends AbstractZaraza
{

    public Pasozyt()
    {
        setDmg(20);
    }

    public Pasozyt rozniesPasozyt(){return new Pasozyt();}

}
