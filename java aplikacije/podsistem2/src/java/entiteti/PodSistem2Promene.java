/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author virtuel
 */
public class PodSistem2Promene implements Serializable{
    public ArrayList<RacunZas> racuni = new ArrayList<>();
    public ArrayList<TransZas> transakcije = new ArrayList<>();
    
    public void dodajRacun(Racun r){
        racuni.add(new RacunZas(r));
    }
    
    public void dodajTrans(Transakcija t){
        transakcije.add(new TransZas(t));
    }
    
    
    public class RacunZas implements Serializable{
        public int brR;
        public Integer stanje;
        public Integer dozvoljen;
        public String status;
        public java.util.Date datum;
        public java.util.Date vreme;
        public int brTr;
        public int mesto;
        public int komit;
        
        public RacunZas(Racun r){
            brR = r.getBrojRacuna();
            stanje = r.getStanje();
            dozvoljen = r.getDozvoljenMinus();
            status = r.getStatus();
            datum = r.getDatum();
            vreme = r.getVreme();
            brTr = r.getBrojTransakcija();
            mesto = r.getMesto();
            komit = r.getIdKomitent().getIdKomitent();
        }
    }
    
    public class TransZas implements Serializable{
        
        public int idTr;
        public java.util.Date datum;
        public java.util.Date vreme;
        public int iznos;
        public int rbRacun;
        public String svrha;
        public int idRac;
        public String tip;
        public Integer naRacun;
        public Integer idFil;
        
        public TransZas(Transakcija t){
            idTr = t.getIdTransakcije();
            datum = t.getDatum();
            vreme = t.getVreme();
            iznos = t.getIznos();
            rbRacun = t.getRbRacuna();
            svrha = t.getSvrha();
            idRac = t.getIdRacun().getBrojRacuna();
            tip = t.getTipTrans();
            if (tip == "P"){
                naRacun = t.getRacun().getBrojRacuna();
                idFil = null;
            }
            else{
                idFil = t.getIdFil();
                naRacun = null;
            }
        }
    }
    
}
