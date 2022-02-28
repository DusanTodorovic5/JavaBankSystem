/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;
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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 *
 * @author virtuel
 */
public class PodSistem1Thread extends Thread{
    
    
    private ConnectionFactory connectionFactory;
    
    private Queue queue;
    
    private EntityManager em;
    
    @Override
    public void run(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem1PU");
        em = emf.createEntityManager();
        
        Destination destinationPrima = queue;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationPrima, "id = 1");
        JMSProducer producer = context.createProducer();
        
        while (true){
            try {
                System.out.println("Cekam");
                Message m = consumer.receive();
                
                ObjectMessage objMsg = context.createObjectMessage();
                objMsg.setIntProperty("id", 3);
                System.out.println("Uzimam promene");
                PodSistem1Promene promene = getPromene();
                
                objMsg.setObject(promene);
                System.out.println("Saljem");
                producer.send(destinationPrima, objMsg);
                
            } catch (JMSException ex) {
                Logger.getLogger(PodSistem1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    void setConnectionFactory(ConnectionFactory f, Queue q){
        connectionFactory = f;
        queue = q;
    }
    
    PodSistem1Promene getPromene(){
        PodSistem1Promene promene = new PodSistem1Promene();
        TypedQuery<Mesto> query = em.createNamedQuery("Mesto.findAll",Mesto.class);
        ArrayList<Mesto> ml = new ArrayList<Mesto>(query.getResultList());
        
        for (Mesto m : ml){
            promene.dodajMesto(m);
        }
        
        TypedQuery<Filijala> query2 = em.createNamedQuery("Filijala.findAll",Filijala.class);
        ArrayList<Filijala> fl = new ArrayList<Filijala>(query2.getResultList());
        
        for (Filijala f : fl){
            promene.dodajFil(f);
        }
        
        TypedQuery<Komitent> query3 = em.createNamedQuery("Komitent.findAll",Komitent.class);
        ArrayList<Komitent> kl = new ArrayList<Komitent>(query3.getResultList());
        
        for (Komitent k : kl){
            promene.dodajKom(k);
        }
        
        return promene;
    }
    
    
}
