# JavaBankSystem
Bank system made in java with 4 servers and client app

## Central Server

Central server features server for receiving and sending http request

 - POST: podsistem1/kreirajMesto/broj/naziv -> Creates location in database with given zip code(broj) and name (naziv)
 - POST: podsistem1/kreirajFilijalu/naziv/adresa/mestoId -> Creates branch office with given name(naziv), address(adresa) and location's id(mestoId)
 - POST: podsistem1/kreirajKomitenta/naziv/adresa/mestoId -> Creates client with given name(naziv), address(adresa) and location's id(mestoId)
 - POST: podsistem1/promeniSediste/idKom/adresa/idMesto -> Changes clients location with given id(idKom), address(adresa) and location's id(idMesto)
 - POST: podsistem2/otvoriRacun/dozvMinus/idMesto/idKom -> Creates new account with given allowed minus(dozvMinus), location(idMesto) and client account(idKom)
 - POST: podsistem2/zatvoriRacun/idRac -> Closes account for given id(idRac)
 - POST: podsistem2/kreirajPrenos/iznos/svrha/idRac/naRacun -> Creates transaction with given amount(iznos), description(svrha) and accounts(idRac, naRacun)
 - POST: podsistem2/kreirajUplata/iznos/svrha/idRac/idFil -> Creates payment with given amount(iznos), description(svrha), account(idRac) and branch office(idFil)
 - POST: podsistem2/kreirajIsplata/iznos/svrha/idRac/idFil -> Creates payout with given amount(iznos), description(svrha), account(idRac) and branch office(idFil)
 - GET: podsistem1/mesta -> Returns all locations in database
 - GET: podsistem1/filijale -> Returns all branch offices in database
 - GET: podsistem1/komitenti -> Returns all clients in database
 - GET: podsistem2/racun/idKom -> Returns all accounts for given client(idKom)
 - GET: podsistem2/transakcije/idRac -> Returns all transactions, payments and payouts for given account(idRac)
 - GET: podsistem3 -> Returns all data in third subsystem
 - GET: podsistem3/razlike -> Returns difference between third subsystem and other two subsystems


## Subsystem 1

This subsystem stores only data for locations, branch offices and clients. It communicates only with central server, second subsystem and third subsystem using Java Message Service. It queries request from central server. It is made with Maven and Java as web application.

## Subsystem 2

This subsystem stores only data for accounts, transactions, payments and payouts, but it also stores surogate values of clients needed for account querries. It communicates only with central server, first subsystem and third subsystem using Java Message Service. It queries request from central server. It is made with Ant and Java as enterprise client application.

## Subsystem 3

This subsystem is basically backup system. It communicates with central system only for last 2 http requests but every 2 minutes this system sends Java Message Service message to subsystems 1 and 2 requesting all the data. It is made with Ant and Java as enterprise client application.

## Client application

Client application is only demonstrative, it has 18 different tabs each representing one of the requests. It is made with Java using Swing as GUI framework. 
