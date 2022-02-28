/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

import entiteti.*;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
/**
 *
 * @author virtuel
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem3_sendQueue")
    private static Queue queueSlanje;
    
    @Resource(lookup = "podsistem3_recieveQueue")
    private static Queue queuePrima;
    
    @Resource(lookup = "backupQueue")
    private static Queue backUpQueue;
    
    private static EntityManager em;
    
    private static PodSistem3Thread thread = new PodSistem3Thread();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem3PU");
        em = emf.createEntityManager();
        
        Destination destinationSlanje = queueSlanje;
        Destination destinationPrima = queuePrima;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationPrima);
        JMSProducer producer = context.createProducer();
        thread.setConnectionFactory(connectionFactory,backUpQueue);
        thread.start();
        
        try {
            while (true){
                Message message = consumer.receive();
                ArrayList<ArrayList<String>> odgovori = new ArrayList<ArrayList<String>>();
                int tip = -1;
                if(message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage)message;
                    ArrayList<String> argumenti = (ArrayList<String>)msg.getObject();
                    //System.out.println("Primljena je poruka sa sadrzajem: " + ((TextMessage)message).getText());
                    tip = message.getIntProperty("tip");
                    switch (tip){
                        
                        case 15:
                            System.out.println("Radim sve");
                            odgovori = dohvatiSve();
                            break;
                        case 16:
                            System.out.println("Radim razlike");
                            odgovori = thread.razlike();
                            break;
                    }
                }
                for (ArrayList<String> lista : odgovori){
                    System.out.println("################");
                    for (String s : lista){
                        System.out.println(s);
                    }
                }
                System.out.println("Salje odgovor");
                ObjectMessage odgovor = context.createObjectMessage();
                odgovor.setObject(odgovori);
                producer.send(destinationSlanje, odgovor);
            }
            
        } catch (JMSException ex) {
            System.out.println("Greska");
        }
    }
    
    public static ArrayList<ArrayList<String>> dohvatiSve(){
        ArrayList<ArrayList<String>> odgovor = new ArrayList<ArrayList<String>>();
        int i =0;
        //Mesta
        TypedQuery<Mesto> query1 = em.createNamedQuery("Mesto.findAll",Mesto.class);
        ArrayList<Mesto> mesta = new ArrayList<Mesto>(query1.getResultList());
        ArrayList<String> strMesta = new ArrayList<String>();
        for (Mesto m : mesta){
            strMesta.add(m.getIdMesto() + ";" + m.getNaziv() + ";" + m.getPostanskiBroj());
        }
        odgovor.add(strMesta);
        //Komitenti
        TypedQuery<Komitent> query2 = em.createNamedQuery("Komitent.findAll",Komitent.class);
        ArrayList<Komitent> komitenti = new ArrayList<Komitent>(query2.getResultList());
        ArrayList<String> strKomitenti = new ArrayList<String>();
        for (Komitent m : komitenti){
            strKomitenti.add(m.getIdKomitent() + ";" + m.getAdresa() + ";" + m.getNaziv());
        }
        odgovor.add(strKomitenti);
        
        //Filijala
        TypedQuery<Filijala> query3 = em.createNamedQuery("Filijala.findAll",Filijala.class);
        ArrayList<Filijala> filijale = new ArrayList<Filijala>(query3.getResultList());
        ArrayList<String> strFilijale = new ArrayList<String>();
        for (Filijala m : filijale){
            strFilijale.add(m.getIdFilijala() + ";" + m.getAdresa() + ";" + m.getNaziv() + ";" + m.getMestoFil().getIdMesto());
        }
        odgovor.add(strFilijale);
        
        //Racun
        TypedQuery<Racun> query4 = em.createNamedQuery("Racun.findAll",Racun.class);
        ArrayList<Racun> racuni = new ArrayList<Racun>(query4.getResultList());
        ArrayList<String> strRacuni = new ArrayList<String>();
        for (Racun m : racuni){
            strRacuni.add(m.getBrojRacuna() + ";" + m.getStatus() + ";" + m.getStanje() + ";" + m.getDozvoljenMinus() + ";" + m.getDatum() + ";" + m.getVreme() +
                    ";" + m.getBrojTransakcija() + ";" + m.getMesto() + ";" + m.getIdKomitent());
        }
        odgovor.add(strRacuni);
        
        //Transakcije
        TypedQuery<Transakcija> query5 = em.createNamedQuery("Transakcija.findAll",Transakcija.class);
        ArrayList<Transakcija> transakcije = new ArrayList<Transakcija>(query5.getResultList());
        ArrayList<String> strTransakcije = new ArrayList<String>();
        for (Transakcija t : transakcije){
            String idRacunNa = "NULL";
            String idFil = "NULL";
            if (t.getNaRacun() != null){
                idRacunNa = t.getNaRacun() + "";
            }
            if (t.getIdFil() != null){
                idFil = t.getIdFil() + "";
            }
            strTransakcije.add(t.getIdTransakcije() + ";" + t.getDatum() + ";" + t.getVreme() + ";" + t.getIznos() + ";" + t.getRbRacuna() + ";" +
                            t.getSvrha() + " " + t.getTipTrans() + ";" + idRacunNa + ";" + idFil);
        }
        odgovor.add(strTransakcije);
        
        return odgovor;
    }
    
}
