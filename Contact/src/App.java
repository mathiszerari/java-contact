import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
                    modifContact();
                    break;
                case "5":
                    searchContact();
                    break;
                case "6":
                    sortAContact();
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
        menus.add("\n-- MENU --");
        menus.add("1- Ajouter un contact");
        menus.add("2- Lister les contacts");
        menus.add("3- Supprimer un contact");
        menus.add("4- Modifier un contact");
        menus.add("5- Afficher un contact");
        menus.add("6- Trier les contacts par nom");
        menus.add("7- Trier les contacts par date de naissance");
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

    private static void modifContact() {
        System.out.println("Entrez le prénom du contact que vous voulez modifier");
        String numeroASupprimer = scan.nextLine();
        try {
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
                Contact contact = liste.get(index); // utilisez la variable 'i' déclarée précédemment
                System.out.println(contact);

                while(true) {
                    System.out.println("\nQue voulez-vous modifier parmi le nom, le prenom, le mail, le numéro et la date de naissance ? \nSi vous souhaitez quitter ce menu tapez 'quitter'");
                    String champAModifier = scan.nextLine(); // stockez la valeur entrée par l'utilisateur
                    switch (champAModifier) {
                    case "nom":
                        System.out.println("\nEntrez le nouveau nom");
                        String nouveauNom = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setNom(nouveauNom); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "prenom":
                        System.out.println("\nEntrez le nouveau prenom");
                        String nouveauprenom = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setPrenom(nouveauprenom); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "mail":
                        System.out.println("\nEntrez le nouveau mail");
                        String nouveaumail = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setMail(nouveaumail); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "numero":
                        System.out.println("\nEntrez le nouveau numero");
                        String nouveaunumero = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setNumero(nouveaunumero); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "date":
                        System.out.println("\nEntrez la nouvelle date");
                        String nouveaudate = scan.nextLine(); // stockez le nouveau nom entré par l'utilisateur  
                        contact.setDateNaissance(nouveaudate); // utilisez la méthode setNom() pour mettre à jour le nom du contact
                        break;
                    case "quitter":
                        // afficherMenu();
                        return;
                    default:
                        System.out.println("pabon");
                        break;
                    }
                    Contact.ecrire(liste); // écrivez la liste modifiée dans le fichier contacts.csv
                }
            } else{
                System.out.println("contact non trouvé");
            }
        }
        catch(IOException e) {
            System.out.println("erreur avec le fichier");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void searchContact() throws IOException {
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


    private static void sortAContact() throws IOException {
        // Lecture du fichier en entrée
        File inputFile = new File("contacts.csv");
        Scanner input = new Scanner(inputFile);

        // Création de l'ArrayList pour stocker les lignes du fichier
        ArrayList<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
        lines.add(input.nextLine());
        }
        input.close();

        // Tri des lignes du fichier par ordre alphabétique
        Collections.sort(lines);

        // Écriture des lignes triées dans un nouveau fichier
        File outputFile = new File("contacts.csv");
        PrintWriter output = new PrintWriter(outputFile);
        for (String line : lines) {
            output.println(line);
        }
        output.close();
        System.out.println("Votre fichier à été trié");
    }
}
