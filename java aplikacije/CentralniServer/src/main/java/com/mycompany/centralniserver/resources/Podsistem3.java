/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.centralniserver.resources;

import java.util.ArrayList;
import java.util.List;
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
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Path("podsistem3")
public class Podsistem3 {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem3_sendQueue")
    private Queue queueZaZahtev;
    
    @Resource(lookup = "podsistem3_recieveQueue")
    private Queue queueOdgovor;
    
    @Resource(lookup = "podsistem2_sendQueue")
    private Queue queueZaZahtev2;
    
    @Resource(lookup = "podsistem2_recieveQueue")
    private Queue queueOdgovor2;
    
    @Resource(lookup = "podsistem1_sendQueue")
    private Queue queueZaZahtev1;
    
    @Resource(lookup = "podsistem1_recieveQueue")
    private Queue queueOdgovor1;
    
    
    
    @GET
    public Response sve(){
        
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<ArrayList<String>> odgovori = new ArrayList<ArrayList<String>>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 15);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<ArrayList<String>>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        for (ArrayList<String> lista : odgovori){
            System.out.println("################");
            for (String s : lista){
                System.out.println(s);
           }
       }
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    @GET
    @Path("razlike")
    public Response razlike(){
        
       Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<ArrayList<String>> odgovori = new ArrayList<ArrayList<String>>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 16);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<ArrayList<String>>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
}

