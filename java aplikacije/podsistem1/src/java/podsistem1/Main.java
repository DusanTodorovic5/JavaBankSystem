/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import entiteti.*;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.TypedQuery;
/**
 *
 * @author virtuel
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem1_sendQueue")
    private static Queue queueSlanje;
    
    @Resource(lookup = "podsistem1_recieveQueue")
    private static Queue queuePrima;
    
    @Resource(lookup = "backupQueue")
    private static Queue backUpQueue;
    
    private static EntityManager em;
    
    private static PodSistem1Thread thread = new PodSistem1Thread();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem1PU");
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
                ArrayList<String> odgovori = new ArrayList<String>();
                if(message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage)message;
                    ArrayList<String> argumenti = (ArrayList<String>)msg.getObject();
                    //System.out.println("Primljena je poruka sa sadrzajem: " + ((TextMessage)message).getText());
                    int tip = message.getIntProperty("tip");
                   
                    switch (tip){
                        case 1:
                            System.out.println(argumenti.get(0) + " " + argumenti.get(1));
                            odgovori.add(kreirajMesto(argumenti.get(0),argumenti.get(1)));
                            break;
                        case 2://(String naziv, String adresa, int mestoId
                            odgovori.add(kreirajFilijalu(argumenti.get(0),argumenti.get(1),Integer.parseInt(argumenti.get(2))));
                            break;
                        case 3://kreirajKomitenta(String naziv, String adresa, int mestoId)
                            odgovori.add(kreirajKomitenta(argumenti.get(0), argumenti.get(1),Integer.parseInt(argumenti.get(2))));
                            break;
                        case 4://azurirajKomitenta(int idKom, String adresa, int idMesto)
                            odgovori.add(azurirajKomitenta(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2))));
                            break;
                        case 10:// svaMesta
                            odgovori = svaMesta();
                            break;
                        case 11:// sveFilijale
                            odgovori = sveFilijale();
                            break;
                        case 12:// sviKomitenti
                            odgovori = sviKomitenti();
                            break;
                    }
                }
                for (String s : odgovori){
                    System.out.println(s);
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
    
    public static String kreirajMesto(String postanskiBroj, String naziv){
       TypedQuery<Mesto> query = em.createNamedQuery("Mesto.findByPostanskiBroj",Mesto.class);
       query.setParameter("postanskiBroj", postanskiBroj);
       List<Mesto> mestaList = query.getResultList();
       if (mestaList.size() > 0){
           return "Neuspesno : takvo mesto vec postoji u bazi";
       }
       Mesto m = new Mesto();
       m.setNaziv(naziv);
       m.setPostanskiBroj(postanskiBroj);
       EntityTransaction trans = em.getTransaction();
       trans.begin();
       em.persist(m);
       em.flush();
       em.refresh(m);
       trans.commit();

       return "Uspesno : Novo mesto kreirano";
    }
    
    public static String kreirajFilijalu(String naziv, String adresa, int mestoId){
        TypedQuery<Mesto> queryMesto = em.createNamedQuery("Mesto.findByIdMesto", Mesto.class);
        queryMesto.setParameter("idMesto", mestoId);
        if (queryMesto.getResultList().size() < 1){
            return "Neuspesno : takvo mesto ne postoji";
        }
        Filijala f = new Filijala();
        f.setAdresa(adresa);
        f.setMestoFil(queryMesto.getResultList().get(0));
        f.setNaziv(naziv);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(f);
        em.flush();
        em.refresh(f);
        trans.commit();
        return "Uspesno : Nova filijala kreirana";
    }
    
    public static String kreirajKomitenta(String naziv, String adresa, int mestoId){
        TypedQuery<Mesto> queryMesto = em.createNamedQuery("Mesto.findByIdMesto", Mesto.class);
        queryMesto.setParameter("idMesto", mestoId);
        if (queryMesto.getResultList().size() < 1){
            return "Neuspesno : takvo mesto ne postoji";
        }
        Komitent k = new Komitent();
        k.setAdresa(adresa);
        k.setMesto(queryMesto.getResultList().get(0));
        k.setNaziv(naziv);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(k);
        em.flush();
        em.refresh(k);
        trans.commit();
        return "Uspesno : Nov komitent kreiran";
    }
    
    public static String azurirajKomitenta(int idKom, String adresa, int idMesto){
        TypedQuery<Mesto> queryMesto = em.createNamedQuery("Mesto.findByIdMesto", Mesto.class);
        queryMesto.setParameter("idMesto", idMesto);
        if (queryMesto.getResultList().size() < 1){
            return "Neuspesno : takvo mesto ne postoji";
        }
        TypedQuery<Komitent> queryKomitent = em.createNamedQuery("Komitent.findByIdKomitent", Komitent.class);
        queryKomitent.setParameter("idKomitent", idKom);
        if (queryKomitent.getResultList().size() < 1){
            return "Neuspesno : takav komitent ne postoji";
        }
        Komitent k = queryKomitent.getResultList().get(0);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        k.setAdresa(adresa);
        k.setMesto(queryMesto.getResultList().get(0));
        em.persist(k);
        em.flush();
        em.refresh(k);
        trans.commit();
        return "Uspesno : promenjeno mesto komitenta";
    }
    
    public static ArrayList<String> svaMesta(){
        TypedQuery<Mesto> query = em.createNamedQuery("Mesto.findAll",Mesto.class);
        ArrayList<Mesto> mesta = new ArrayList<Mesto>(query.getResultList());
        
        ArrayList<String> stringMesta = new ArrayList<String>();
        for (Mesto m : mesta){
            stringMesta.add(m.getIdMesto() + ";" + m.getNaziv() + ";" + m.getPostanskiBroj());
        }
        return stringMesta;
    }
    public static ArrayList<String> sveFilijale(){
        TypedQuery<Filijala> query = em.createNamedQuery("Filijala.findAll",Filijala.class);
        ArrayList<Filijala> mesta = new ArrayList<Filijala>(query.getResultList());
        
        ArrayList<String> stringMesta = new ArrayList<String>();
        for (Filijala m : mesta){
            stringMesta.add(m.getIdFilijala() + ";" + m.getNaziv() + ";" + m.getAdresa() + ";" + m.getMestoFil().getNaziv() + ";" + m.getMestoFil().getPostanskiBroj());
        }
        return stringMesta;
    }
    public static ArrayList<String> sviKomitenti(){
        TypedQuery<Komitent> query = em.createNamedQuery("Komitent.findAll",Komitent.class);
        ArrayList<Komitent> mesta = new ArrayList<Komitent>(query.getResultList());
        
        ArrayList<String> stringMesta = new ArrayList<String>();
        for (Komitent m : mesta){
            stringMesta.add(m.getIdKomitent()+ ";" + m.getNaziv() + ";" + m.getAdresa() + ";" + m.getMesto().getPostanskiBroj() + ";" + m.getMesto().getNaziv());
        }
        return stringMesta;
    }
}
