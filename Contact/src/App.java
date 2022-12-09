import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.concurrent.CountDownLatch;

import model.Contact;

public class App {
    private static Scanner scan = new Scanner(System.in);
    private static BufferedReader reader;

    public static void main(String[] args) throws Exception {
        afficherMenu();
        while (true) {
            String choix = scan.nextLine();
            switch (choix) {
                case "1":
                    ajouterContact();
                    break;
                case "2":
                    listerContact();
                    break;
                case "3":
                    deleteContact();
                    break;
                case "4":
                    editContact();
                    break;
                case "5":
                    searchContact1();
                    break;
                case "q":
                    scan.close();
                    return;
                default:
                    System.out.println("Boulet!!!!");
                    break;
            }
            afficherMenu();
        }
    }

    private static void listerContact() {
        // Contact c = new Contact();
        try {
            ArrayList<Contact> liste = Contact.lister();

            for (Contact contact : liste) {
                System.out.println(contact.getPrenom() + " " + contact.getNom());
            }
        } catch (IOException e) {
            System.out.println("Erreur avec le fichier");
        }

    }

    public static void ajouterContact() {

        Contact c = new Contact();
        System.out.println("Saisir le nom:");
        c.setNom(scan.nextLine());
        System.out.println("Saisir le prénom:");
        c.setPrenom(scan.nextLine());

        do {
            try {
                System.out.println("Saisir le téléphone:");
                c.setNumero(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir le mail:");
                c.setMail(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        do {
            try {
                System.out.println("Saisir la date de naissance:");
                c.setDateNaissance(scan.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Error, try again!");
            }
        } while (true);

        try {
            c.enregistrer();
            System.out.println("Contact enregistré.");
        } catch (IOException e) {
            System.out.println("Erreur à l'enregistrement");
        }

    }

    public static void afficherMenu() {
        ArrayList<String> menus = new ArrayList<>();
        menus.add("-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Supprimer un contact");
        menus.add("4- Modifier un contact");
        menus.add("5- Afficher un contact");
        menus.add("q- Quitter");
        for (String s : menus) {
            System.out.println(s);
        }
    }

    private static void deleteContact() throws IOException {

        // Récupérez l'entrée de l'utilisateur pour savoir quel contact supprimer
        System.out.println("Entrez le prénom du contact que vous voulez supprimer :");
        String numeroASupprimer = scan.nextLine();

        // Charger la liste des contacts du fichier dans un ArrayList
        ArrayList<Contact> liste = Contact.lister();

        // Utilisez la méthode indexOf() pour rechercher le contact à supprimer
        int index = -1;
        for (int i = 0; i < liste.size(); i++) {
            Contact contact = liste.get(i);
            if (contact.getPrenom().equals(numeroASupprimer)) {
                index = i;
                break;
            }
        }
        // Si l'index a été trouvé, supprimer le contact de la liste et écrire la liste
        // modifiée dans le fichier contacts.csv
        if (index != -1) {
            liste.remove(index);
            Contact.ecrire(liste);
        }
    }

    private static void editContact() throws IOException {

        // Récupérez l'entrée de l'utilisateur pour savoir quel contact supprimer
        System.out.println("Entrez le prénom du contact que vous voulez modifier :");
        String prenomEdit = scan.nextLine();

        ArrayList<Contact> liste = Contact.lister();

        int index = -1;
        for (int i = 0; i < liste.size(); i++) {
            Contact contact = liste.get(i);
            if (contact.getPrenom().equals(prenomEdit)) {
                index = i;
                System.out.println("Contact en cours de modification");
                break;
            }
        }
        // Si l'index a été trouvé, supprimer le contact de la liste et écrire la liste
        // modifiée dans le fichier contacts.csv
        if (index != -1) {
            liste.remove(index);
            Contact.ecrire(liste);
            App.ajouterContact();
            System.out.println("Contact modifié");
        }
    }

    private static void searchContact1() throws IOException {
        // Saisissez le prénom du contact que vous souhaitez afficher
        System.out.println("\nEntrez le prénom du contact que vous voulez afficher :");
        String numeroASupprimer = scan.nextLine();
    
        // Charger la liste des contacts du fichier dans un ArrayList
        ArrayList<Contact> liste = Contact.lister();
    
        // Utilisez la méthode indexOf() pour rechercher le contact à supprimer
        var index = -1;
        for (int i = 0; i < liste.size(); i++) {
            Contact contact = liste.get(i);
            if (contact.getPrenom().equals(numeroASupprimer)) {
                index = i;
                break;
            }
        }
    
        // Si l'index a été trouvé, afficher la ligne d'index dans le fichier contacts.csv
        if (index != -1) {
            try {
                FileInputStream inputStream = new FileInputStream("contacts.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
                // Sautez les premières lignes jusqu'à la ligne spécifiée par l'index
                for (int i = 0; i < index; i++) {
                    reader.readLine();
                }
            
                // Affichez la ligne d'index
                String line = reader.readLine();
                System.out.println("\nVoilà votre contact : " + "\n" + line + "\n");
            } catch (Exception e) {
                // Gérez les exceptions ici
                System.out.println("Error: " + e.getMessage());
            }         
        } else {
            System.out.println("Contact not found");
        }
    }
}
