package entiteti;

import entiteti.Mesto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-02T15:25:40")
@StaticMetamodel(Komitent.class)
public class Komitent_ { 

    public static volatile SingularAttribute<Komitent, Integer> idKomitent;
    public static volatile SingularAttribute<Komitent, String> naziv;
    public static volatile SingularAttribute<Komitent, String> adresa;
    public static volatile SingularAttribute<Komitent, Mesto> mesto;

}