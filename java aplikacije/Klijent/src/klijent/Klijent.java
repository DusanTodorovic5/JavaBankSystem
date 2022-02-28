/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author virtuel
 */
public class Klijent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(dohvSve());
        Okvir o = new Okvir();
        o.set();
        o.setVisible(true);
    }
    
    static String kreirajMesto(String naziv, String postanskiBroj){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/kreirajMesto/" + postanskiBroj + "/" + naziv;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                System.out.println(inputString);
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    static String kreirajFilijala(String naziv, String adresa, int mestoId){
        String povr = "Greska pri povezivanju";
        try{//kreirajFilijalu/{naziv}/{adresa}/{mestoId}
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/kreirajFilijalu/" + naziv + "/" + adresa + "/" + mestoId;
            url = url.replace(" ", "%20");
            System.out.println("1");
            URL uri = new URL(url);
            System.out.println("1");
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            System.out.println("1");
            conn.setRequestMethod("POST");
            System.out.println("1");
            conn.setDoInput(true);
            System.out.println("1");
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode + "  rr");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("1");
            String inputString = null;
            System.out.println("1");
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            System.out.println("GRESKA");
        }
        return povr;
    }
    //kreirajKomitenta/{naziv}/{adresa}/{mestoId}
    static String kreirajKomitenta(String naziv, String adresa, int mestoId){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/kreirajKomitenta/" + naziv + "/" + adresa + "/" + mestoId;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    //promeniSediste/{idKom}/{adresa}/{idMesto}
    static String promeniSediste(int idKom, String adresa, int mestoId){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/promeniSediste/" + idKom + "/" + adresa + "/" + mestoId;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    //mesta
    static String dohvatiMesta(){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/mesta";
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    //filijale
    static String dohvatiFilijale(){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/filijale";
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    static String dohvatiKomitente(){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem1/komitenti";
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    
    //otvoriRacun/{dozvMinus}/{idMesto}/{idKom}
    static String otvoriRacun(int dozvMinus, int mestoId, int idKom){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/otvoriRacun/" + dozvMinus + "/" + mestoId + "/" + idKom;
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //zatvoriRacun/{idRac}
    static String zatvoriRacun(int idRac){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/zatvoriRacun/" + idRac;
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //kreirajPrenos(int iznos, String svrha, int idRac, int saRacun)
    static String kreirajPrenos(int iznos, String svrha, int idRac, int saRacun){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/kreirajPrenos/" + iznos + "/" + svrha + "/" + idRac + "/" + saRacun;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //kreirajUplata/{iznos}/{svrha}/{idRac}/{idFil}
    static String kreirajUplatu(int iznos, String svrha, int idRac, int idFil){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/kreirajUplata/" + iznos + "/" + svrha + "/" + idRac + "/" + idFil;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //kreirajIsplata/{iznos}/{svrha}/{idRac}/{idFil}
    static String kreirajIsplatu(int iznos, String svrha, int idRac, int idFil){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/kreirajIsplata/" + iznos + "/" + svrha + "/" + idRac + "/" + idFil;
            url = url.replace(" ", "%20");
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //racun/{idKom}
    static String dohvRacun(int idKom){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/racun/" + idKom;
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //transakcije/{idRac}
    static String dohvTransakciju(int idRac){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem2/transakcije/" + idRac;
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }//dohvKomitenta
    
    //podsistem3
    static String dohvSve(){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem3";
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
    
    //razlike
    static String dohvRazlike(){
        String povr = "Greska pri povezivanju";
        try{
            String url = "http://localhost:8080/CentralniServer/banka/podsistem3/razlike";
            URL uri = new URL(url);
            
            HttpURLConnection conn = (HttpURLConnection)uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            int responseCode = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputString = null;
            povr = "";
            while ((inputString = in.readLine()) != null){
                povr = povr + inputString + "\n";
            }
        }
        catch (Exception e){
            
        }
        return povr;
    }
}
