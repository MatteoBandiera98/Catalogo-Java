package gestione;

import elementi.ElementoCatalogo;
import elementi.Libro;
import elementi.Rivista;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GestoreCatalogo {
    private List<ElementoCatalogo> catalogo;

    public GestoreCatalogo() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getAnnoPubblicazione() == anno) {
                risultati.add(elemento);
            }
        }
        return risultati;
    }

    public List<ElementoCatalogo> ricercaPerAutore(String autore) {
        List<ElementoCatalogo> risultati = new ArrayList<>();
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento instanceof Libro) {
                Libro libro = (Libro) elemento;
                if (libro.getAutore().equalsIgnoreCase(autore)) {
                    risultati.add(elemento);
                }
            }
        }
        return risultati;
    }

    public ElementoCatalogo ricercaPerISBN(String isbn) {
        for (ElementoCatalogo elemento : catalogo) {
            if (elemento.getIsbn().equals(isbn)) {
                return elemento;
            }
        }
        return null;
    }

    public boolean rimuoviElementoPerISBN(String isbn) {
        ElementoCatalogo elementoDaRimuovere = ricercaPerISBN(isbn);
        if (elementoDaRimuovere != null) {
            return catalogo.remove(elementoDaRimuovere);
        }
        return false;
    }

    public void salvataggioSuFileTXT(String nomeFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            for (ElementoCatalogo elemento : catalogo) {
                if (elemento instanceof Libro) {
                    Libro libro = (Libro) elemento;
                    writer.println("Libro|" + libro.getIsbn() + "|" + libro.getTitolo() + "|" + libro.getAnnoPubblicazione() + "|" + libro.getNumeroPagine() + "|" + libro.getAutore() + "|" + libro.getGenere());
                } else if (elemento instanceof Rivista) {
                    Rivista rivista = (Rivista) elemento;
                    writer.println("Rivista|" + rivista.getIsbn() + "|" + rivista.getTitolo() + "|" + rivista.getAnnoPubblicazione() + "|" + rivista.getNumeroPagine() + "|" + rivista.getPeriodicita());
                }
            }
        }
    }

    public List<ElementoCatalogo> caricamentoDaFileTXT(String nomeFile) throws IOException {
        List<ElementoCatalogo> catalogoCaricato = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] parti = linea.split("\\|");
                if (parti.length > 0) {
                    if (parti[0].equals("Libro") && parti.length == 7) {
                        catalogoCaricato.add(new Libro(parti[1], parti[2], Integer.parseInt(parti[3]), Integer.parseInt(parti[4]), parti[5], parti[6]));
                    } else if (parti[0].equals("Rivista") && parti.length == 6) {
                        catalogoCaricato.add(new Rivista(parti[1], parti[2], Integer.parseInt(parti[3]), Integer.parseInt(parti[4]), Rivista.Periodicita.valueOf(parti[5])));
                    }
                }
            }
        }
        return catalogoCaricato;
    }
}

