/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author virtuel
 */
public class Okvir extends JFrame{
    
    JPanel panel = new JPanel();
    Button[] dugmad = new Button[16];
    JPanel glavni = new JPanel();
    
    PanelGenerator pg = new PanelGenerator();
    
    public Okvir(){
        super("Projekat iz IS1");
	
    }
    public void set(){
        this.setBounds(new Rectangle(100, 100, 700, 500));
        this.setLayout(new BorderLayout());
        JPanel panelZaDugmad = new JPanel();
        panelZaDugmad.setLayout(new BoxLayout(panelZaDugmad,BoxLayout.Y_AXIS));
        JPanel gornji = new JPanel(new FlowLayout());
        JPanel srednji = new JPanel(new FlowLayout());
        JPanel donji = new JPanel(new FlowLayout());
        for (int i=0;i<dugmad.length;i++){
            dugmad[i] = new Button();
        }
        dugmad[0].setLabel("1. Kreiraj mesto");
        dugmad[0].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreiranjeMesta(),BorderLayout.CENTER);
            rev();
        });
        dugmad[1].setLabel("2. Kreiraj filijalu");
        dugmad[1].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreirajFilijalu(),BorderLayout.CENTER);
            rev();
        });
        dugmad[2].setLabel("3. Kreiraj komitenta");
        dugmad[2].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreirajKomitenta(),BorderLayout.CENTER);
            rev();
        });
        dugmad[3].setLabel("4. Promeni Sediste");
        dugmad[3].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.promeniSediste(),BorderLayout.CENTER);
            rev();
        });
        dugmad[4].setLabel("5. Otvori Racun");
        dugmad[4].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.otvoriRacun(),BorderLayout.CENTER);
            rev();
        });
        dugmad[5].setLabel("6. Zatvori Racun");
        dugmad[5].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.zatvoriRacun(),BorderLayout.CENTER);
            rev();
        });
        dugmad[6].setLabel("7. Kreiraj Prenos");
        dugmad[6].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreirajPrenos(),BorderLayout.CENTER);
            rev();
        });
        dugmad[7].setLabel("8. Kreiraj Uplatu");
        dugmad[7].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreirajUplatu(),BorderLayout.CENTER);
            rev();
        });
        dugmad[8].setLabel("9. Kreiraj Isplatu");
        dugmad[8].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.kreirajIsplatu(),BorderLayout.CENTER);
            rev();
        });
        dugmad[9].setLabel("10. Dohvati sva mesta");
        dugmad[9].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvMesta(),BorderLayout.CENTER);
            rev();
        });
        dugmad[10].setLabel("11. Dohvati sve filijale");
        dugmad[10].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvFilijale(),BorderLayout.CENTER);
            rev();
        });
        dugmad[11].setLabel("12. Dohvati sve komitente");
        dugmad[11].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvKomitente(),BorderLayout.CENTER);
            rev();
        });
        dugmad[12].setLabel("13. Dohvati racune");
        dugmad[12].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvRacun(),BorderLayout.CENTER);
            rev();
        });
        dugmad[13].setLabel("14. Dohvati transakcije");
        dugmad[13].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvTrans(),BorderLayout.CENTER);
            rev();
        });
        dugmad[14].setLabel("15. Dohvati backup");
        dugmad[14].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvSve(),BorderLayout.CENTER);
            rev();
        });
        dugmad[15].setLabel("16. Dohvati razliku");
        dugmad[15].addActionListener((ae)->{
            panel.removeAll();
            panel.add(pg.dohvRazliku(),BorderLayout.CENTER);
            rev();
        });
        for (int i=0;i<6;i++){
            gornji.add(dugmad[i]);
        }
        for (int i=6;i<12;i++){
            srednji.add(dugmad[i]);
        }
        for (int i=12;i<16;i++){
            donji.add(dugmad[i]);
        }
        panelZaDugmad.add(gornji);
        panelZaDugmad.add(srednji);
        panelZaDugmad.add(donji);
        this.add(panelZaDugmad,BorderLayout.NORTH);
        this.add(panel,BorderLayout.CENTER);
        //this.add(pg.dohvRazliku(),BorderLayout.CENTER);
        
        this.revalidate();
    }
    
    public void rev(){
        revalidate();
        repaint();
    }
}
