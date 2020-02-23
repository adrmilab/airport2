package airportPackage;

public class Samolot {
    public int getId() {
        return id;
    }

    private int id;
    private int ladownosc;
    private int liczbaMiejsc;

    Samolot(int id, int ladownosc,int liczbaMiejsc){
        this.id = id;
        this.ladownosc=ladownosc;
        this.liczbaMiejsc=liczbaMiejsc;
    }
    Samolot(int ladownosc,int liczbaMiejsc){

        this.ladownosc=ladownosc;
        this.liczbaMiejsc=liczbaMiejsc;
    }
    public int getLadownosc() {
        return ladownosc;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }
}
