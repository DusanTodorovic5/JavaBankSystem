/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;
import entiteti.*;
import entiteti.Komitent;
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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author virtuel
 */
public class PodSistem2Thread extends Thread {
    
    private ConnectionFactory connectionFactory;

    private Queue queue;
    
    private EntityManager em;
    
    @Override
    public void run(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem2PU");
        em = emf.createEntityManager();
        
        Destination destinationPrima = queue;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationPrima, "id = 2");
        JMSProducer producer = context.createProducer();
        
        while (true){
            try {
                System.out.println("Cekam");
                Message m = consumer.receive();
                
                ObjectMessage objMsg = context.createObjectMessage();
                objMsg.setIntProperty("id", 4);
                System.out.println("Pravim promene");
                PodSistem2Promene promene = getPromene();
                
                objMsg.setObject(promene);
                System.out.println("Saljem");
                producer.send(destinationPrima, objMsg);
                
            } catch (JMSException ex) {
                Logger.getLogger(PodSistem2Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    void setConnectionFactory(ConnectionFactory f, Queue q){
        connectionFactory = f;
        queue = q;
    }
    
    PodSistem2Promene getPromene(){
        PodSistem2Promene promene = new PodSistem2Promene();
        TypedQuery<Racun> query = em.createNamedQuery("Racun.findAll",Racun.class);
        ArrayList<Racun> rl = new ArrayList<Racun>(query.getResultList());
        for (Racun r : rl){
            promene.dodajRacun(r);
        }
        TypedQuery<Transakcija> query2 = em.createNamedQuery("Transakcija.findAll",Transakcija.class);
        ArrayList<Transakcija> tl = new ArrayList<Transakcija>(query2.getResultList());
        for (Transakcija t : tl){
            promene.dodajTrans(t);
        }
        
        return promene;
    }
}
