package entiteti;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T15:25:40")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Date> datum;
    public static volatile SingularAttribute<Transakcija, Integer> iznos;
    public static volatile SingularAttribute<Transakcija, String> svrha;
    public static volatile SingularAttribute<Transakcija, Date> vreme;
    public static volatile SingularAttribute<Transakcija, Integer> idRacun;
    public static volatile SingularAttribute<Transakcija, String> tipTrans;
    public static volatile SingularAttribute<Transakcija, Integer> idFil;
    public static volatile SingularAttribute<Transakcija, Integer> idTransakcije;
    public static volatile SingularAttribute<Transakcija, Integer> naRacun;
    public static volatile SingularAttribute<Transakcija, Integer> rbRacuna;

}