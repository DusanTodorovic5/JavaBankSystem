package entiteti;

import entiteti.Komitent;
import entiteti.Transakcija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-01T12:41:27")
@StaticMetamodel(Racun.class)
public class Racun_ { 

    public static volatile SingularAttribute<Racun, Date> datum;
    public static volatile SingularAttribute<Racun, Integer> brojTransakcija;
    public static volatile SingularAttribute<Racun, Integer> dozvoljenMinus;
    public static volatile SingularAttribute<Racun, Integer> stanje;
    public static volatile SingularAttribute<Racun, Date> vreme;
    public static volatile SingularAttribute<Racun, Komitent> idKomitent;
    public static volatile SingularAttribute<Racun, Integer> mesto;
    public static volatile ListAttribute<Racun, Transakcija> transakcijaList;
    public static volatile ListAttribute<Racun, Transakcija> transakcijaList1;
    public static volatile SingularAttribute<Racun, Integer> brojRacuna;
    public static volatile SingularAttribute<Racun, String> status;

}