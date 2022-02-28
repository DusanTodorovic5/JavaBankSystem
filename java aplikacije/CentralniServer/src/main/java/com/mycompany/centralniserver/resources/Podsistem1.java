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

/**
 *
 * @author 
 */
@Path("podsistem1")
public class Podsistem1 {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem1_sendQueue")
    private Queue queueZaZahtev;
    
    @Resource(lookup = "podsistem1_recieveQueue")
    private Queue queueOdgovor;
    
    @Resource(lookup = "podsistem2_recieveQueue")
    private Queue queueOdgovor2;
    
    @POST
    @Path("kreirajMesto/{broj}/{naziv}")
    public Response kreirajMesto(@PathParam("broj")String postanski_broj, @PathParam("naziv")String naziv){
        
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                argumenti.add(postanski_broj);
                argumenti.add(naziv);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 1);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    
    @POST
    @Path("kreirajFilijalu/{naziv}/{adresa}/{mestoId}")
    public Response kreirajFilijalu(@PathParam("naziv")String naziv, @PathParam("adresa")String adresa,  @PathParam("mestoId")String mesto){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                ArrayList<String> argumenti = new ArrayList<String>();
                argumenti.add(naziv);
                argumenti.add(adresa);
                argumenti.add(mesto);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 2);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
                
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
        
    }
    
    @POST
    @Path("kreirajKomitenta/{naziv}/{adresa}/{mestoId}")
    public Response kreirajKomitenta(@PathParam("naziv")String naziv, @PathParam("adresa")String adresa,  @PathParam("mestoId")String mesto){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        Destination destination2 = queueOdgovor2;
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                ArrayList<String> argumenti = new ArrayList<String>();
                argumenti.add(naziv);
                argumenti.add(adresa);
                argumenti.add(mesto);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 3);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
                if ((odgovori.get(0).split(" ")[0]).equals("Uspesno")){
                    producer.send(destination2, objMessage);
                }
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    
    @POST
    @Path("promeniSediste/{idKom}/{adresa}/{idMesto}")
    public Response promeniSediste(@PathParam("idKom")String komitent, @PathParam("adresa")String adresa,  @PathParam("idMesto")String mesto){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                argumenti.add(komitent);
                argumenti.add(adresa);
                argumenti.add(mesto);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 4);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    @GET
    @Path("mesta")
    public Response svaMesta(){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 10);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    @GET
    @Path("filijale")
    public Response sveFilijale(){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 11);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    @GET
    @Path("komitenti")
    public Response sviKomitenti(){
        Destination destinationSlanje = queueZaZahtev;
        Destination destinationPrima = queueOdgovor;
        
        JMSContext context = connectionFactory.createContext();
        JMSConsumer consumer = context.createConsumer(destinationSlanje);
        JMSProducer producer = context.createProducer();
        ArrayList<String> odgovori = new ArrayList<String>();
        try {
                ObjectMessage objMessage = context.createObjectMessage();
                //objMessage.setText(postanski_broj + ";" + naziv);
                ArrayList<String> argumenti = new ArrayList<String>();
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 12);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
}