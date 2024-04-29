package org.example;



import gestione.GestoreCatalogo;
import elementi.ElementoCatalogo;
import elementi.Libro;
import elementi.Rivista;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creazione di un gestore del catalogo
        GestoreCatalogo gestoreCatalogo = new GestoreCatalogo();

        // Aggiunta di alcuni elementi al catalogo
        gestoreCatalogo.aggiungiElemento(new Libro("978-0544003415", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-8804685384", "1984", 1949, 295, "George Orwell", "Distopia"));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-0977616800", "National Geographic", 1888, 160, Rivista.Periodicita.MENSILE));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-0744013066", "Time", 1923, 112, Rivista.Periodicita.SETTIMANALE));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-0123456789", "Science", 1880, 90, Rivista.Periodicita.MENSILE));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-0987654321", "The Economist", 1843, 96, Rivista.Periodicita.SETTIMANALE));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-1234567890", "Nature", 1869, 88, Rivista.Periodicita.MENSILE));
        gestoreCatalogo.aggiungiElemento(new Rivista("978-1357924680", "Sports Illustrated", 1954, 72, Rivista.Periodicita.SETTIMANALE));
        gestoreCatalogo.aggiungiElemento(new Libro("978-1451673319", "To Kill a Mockingbird", 1960, 281, "Harper Lee", "Romanzo"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-0140286800", "The Catcher in the Rye", 1949, 277, "J.D. Salinger", "Romanzo"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-0061120084", "Pride and Prejudice", 1813, 279, "Jane Austen", "Romanzo"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-0743273565", "The Great Gatsby", 1925, 180, "F. Scott Fitzgerald", "Romanzo"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-1416524304", "The Da Vinci Code", 2003, 454, "Dan Brown", "Thriller"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-0142424179", "The Hunger Games", 2008, 374, "Suzanne Collins", "Fantascienza"));
        gestoreCatalogo.aggiungiElemento(new Libro("978-0544003415", "Il Signore degli Anelli e le Due torri ", 1958, 1348, "J.R.R. Tolkien", "Fantasy"));



        // Ricerca per ISBN
        String isbnDaCercare = "978-0544003415";
        ElementoCatalogo elementoPerIsbn = gestoreCatalogo.ricercaPerISBN(isbnDaCercare);
        if (elementoPerIsbn != null) {
            System.out.println("\nElemento trovato per ISBN " + isbnDaCercare + ":");
            if (elementoPerIsbn instanceof Libro) {
                Libro libro = (Libro) elementoPerIsbn;
                System.out.println("Libro: " + libro.getTitolo() + ", Autore: " + libro.getAutore());
            } else if (elementoPerIsbn instanceof Rivista) {
                Rivista rivista = (Rivista) elementoPerIsbn;
                System.out.println("Rivista: " + rivista.getTitolo() + ", Periodicita: " + rivista.getPeriodicita());
            }
        } else {
            System.out.println("\nNessun elemento trovato per ISBN " + isbnDaCercare);
        }

        // Rimozione di un elemento dato un codice ISBN
        String isbnDaRimuovere = "978-8804685384";
        boolean elementoRimosso = gestoreCatalogo.rimuoviElementoPerISBN(isbnDaRimuovere);
        if (elementoRimosso) {
            System.out.println("\nElemento rimosso con successo per ISBN " + isbnDaRimuovere);
        } else {
            System.out.println("\nNessun elemento trovato per ISBN " + isbnDaRimuovere + ", nessuna rimozione effettuata");
        }

        // Ricerca per anno di pubblicazione
        List<ElementoCatalogo> elementiPerAnno = gestoreCatalogo.ricercaPerAnnoPubblicazione(1949);
        System.out.println("\nElementi trovati per anno di pubblicazione (1949):");
        for (ElementoCatalogo elemento : elementiPerAnno) {
            if (elemento instanceof Libro) {
                Libro libro = (Libro) elemento;
                System.out.println("Libro: " + libro.getTitolo() + ", Autore: " + libro.getAutore());
            } else if (elemento instanceof Rivista) {
                Rivista rivista = (Rivista) elemento;
                System.out.println("Rivista: " + rivista.getTitolo() + ", Periodicita: " + rivista.getPeriodicita());
            }
        }

        // Ricerca per autore
        List<ElementoCatalogo> libriDiTolkien = gestoreCatalogo.ricercaPerAutore("J.R.R. Tolkien");
        System.out.println("\nLibri di J.R.R. Tolkien trovati:");
        for (ElementoCatalogo elemento : libriDiTolkien) {
            Libro libro = (Libro) elemento;
            System.out.println("Libro: " + libro.getTitolo() + ", Autore: " + libro.getAutore());
        }


        // Salvataggio su disco del catalogo
        try {
            gestoreCatalogo.salvataggioSuFileTXT("catalogo.txt");
            System.out.println("\nCatalogo salvato su disco.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio su disco: " + e.getMessage());
        }

        // Caricamento da disco del catalogo
        try {
            List<ElementoCatalogo> catalogoCaricato = gestoreCatalogo.caricamentoDaFileTXT("catalogo.txt");
            System.out.println("\nCatalogo caricato da disco:");
            for (ElementoCatalogo elemento : catalogoCaricato) {
                if (elemento instanceof Libro) {
                    Libro libro = (Libro) elemento;
                    System.out.println("Libro: " + libro.getTitolo() + ", Autore: " + libro.getAutore());
                } else if (elemento instanceof Rivista) {
                    Rivista rivista = (Rivista) elemento;
                    System.out.println("Rivista: " + rivista.getTitolo() + ", Periodicita: " + rivista.getPeriodicita());
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento da disco: " + e.getMessage());
        }
    }
}
