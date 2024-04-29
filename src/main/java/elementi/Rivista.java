package elementi;



import java.io.Serializable;

public class Rivista extends ElementoCatalogo implements Serializable {
    public enum Periodicita {SETTIMANALE, MENSILE, SEMESTRALE}

    private Periodicita periodicita;

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo='" + getTitolo() + '\'' +
                ", periodicita=" + periodicita +
                '}';
    }
}
