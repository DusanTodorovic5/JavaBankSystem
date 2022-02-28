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
public class PodSistem1Promene implements Serializable {
    public ArrayList<FilijalaZas> filijale = new ArrayList<>();
    public ArrayList<KomZas> komitenti = new ArrayList<>();
    public ArrayList<MestoZas> mesta = new ArrayList<>();
    
    public void dodajFil(Filijala f){
        filijale.add(new FilijalaZas(f));
    }
    
    public void dodajKom(Komitent k){
        komitenti.add(new KomZas(k));
    }
    
    public void dodajMesto(Mesto m){
        mesta.add(new MestoZas(m));
    }
    
    public class MestoZas implements Serializable{
        public int id;
        public String naziv;
        public String broj;
        public MestoZas(Mesto m){
            id = m.getIdMesto();
            naziv = m.getNaziv();
            broj = m.getPostanskiBroj();
        }
    }
    
    public class FilijalaZas implements Serializable{
        public int id;
        public String naziv;
        public String adresa;
        public int mesto;
        
        public FilijalaZas(Filijala f){
            id = f.getIdFilijala();
            naziv = f.getNaziv();
            adresa = f.getAdresa();
            mesto = f.getMestoFil().getIdMesto();
        }
    }
    
    public class KomZas implements Serializable{
        public int id;
        public String naziv;
        public String adresa;
        public int mesto;
        public KomZas(Komitent k){
            id = k.getIdKomitent();
            naziv = k.getNaziv();
            adresa = k.getAdresa();
            mesto = k.getMesto().getIdMesto();
        }
    }
    
}
