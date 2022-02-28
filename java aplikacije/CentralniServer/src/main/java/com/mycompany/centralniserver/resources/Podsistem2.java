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

@Path("podsistem2")
public class Podsistem2 {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "podsistem2_sendQueue")
    private Queue queueZaZahtev;
    
    @Resource(lookup = "podsistem2_recieveQueue")
    private Queue queueOdgovor;
   // case 5://otvoriRacun(int dozvMinus, int idMesto, int idKom)
   //     odgovori.add(otvoriRacun(Integer.parseInt(argumenti.get(0)),Integer.parseInt(argumenti.get(1)),Integer.parseInt(argumenti.get(2))));
    @POST
    @Path("otvoriRacun/{dozvMinus}/{idMesto}/{idKom}")
    public Response otvoriRacun(@PathParam("dozvMinus")String dozvMinus, @PathParam("idMesto")String idMesto,@PathParam("idKom")String idKom){
        
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
                argumenti.add(dozvMinus);
                argumenti.add(idMesto);
                argumenti.add(idKom);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 5);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    //case 6://zatvoriRacun(int idRac)
    //    odgovori.add(zatvoriRacun(Integer.parseInt(argumenti.get(0))));
    @POST
    @Path("zatvoriRacun/{idRac}")
    public Response zatvoriRacun(@PathParam("idRac") String idRac){
        
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
                argumenti.add(idRac);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 6);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    //case 7://kreirajPrenos(int iznos, String svrha, int idRac, int saRacun)
    //    odgovori.add(kreirajPrenos(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2)), Integer.parseInt(argumenti.get(3))));
    @POST
    @Path("kreirajPrenos/{iznos}/{svrha}/{idRac}/{naRacun}")
    public Response kreirajPrenos(@PathParam("iznos")String iznos, @PathParam("svrha")String svrha,@PathParam("idRac")String idRac, @PathParam("naRacun")String naRacun){
        
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
                argumenti.add(iznos);
                argumenti.add(svrha);
                argumenti.add(idRac);
                argumenti.add(naRacun);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 7);
                producer.send(destinationPrima, objMessage);
                
                Message message = consumer.receive();
                odgovori = (ArrayList<String>)((ObjectMessage)message).getObject();
          
        } catch (JMSException ex) {
            System.out.println("Greska");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
       return Response.status(Response.Status.OK).entity(odgovori).build();
    }
    //case 8://kreirajUplata(int iznos, String svrha, int idRac, int idFil
    //    odgovori.add(kreirajUplata(Integer.parseInt(argumenti.get(0)), argumenti.get(1), Integer.parseInt(argumenti.get(2)), Integer.parseInt(argumenti.get(3))));
    @POST
    @Path("kreirajUplata/{iznos}/{svrha}/{idRac}/{idFil}")
    public Response kreirajUplata(@PathParam("iznos")String iznos, @PathParam("svrha")String svrha,@PathParam("idRac")String idRac, @PathParam("idFil") String idFil){
        
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
                argumenti.add(iznos);
                argumenti.add(svrha);
                argumenti.add(idRac);
                argumenti.add(idFil);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 8);
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
    @Path("kreirajIsplata/{iznos}/{svrha}/{idRac}/{idFil}")
    public Response kreirajIsplata(@PathParam("iznos")String iznos, @PathParam("svrha")String svrha,@PathParam("idRac")String idRac, @PathParam("idFil") String idFil){
        
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
                argumenti.add(iznos);
                argumenti.add(svrha);
                argumenti.add(idRac);
                argumenti.add(idFil);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 9);
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
    @Path("racun/{idKom}")
    public Response racuni(@PathParam("idKom") String idKom){
        
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
                argumenti.add(idKom);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 13);
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
    @Path("transakcije/{idRac}")
    public Response transakcije(@PathParam("idRac") String idRac){
        
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
                argumenti.add(idRac);
                objMessage.setObject(argumenti);
                objMessage.setIntProperty("tip", 14);
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
