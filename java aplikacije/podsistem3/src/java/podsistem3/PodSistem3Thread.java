/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;
import entiteti.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author virtuel
 */
public class PodSistem3Thread extends Thread {
    
    private ConnectionFactory connectionFactory;
    
    private Queue queue;
    
    private EntityManager em;
    
    @Override
    public void run(){
        
        
        Destination destinationPrima = queue;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer1 = context.createConsumer(destinationPrima, "id = 3");
        JMSConsumer consumer2 = context.createConsumer(destinationPrima, "id = 4");
        JMSProducer producer = context.createProducer();
        
        while (true){
            try {
                Thread.sleep(120000);
                
                ObjectMessage msg1 = context.createObjectMessage();
                msg1.setObject(new ArrayList<String>());
                msg1.setIntProperty("id", 1);
                
                producer.send(destinationPrima, msg1);
                
                ObjectMessage msg2 = context.createObjectMessage();
                msg2.setObject(new ArrayList<String>());
                msg2.setIntProperty("id", 2);
                
                producer.send(destinationPrima, msg2);
                
                Message m1 = consumer1.receive();
                
                PodSistem1Promene p1 = (PodSistem1Promene)((ObjectMessage)m1).getObject();
                
                Message m2 = consumer2.receive();
                
                PodSistem2Promene p2 = (PodSistem2Promene)((ObjectMessage)m2).getObject();
                
                
                checkMesta(p1.mesta);
                checkFilijale(p1.filijale);
                checkKomitenti(p1.komitenti);
                checkRacuni(p2.racuni);
                checkTrans(p2.transakcije);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PodSistem3Thread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JMSException ex) {
                Logger.getLogger(PodSistem3Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    void setConnectionFactory(ConnectionFactory f, Queue q){
        connectionFactory = f;
        queue = q;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem3PU");
        em = emf.createEntityManager();
    }
    
    void checkMesta(ArrayList<PodSistem1Promene.MestoZas> ml){
        for (PodSistem1Promene.MestoZas m : ml){
            Mesto mm = em.find(Mesto.class, m.id);
            if (mm != null){
                boolean pers = false;
                if (!mm.getNaziv().equals(m.naziv)){
                    pers = true;
                    mm.setNaziv(m.naziv);
                }
                if (!mm.getPostanskiBroj().equals(m.broj)){
                    pers = true;
                    mm.setPostanskiBroj(m.broj);
                }
                if (pers){
                    EntityTransaction trans = em.getTransaction();
                    trans.begin();
                    em.persist(mm);
                    em.flush();
                    em.refresh(mm);
                    trans.commit();
                }
                
            }
            else{
                mm = new Mesto();
                mm.setNaziv(m.naziv);
                mm.setPostanskiBroj(m.broj);
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                em.persist(mm);
                em.flush();
                em.refresh(mm);
                trans.commit();
            }
        }
    }
    
    void checkFilijale(ArrayList<PodSistem1Promene.FilijalaZas> fl){
        for (PodSistem1Promene.FilijalaZas f : fl){
            Filijala ff = em.find(Filijala.class, f.id);
            if (ff != null){
                boolean pers = false;
                if (!ff.getNaziv().equals(f.naziv)){
                    pers = true;
                    ff.setNaziv(f.naziv);
                }
                if (!ff.getAdresa().equals(f.adresa)){
                    pers = true;
                    ff.setAdresa(f.adresa);
                }
                if (pers){
                    EntityTransaction trans = em.getTransaction();
                    trans.begin();
                    em.persist(ff);
                    em.flush();
                    em.refresh(ff);
                    trans.commit();
                }
            }
            else{
                ff = new Filijala();
                ff.setNaziv(f.naziv);
                ff.setAdresa(f.adresa);
                ff.setMestoFil(em.find(Mesto.class, f.mesto));
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                em.persist(ff);
                em.flush();
                em.refresh(ff);
                trans.commit();
            }
        }
    }
    
    void checkKomitenti(ArrayList<PodSistem1Promene.KomZas> kl){
        for (PodSistem1Promene.KomZas k : kl){
            Komitent kk = em.find(Komitent.class, k.id);
            if (kk != null){
                boolean persist = false;
                if (!kk.getAdresa().equals(k.adresa)){
                    persist = true;
                    kk.setAdresa(k.adresa);
                }
                if (!kk.getNaziv().equals(k.naziv)){
                    persist = true;
                    kk.setNaziv(k.naziv);
                }
                if (kk.getMesto().getIdMesto() != k.mesto){
                    persist = true;
                    kk.setMesto(em.find(Mesto.class, k.mesto));
                }
                if (persist){
                    EntityTransaction trans = em.getTransaction();
                    trans.begin();
                    em.persist(kk);
                    em.flush();
                    em.refresh(kk);
                    trans.commit();
                }
            }
            else{
                kk = new Komitent();
                kk.setMesto(em.find(Mesto.class,k.mesto));
                kk.setAdresa(k.adresa);
                kk.setNaziv(k.naziv);
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                em.persist(kk);
                em.flush();
                em.refresh(kk);
                trans.commit();
            }
        }
    }
    
    void checkRacuni(ArrayList<PodSistem2Promene.RacunZas> rl){
        for (PodSistem2Promene.RacunZas r : rl){
            Racun rr = em.find(Racun.class, r.brR);
            System.out.println("################" + rr);
            System.out.println(r.brR);
            if (rr != null){
                boolean persist = false;
                if (rr.getStanje() != r.stanje){
                    persist = true;
                    rr.setStanje(r.stanje);
                }
                if (!rr.getStatus().equals(r.status)){
                    persist = true;
                    rr.setStatus(r.status);
                }
                if (rr.getBrojTransakcija() != r.brTr){
                    persist = true;
                    rr.setBrojTransakcija(r.brTr);
                }
                if (persist){
                    EntityTransaction trans = em.getTransaction();
                    trans.begin();
                    em.persist(rr);
                    em.flush();
                    em.refresh(rr);
                    trans.commit();
                }
            }
            else{
                rr = new Racun();
                rr.setBrojRacuna(r.brR);
                rr.setBrojTransakcija(r.brTr);
                rr.setDatum(r.datum);
                rr.setVreme(r.vreme);
                rr.setDozvoljenMinus(r.dozvoljen);
                rr.setStanje(r.stanje);
                rr.setStatus(r.status);
                rr.setMesto(r.mesto);
                rr.setIdKomitent(r.komit);
                
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                em.persist(rr);
                em.flush();
                em.refresh(rr);
                trans.commit();
            }
        }
    }
    
    void checkTrans(ArrayList<PodSistem2Promene.TransZas> tl){
        for (PodSistem2Promene.TransZas t : tl){
            Transakcija tt = em.find(Transakcija.class, t.idTr);
            System.out.println("################" + tt);
            System.out.println(t.idRac);
            System.out.println(t.naRacun);
            System.out.println(t.idTr);
            System.out.println("######\n");
            if (tt == null){
                tt = new Transakcija();
                tt.setIdTransakcije(t.idTr);
                tt.setDatum(t.datum);
                tt.setVreme(t.vreme);
                tt.setIznos(t.iznos);
                tt.setRbRacuna(t.rbRacun);
                tt.setSvrha(t.svrha);
                tt.setIdRacun(t.idRac);
                tt.setTipTrans(t.tip);
                tt.setIdFil(t.idFil);
                if (t.tip.equals("P")){
                    tt.setNaRacun(t.naRacun);
                }
                else{
                    tt.setNaRacun(null);
                }
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                em.persist(tt);
                em.flush();
                em.refresh(tt);
                trans.commit();
            }
        }
    }
    
    public ArrayList<ArrayList<String>> razlike(){
        ArrayList<ArrayList<String>> razlike = new ArrayList<>();
        
        Destination destinationPrima = queue;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer1 = context.createConsumer(destinationPrima, "id = 3");
        JMSConsumer consumer2 = context.createConsumer(destinationPrima, "id = 4");
        JMSProducer producer = context.createProducer();

            try {
                System.out.println("Saljem prvom");
                ObjectMessage msg1 = context.createObjectMessage();
                msg1.setObject(new ArrayList<String>());
                msg1.setIntProperty("id", 1);
                
                producer.send(destinationPrima, msg1);
                System.out.println("Saljem drugom");
                ObjectMessage msg2 = context.createObjectMessage();
                msg2.setObject(new ArrayList<String>());
                msg2.setIntProperty("id", 2);
                
                producer.send(destinationPrima, msg2);
                System.out.println("uzimam prvom");
                Message m1 = consumer1.receive();
                
                PodSistem1Promene p1 = (PodSistem1Promene)((ObjectMessage)m1).getObject();
                
                System.out.println("uzimam drugom");
                Message m2 = consumer2.receive();
                
                PodSistem2Promene p2 = (PodSistem2Promene)((ObjectMessage)m2).getObject();
                
                //Mesta provera
                System.out.println("Krenuo");
                razlike.add(mestaRazlike(p1.mesta));
                System.out.println("Krenuo2");
                razlike.add(filijaleRazlike(p1.filijale));
                System.out.println("Krenuo3");
                razlike.add(komitentRazlike(p1.komitenti));
                System.out.println("Krenuo4");
                razlike.add(racunRazlike(p2.racuni));
                System.out.println("Krenu5");
                razlike.add(transRazlike(p2.transakcije));
                System.out.println("Proso");
                
            } catch (JMSException ex) {
                Logger.getLogger(PodSistem3Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        return razlike;
    }
    
    ArrayList<String> mestaRazlike(ArrayList<PodSistem1Promene.MestoZas> mesta){
        ArrayList<String> razlike = new ArrayList<>();
        
        for (PodSistem1Promene.MestoZas m : mesta){
            Mesto mm = em.find(Mesto.class, m.id);
            if (mm != null){
                if (!mm.getPostanskiBroj().equals(m.broj) || !mm.getNaziv().equals(m.naziv)){
                    razlike.add(m.id + ";" + mm.getPostanskiBroj() + " -> " + m.broj + ";" + mm.getNaziv() + " -> " + m.naziv);
                }
            }
            else{
                razlike.add(m.id + ";"  + m.broj + ";"  + m.naziv);
            }
        }
        
        return razlike;
    }
    
    ArrayList<String> filijaleRazlike(ArrayList<PodSistem1Promene.FilijalaZas> filijale){
        ArrayList<String> razlike = new ArrayList<>();
        
        for (PodSistem1Promene.FilijalaZas f : filijale){
            Filijala ff = em.find(Filijala.class, f.id);
            if (ff != null){
                if (!ff.getAdresa().equals(f.adresa) || !ff.getNaziv().equals(f.naziv) || ff.getMestoFil().getIdMesto() != f.mesto){
                    razlike.add(f.id + ";" + ff.getAdresa() + " -> " + f.adresa + ";" + ff.getNaziv() + " -> " + f.naziv + ";" + 
                                ff.getMestoFil().getIdMesto() + " -> " + f.mesto);
                }
            }
            else{
                razlike.add(f.id + ";" + f.adresa + ";" + f.naziv + ";" + f.mesto);
            }
        }
        
        return razlike;
    }
    
    ArrayList<String> komitentRazlike(ArrayList<PodSistem1Promene.KomZas> komitenti){
        ArrayList<String> razlike = new ArrayList<>();
        
        for (PodSistem1Promene.KomZas k : komitenti){
            Komitent kk = em.find(Komitent.class, k.id);
            if (kk != null){
                if (!k.adresa.equals(kk.getAdresa()) || !k.naziv.equals(kk.getNaziv()) || k.mesto != kk.getMesto().getIdMesto()){
                    razlike.add(k.id + ";" + kk.getAdresa() + " -> " + k.adresa + ";" + kk.getNaziv() + " -> " + k.naziv + ";" +
                            kk.getMesto().getIdMesto() + " -> " + k.mesto);
                }
            }
            else{
                razlike.add(k.id + ";" + k.adresa + ";" + k.naziv + ";" + k.mesto);
            }
        }
        
        return razlike;
    }
    
    ArrayList<String> racunRazlike(ArrayList<PodSistem2Promene.RacunZas> racuni){
        ArrayList<String> razlike = new ArrayList<>();
        
        for (PodSistem2Promene.RacunZas r : racuni){
            Racun rr = em.find(Racun.class, r.brR);
            if (rr != null){
                String status = "" + rr.getStatus();
                String stanje = "" + rr.getStanje();
                String dozv = "" + rr.getDozvoljenMinus();
                if (r.stanje != rr.getStanje()){
                    stanje = stanje + " -> " + r.stanje;
                }
                if (!r.status.equals(rr.getStatus())){
                    status = status + " -> " + r.status;
                }
                razlike.add(r.brR + ";" + status + ";" + stanje + ";" + dozv + ";" +
                        r.komit + ";" + r.mesto);
            }
            else{
                razlike.add(r.brR + ";" + r.status + ";" + r.stanje + ";" + r.dozvoljen + ";" +
                        r.komit + ";" + r.mesto);
            }
        }
        
        return razlike;
    }
    
    ArrayList<String> transRazlike(ArrayList<PodSistem2Promene.TransZas> transakcije){
        ArrayList<String> razlike = new ArrayList<>();
        
        for (PodSistem2Promene.TransZas t : transakcije){
            Transakcija tt = em.find(Transakcija.class, t.idTr);
            if (tt == null){
                String idRacunNa = "NULL";
                String idFil = "NULL";
                if (t.tip.equals("P")){
                    idRacunNa = t.naRacun + "";
                }
                else{
                    idFil = t.idFil + "";
                }
                razlike.add(t.idTr + ";" + t.datum + ";" + t.vreme + ";" + t.iznos + ";" + t.idRac + ";" + t.rbRacun + ";" +
                            t.svrha + " " + t.tip + ";" + idRacunNa + ";" + idFil);
            }
        }
        
        return razlike;
    }
    
}
