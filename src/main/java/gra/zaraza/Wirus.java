package gra.zaraza;

public class Wirus extends AbstractZaraza
{
    private Wirus Wirus;

    public Wirus()
    {
        setDmg(10);
    }

    public Wirus rozniesWirus(){return new Wirus();}

}
