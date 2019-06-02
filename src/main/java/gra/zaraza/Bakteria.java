package gra.zaraza;

public class Bakteria extends AbstractZaraza
{
    public Bakteria()
    {
        setDmg(15);
    }

    public Bakteria rozniesBakteria(){return new Bakteria();}
}
