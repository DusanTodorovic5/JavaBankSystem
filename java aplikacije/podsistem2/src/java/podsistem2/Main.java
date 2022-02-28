/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;
import entiteti.*;
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
    @Resource(lookup = "podsistem2_sendQueue")
    private static Queue queueSlanje;
    
    @Resource(lookup = "podsistem2_recieveQueue")
    private static Queue queuePrima;
    
    @Resource(lookup = "backupQueue")
    private static Queue backUpQueue;
    
    private static EntityManager em;
    
    private static PodSistem2Thread thread = new PodSistem2Thread();

    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem2PU");
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
                int tip = -1;
                if(message instanceof ObjectMessage){
                    ObjectMessage msg = (ObjectMessage)message;
                    ArrayList<String> argumenti = (ArrayList<String>)msg.getObject();
                    //System.out.println("Primljena je poruka sa sadrzajem: " + ((TextMessage)message).getText());
                    tip = message.getIntProperty("tip");
                    switch (tip){
                        case 3:
                            odgovori.add(kreirajKomitenta(argumenti.get(0), argumenti.get(1),Integer.parseInt(argumenti.get(2))));
                            break;
                        case 5://otvoriRacun(int dozvMinus, int idMesto, int idKom)
                            odgovori.add(otvoriRacun(Integer.parseInt(argumenti.get(0)),Integer.parseInt(argumenti.get(1)),Integer.parseInt(argumenti.get(2))));
                            break;
                        case 6://zatvoriRacun(int idRac)
                            odgovori.add(zatvoriRacun(Integer.parseInt(argumenti.get(0))));
                            break;
                        case 7://kreirajPrenos(int iznos, String svrha, int idRac, int saRacun)
                            odgovori.add(kreirajPrenos(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2)), Integer.parseInt(argumenti.get(3))));
                            break;
                        case 8://kreirajUplata(int iznos, String svrha, int idRac, int idFil
                            odgovori.add(kreirajUplata(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2)), Integer.parseInt(argumenti.get(3))));
                            break;
                        case 9:// kreirajIsplata(int iznos, String svrha, int idRac, int idFil)
                            odgovori.add(kreirajIsplata(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2)), Integer.parseInt(argumenti.get(3))));
                            break;
                        case 13:// sviRacuni(int idKom)
                            odgovori = sviRacuni(Integer.parseInt(argumenti.get(0)));
                            break;
                        case 14:// sveTransakcije(int idRac)
                            odgovori = sveTransakcije(Integer.parseInt(argumenti.get(0)));
                            break;
                    }
                }
                for (String s : odgovori){
                    System.out.println(s);
                }
                System.out.println("Salje odgovor");
                ObjectMessage odgovor = context.createObjectMessage();
                odgovor.setObject(odgovori);
                if (tip != 3)
                    producer.send(destinationSlanje, odgovor);
            }
            
        } catch (JMSException ex) {
            System.out.println("Greska");
        }
    }
    
    private static String otvoriRacun(int dozvMinus, int idMesto, int idKom){
       TypedQuery<Komitent> query = em.createNamedQuery("Komitent.findByIdKomitent",Komitent.class);
       query.setParameter("idKomitent", idKom);
       List<Komitent> mestaList = query.getResultList();
       if (mestaList.size() < 1){
           return "Neuspesno : takav komitent ne postoji";
       }
       Racun m = new Racun();
       m.setDozvoljenMinus(dozvMinus);
       m.setMesto(idMesto);
       m.setIdKomitent(mestaList.get(0));
       m.setDatum(new java.util.Date());
       m.setVreme(new java.util.Date());
       m.setStatus("A");
       m.setStanje(0);
       m.setBrojTransakcija(0);
       EntityTransaction trans = em.getTransaction();
       trans.begin();
       em.persist(m);
       em.flush();
       em.refresh(m);
       trans.commit();
       return "Uspesno : Nov racun kreiran";
    }
    private static String zatvoriRacun(int idRac){
        Racun r = em.find(Racun.class, idRac);
        if (r == null){
            return "Neuspesno : racun ne postoji";
        }
        if (r.getStatus().equals("U")){
            return "Neuspesno : racun vec zatvoren";
        }
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        r.setStatus("U");
        r.setStanje(null);
        r.setDozvoljenMinus(null);
        em.persist(r);
        em.flush();
        em.refresh(r);
        trans.commit();
        return "Uspesno : racun zatvoren";
    }
    private static String kreirajPrenos(int iznos, String svrha, int idRac, int naRacun){
        Racun sr = em.find(Racun.class, naRacun);
        if (sr == null){
            return "Neuspeh : racun ne postoji";
        }
        if (sr.getStatus().equals("U")){
            return "Neuspeh : racun ugasen";
        }
        String povr = kreirajTrans(idRac,iznos,svrha,"P",sr,null);
        String usp = povr.split(" ")[0];
        if (!usp.equals("uspeh")){
            return povr;
        }
        sr.setStanje(iznos + sr.getStanje());
        if (sr.getStanje() > sr.getDozvoljenMinus()){
            sr.setStatus("A");
        }
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(sr);
        em.flush();
        em.refresh(sr);
        trans.commit();
        return "uspesno";
    }
    private static String kreirajUplata(int iznos, String svrha, int idRac, int idFil){
        String povr = kreirajTrans(idRac,iznos,svrha,"U",null,idFil);
        String usp = povr.split(" ")[0];
        if (!usp.equals("uspeh")){
            return povr;
        }
        return "uspesno";
    }
    private static String kreirajIsplata(int iznos, String svrha, int idRac, int idFil){
        String povr = kreirajTrans(idRac,iznos,svrha,"I",null,idFil);
        String usp = povr.split(" ")[0];
        if (!usp.equals("uspeh")){
            return povr;
        }
        return "uspesno";
    }
    private static String kreirajTrans(int idRac, int iznos, String svrha, String mod, Racun na_rac, Integer idFil){
        TypedQuery<Racun> query = em.createNamedQuery("Racun.findByBrojRacuna", Racun.class);
        query.setParameter("brojRacuna", idRac);
        ArrayList<Racun> racuni = new ArrayList<Racun>(query.getResultList());
        if (racuni.size() < 1){
            return "Neuspeh : ne postoji racun";
        }
        
        Racun r = racuni.get(0);
        if (r.getStatus().equals("U")){
            return "Neuspeh : racun ugasen";
        }
        if ((mod.equals("I") || mod.equals("P")) && r.getStatus().equals("B")){
            return "Neuspeh : racun blokiran";
        }
        int br = r.getBrojTransakcija()+1;
        r.setBrojTransakcija(br);
        if (mod.equals("I") || mod.equals("P"))
            r.setStanje(r.getStanje() - iznos);
        else
            r.setStanje(r.getStanje() + iznos);
        if (r.getStanje() > r.getDozvoljenMinus()){
            r.setStatus("A");
        }
        if (r.getStanje() < r.getDozvoljenMinus()){
            r.setStatus("B");
        }
        Transakcija t = new Transakcija();
        t.setDatum(new java.util.Date());
        t.setVreme(new java.util.Date());
        t.setIznos(iznos);
        t.setSvrha(svrha);
        t.setIdRacun(r);
        t.setRbRacuna(br);
        t.setTipTrans(mod);
        t.setIdFil(idFil);
        t.setRacun(na_rac);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(r);
        em.persist(t);
        em.flush();
        em.refresh(r);
        em.refresh(t);
        trans.commit();
        return "uspeh " + t.getIdTransakcije();
    }
    private static ArrayList<String> sviRacuni(int idKom){
        TypedQuery<Racun> query = em.createNamedQuery("Racun.findAll",Racun.class);
        ArrayList<Racun> mesta = new ArrayList<Racun>(query.getResultList());
        Komitent k = em.find(Komitent.class, idKom);
        ArrayList<String> stringMesta = new ArrayList<String>();
        for (Racun r : mesta){
            if (r.getIdKomitent() == k){
                stringMesta.add(r.getBrojRacuna() + ";" + r.getStanje() + ";" + r.getDozvoljenMinus() + ";" + r.getStatus() + ";" +
                                   r.getDatum() + ";" + r.getVreme() + r.getBrojTransakcija());
            }
        }
        return stringMesta;
    }
    private static ArrayList<String> sveTransakcije(int idRac){
        TypedQuery<Transakcija> query = em.createNamedQuery("Transakcija.findAll",Transakcija.class);
        ArrayList<Transakcija> mesta = new ArrayList<Transakcija>(query.getResultList());
        Racun r = em.find(Racun.class, idRac);
        ArrayList<String> stringMesta = new ArrayList<String>();
        for (Transakcija t : mesta){
            if (t.getIdRacun() == r){
                String idRacunNa = "NULL";
                String idFil = "NULL";
                if (t.getRacun() != null){
                    idRacunNa = t.getRacun().getBrojRacuna() + "";
                }
                if (t.getIdFil() != null){
                    idFil = t.getIdFil() + "";
                }
                stringMesta.add(t.getIdTransakcije() + ";" + t.getDatum() + ";" + t.getVreme() + ";" + t.getIznos() + ";" + t.getRbRacuna() + ";" +
                            t.getSvrha() + " " + t.getTipTrans() + ";" + idRacunNa + ";" + idFil);
            }
        }
        return stringMesta;
    }
    
    public static String kreirajKomitenta(String naziv, String adresa, int mestoId){
        Komitent k = new Komitent();
        k.setAdresa(adresa);
        k.setMesto(mestoId);
        k.setNaziv(naziv);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(k);
        em.flush();
        em.refresh(k);
        trans.commit();
        return "Uspesno : Nov komitent kreiran";
    }
}
