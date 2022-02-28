/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klijent;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author virtuel
 */
public class PanelGenerator {
    
    
    public JPanel kreiranjeMesta(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Postanski broj: "));
        panel1.add(pb);
        panel1.add(new Label(" Naziv: "));
        panel1.add(naziv);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajMesto(naziv.getText(),pb.getText());
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel kreirajFilijalu(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Adresa: "));
        panel1.add(pb);
        panel1.add(new Label(" Naziv: "));
        panel1.add(naziv);
        panel1.add(new Label(" Mesto: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajFilijala(naziv.getText(), pb.getText(), Integer.parseInt(mesto.getText()));
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel kreirajKomitenta(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Adresa: "));
        panel1.add(pb);
        panel1.add(new Label(" Naziv: "));
        panel1.add(naziv);
        panel1.add(new Label(" Mesto: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajKomitenta(naziv.getText(), pb.getText(), Integer.parseInt(mesto.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel promeniSediste(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Adresa: "));
        panel1.add(pb);
        panel1.add(new Label(" Mesto: "));
        panel1.add(naziv);
        panel1.add(new Label(" Komitent: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.promeniSediste(Integer.parseInt(mesto.getText()), pb.getText(), Integer.parseInt(naziv.getText()));
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel otvoriRacun(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" dozvMinus: "));
        panel1.add(pb);
        panel1.add(new Label(" Mesto: "));
        panel1.add(naziv);
        panel1.add(new Label(" Komitent: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.otvoriRacun(Integer.parseInt(pb.getText()), Integer.parseInt(naziv.getText()), Integer.parseInt(mesto.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel zatvoriRacun(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Racun: "));
        panel1.add(pb);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.zatvoriRacun(Integer.parseInt(pb.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel kreirajPrenos(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Iznos: "));
        panel1.add(pb);
        panel1.add(new Label(" Svrha: "));
        panel1.add(naziv);
        panel1.add(new Label(" Sa racuna: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        panel1.add(new Label(" Na racun: "));
        TextField naR = new TextField();
        naR.setPreferredSize(new Dimension(100,20));
        panel1.add(naR);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajPrenos(Integer.parseInt(pb.getText()), naziv.getText(), Integer.parseInt(mesto.getText()), Integer.parseInt(naR.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel kreirajUplatu(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Iznos: "));
        panel1.add(pb);
        panel1.add(new Label(" Svrha: "));
        panel1.add(naziv);
        panel1.add(new Label(" Sa racuna: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        panel1.add(new Label(" Filijala: "));
        TextField naR = new TextField();
        naR.setPreferredSize(new Dimension(100,20));
        panel1.add(naR);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajUplatu(Integer.parseInt(pb.getText()), naziv.getText(), Integer.parseInt(mesto.getText()), Integer.parseInt(naR.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel kreirajIsplatu(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        TextField naziv = new TextField();
        naziv.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Iznos: "));
        panel1.add(pb);
        panel1.add(new Label(" Svrha: "));
        panel1.add(naziv);
        panel1.add(new Label(" Sa racuna: "));
        TextField mesto = new TextField();
        mesto.setPreferredSize(new Dimension(100,20));
        panel1.add(mesto);
        panel1.add(new Label(" Filijala: "));
        TextField naR = new TextField();
        naR.setPreferredSize(new Dimension(100,20));
        panel1.add(naR);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.kreirajIsplatu(Integer.parseInt(pb.getText()), naziv.getText(), Integer.parseInt(mesto.getText()), Integer.parseInt(naR.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvMesta(){
        JPanel panel = new JPanel(new BorderLayout());
       
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
            String s = Klijent.dohvatiMesta();
            s = s.replace("\"", "");
            s = s.replace("]", "");
            s = s.replace("[", "");
            s = s.replace(";", "  ");
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);

        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvFilijale(){
        JPanel panel = new JPanel(new BorderLayout());
       
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
            String s = Klijent.dohvatiFilijale();
            s = s.replace("\"", "");
            s = s.replace("]", "");
            s = s.replace("[", "");
            s = s.replace(";", "  ");
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);

        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvKomitente(){
        JPanel panel = new JPanel(new BorderLayout());
       
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
            String s = Klijent.dohvatiKomitente();
            s = s.replace("\"", "");
            s = s.replace("]", "");
            s = s.replace("[", "");
            s = s.replace(";", "  ");
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);

        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvRacun(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Komitent: "));
        panel1.add(pb);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.dohvRacun(Integer.parseInt(pb.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvTrans(){
        JPanel panel = new JPanel(new BorderLayout());
        
        TextField pb = new TextField();
        pb.setPreferredSize(new Dimension(100,20));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(new Label(" Racun: "));
        panel1.add(pb);
        Button b = new Button("Dohvati");
        panel1.add(b);
        panel.add(panel1,BorderLayout.NORTH);
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        b.addActionListener((ae) ->{
            String s = Klijent.dohvTransakciju(Integer.parseInt(pb.getText()));
            
            String t = "";
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
            tekst.setText(t);
        });
        
        panel.add(tekst,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvSve(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        String sv = Klijent.dohvSve();
        String[] sp = sv.split("\\],\\[");
        String t = "";
        int i = 0;
        for (String s : sp){
            s = s.replace("\"", "");
            s = s.replace("]", "");
            s = s.replace("[", "");
            s = s.replace(";", "  ");
            switch (i){
                case 0:
                    t = t + "###   MESTA   ###\n";
                    break;
                case 1:
                    t = t + "###   KOMITENTI   ###\n";
                    break;
                case 2:
                    t = t + "###   FILIJALE   ###\n";
                    break;
                case 3:
                    t = t + "###   RACUNI   ###\n";
                    break;
                case 4:
                    t = t + "###   TRANSAKCIJE   ###\n";
                    break;
            }
            i++;
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
        }
        
        tekst.setText(t);
        JScrollPane sc = new JScrollPane(tekst);
        panel.add(sc,BorderLayout.CENTER);
        
        return panel;
    }
    public JPanel dohvRazliku(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JTextArea tekst = new JTextArea();
        tekst.setEditable(false);
        
        String sv = Klijent.dohvRazlike();
        String[] sp = sv.split("\\],\\[");
        String t = "";
        int i = 0;
        for (String s : sp){
            s = s.replace("\"", "");
            s = s.replace("]", "");
            s = s.replace("[", "");
            s = s.replace(";", "  ");
            switch (i){
                case 0:
                    t = t + "###   MESTA   ###\n";
                    break;
                case 1:
                    t = t + "###   KOMITENTI   ###\n";
                    break;
                case 2:
                    t = t + "###   FILIJALE   ###\n";
                    break;
                case 3:
                    t = t + "###   RACUNI   ###\n";
                    break;
                case 4:
                    t = t + "###   TRANSAKCIJE   ###\n";
                    break;
            }
            i++;
            String[] spl = s.split(",");
            for (String ss : spl){
                t = t + ss + "\n";
            }
        }
        
        tekst.setText(t);
        JScrollPane sc = new JScrollPane(tekst);
        panel.add(sc,BorderLayout.CENTER);
        
        return panel;
    }
}
