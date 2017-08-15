package com.marta.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
//import org.primefaces.showcase.domain.Car;

import com.marta.beans.Issue;
 
@ManagedBean(name = "issueService")
@ApplicationScoped
public class IssueService {
     
    private final static String[] titles;
     
    private final static String[] descriptions;
    private final static String[] types;
    private final static String[] priorities;
    private final static String[] user_ids;
    private final static String[] states;
    
 
    static {
    	titles = new String[10];
    	titles[0] = "Black";
    	titles[1] = "White";
    	titles[2] = "Green";
    	titles[3] = "Red";
    	titles[4] = "Blue";
    	titles[5] = "Orange";
    	titles[6] = "Silver";
    	titles[7] = "Yellow";
    	titles[8] = "Brown";
    	titles[9] = "Maroon";
         
    	types = new String[6];
    	types[0] = "New feature";
    	types[1] = "Improvement";
    	types[2] = "Specs";
    	types[3] = "Recall";
    	types[4] = "Task";
    	types[5] = "Anomaly";
    	
    	priorities = new String[4];
    	priorities[0] = "Blocking";
    	priorities[1] = "Critical";
    	priorities[2] = "Important";
    	priorities[3] = "Minor";

    	user_ids = new String[10];
    	user_ids[0] = "CMA";
    	user_ids[1] = "MJN";
    	user_ids[2] = "JSIMAS";
    	user_ids[3] = "AED";
    	user_ids[4] = "IOA";
    	user_ids[5] = "KGM";
    	user_ids[6] = "FDF";
    	user_ids[7] = "DDS";
    	user_ids[8] = "SKD";
    	user_ids[9] = "DJF"; 
    	
    	states = new String[3];
    	states[0] = "Closed";
    	states[1] = "Open";
    	states[2] = "Re-Onpened";
    	    
    	
    	descriptions = new String[10];
    	descriptions[0] = "Information is that which informs. In other words, it is the answer to a question of some kind. ... The English word was apparently derived from the Latin stem (information-) of the nominative (informatio): this noun is derived from the ver";
    	descriptions[1] = "Je n'ai pas eu la chance d'apprendre le latin (ce que je regrette), et � l'�poque l'informatique � l'�cole n'�tait pas un sujet. Pourtant, je me ";
    	descriptions[2] = "La La Land est un film r�alis� par Damien Chazelle avec Ryan Gosling, Emma Stone. Synopsis : Au c�ur de Los Angeles, une actrice en devenir pr�nomm�e";
    	descriptions[3] = "Film La La Land: L'histoire d'amour et de musique entre une jeune actrice et un pianiste de jazz. Le nouveau film de Damien Chazelle apr�s le ph�nom�ne ";
    	descriptions[4] = "Reste que prendre La La Land pour une simple association de ce que furent les grands films musicaux hollywoodiens - de Minnelli � Donen en passant par ";
    	descriptions[5] = "Au c�ur de Los Angeles, une actrice en devenir pr�nomm�e Mia sert des caf�s entre deux auditions.De son c�t�, Sebastian, passionn� de jazz, joue du piano dans des clubs miteux pour assurer sa subsistance. Tous deux sont bien loin de la vie r�v�e � laquelle ils aspirent... Le destin va r�unir ces doux r�veurs, mais leur coup de foudre r�sistera-t-il aux tentations, aux d�ceptions, et � la vie tr�pidante d'Hollywood ?";
    	descriptions[6] = "Charmeur, s�ducteur, brillant, et donc extr�mement dou�, l'acteur fait ici un parcours sans faute, certes un petit parcours de claquettes bien orchestr� bien s�r, mais aussi de bien belles interpr�tations au piano sans fausse note sans oublier cette voix chaude et parfaite, tout ceci juste pour nous envo�ter comme ce n'est pas permis !";
    	descriptions[7] = "Cependant il est � noter que m�me si ce film de Damien Chazelle baigne dans l'esprit des com�dies musicales vintage, il n'en reste pas moins qu'il n'en est pas v�ritablement une pure et dure de bout en bout, comme on le penserait";
    	descriptions[8] = "L'histoire des deux tourtereaux prend en effet le pas sur le genre attendu, peut-�tre pour d�cevoir les aficionados de ces fameux ballets chant�s, mais aussi pour mieux y faire entrer les plus r�fractaires ";
    	descriptions[9] = "Reste � ajouter � toutes ces consid�rations la magie de la cam�ra de Damien Chazelle, v�ritable baguette enchanteresse tant au niveau des couleurs, des points de vue inou�s, de l'atmosph�re au charme fou, juste ce qu'il faut de kitch et de sixties";
    }
    
     
    public List<Issue> createIssues(int size) {
        List<Issue> list = new ArrayList<Issue>();
        for(int i = 0 ; i < size ; i++) {
            //list.add(new Issue(getRandomId(), getRandomTitle(), getRandomDescription(), getRandomType(), getRandomPriority(), getRandomCUser(), getRandomAUser(),getRandomState() ));
        }    
        return list;
    }
     
    private int getRandomId() {
    	return 1;
        //return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private String getRandomTitle() {
        return titles[(int) (Math.random() * 10)];
    }
     
    private String getRandomDescription() {
        return descriptions[(int) (Math.random() * 10)];
    }
     
    private String getRandomType() {
        return types[(int) (Math.random() * 6)];
    }
     
    public String getRandomPriority() {
    	return priorities[(int) (Math.random() * 4)];
    }
     
    public String getRandomCUser() {
    	return user_ids[(int) (Math.random() * 10)];
    }
    
    public int getRandomAUser() {
    	return 1;
    	//return user_ids[(int) (Math.random() * 10)];
    }
    
    public String getRandomState() {
    	return states[(int) (Math.random() * 3)];
    }
 
    public List<String> getTitles() {
        return Arrays.asList(titles);
    }
     
    public List<String> getDescriptions() {
        return Arrays.asList(descriptions);
    }
    
}