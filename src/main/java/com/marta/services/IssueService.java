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
    	descriptions[1] = "Je n'ai pas eu la chance d'apprendre le latin (ce que je regrette), et à l'époque l'informatique à l'école n'était pas un sujet. Pourtant, je me ";
    	descriptions[2] = "La La Land est un film réalisé par Damien Chazelle avec Ryan Gosling, Emma Stone. Synopsis : Au cœur de Los Angeles, une actrice en devenir prénommée";
    	descriptions[3] = "Film La La Land: L'histoire d'amour et de musique entre une jeune actrice et un pianiste de jazz. Le nouveau film de Damien Chazelle après le phénomène ";
    	descriptions[4] = "Reste que prendre La La Land pour une simple association de ce que furent les grands films musicaux hollywoodiens - de Minnelli à Donen en passant par ";
    	descriptions[5] = "Au cœur de Los Angeles, une actrice en devenir prénommée Mia sert des cafés entre deux auditions.De son côté, Sebastian, passionné de jazz, joue du piano dans des clubs miteux pour assurer sa subsistance. Tous deux sont bien loin de la vie rêvée à laquelle ils aspirent... Le destin va réunir ces doux rêveurs, mais leur coup de foudre résistera-t-il aux tentations, aux déceptions, et à la vie trépidante d'Hollywood ?";
    	descriptions[6] = "Charmeur, séducteur, brillant, et donc extrêmement doué, l'acteur fait ici un parcours sans faute, certes un petit parcours de claquettes bien orchestré bien sûr, mais aussi de bien belles interprétations au piano sans fausse note sans oublier cette voix chaude et parfaite, tout ceci juste pour nous envoûter comme ce n'est pas permis !";
    	descriptions[7] = "Cependant il est à noter que même si ce film de Damien Chazelle baigne dans l'esprit des comédies musicales vintage, il n'en reste pas moins qu'il n'en est pas véritablement une pure et dure de bout en bout, comme on le penserait";
    	descriptions[8] = "L'histoire des deux tourtereaux prend en effet le pas sur le genre attendu, peut-être pour décevoir les aficionados de ces fameux ballets chantés, mais aussi pour mieux y faire entrer les plus réfractaires ";
    	descriptions[9] = "Reste à ajouter à toutes ces considérations la magie de la caméra de Damien Chazelle, véritable baguette enchanteresse tant au niveau des couleurs, des points de vue inouïs, de l'atmosphère au charme fou, juste ce qu'il faut de kitch et de sixties";
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