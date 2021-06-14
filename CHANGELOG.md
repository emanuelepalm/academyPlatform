**Versione 1.1**
- Creazione getProperties
- Creazione gestione Driver
- Aggiunta GlobalParameters aggiornato
- Creazione 3 Test Class  

`Test Web`:  
-Simulazioni tasti navigazione browser  
    -Test Window Browser  
    \-Test Screenshot   
    \-Test Geolocalizzazione  
    \-Test colore  
    -Creazione `WebSteps` metodi ricerca e chiudi banner  

`Test Ebay`       
 \-Controllare che esista il prodotto ricercato  
 \-Controllo sulle categorie  
-Creazione `EbaySteps` metodi ricerca e chiudi banner  

`Test il meteo`  
 \-Controllare che esista la città ricercata   
 \-Controllare che il titolo e il nome della scheda nel menu combacino  
 \-Creazione `IlMeteoSteps` metodi ricerca, chiudi banner e getMenuTabs

`Test Amazon`    
 \-Creazione `AmazonSteps` metodi ricerca, chiudi banner e getMenuTabs


`Test Mobile`  
 \-Test sulla login corretta e due fallimentari   
 \-Test sull'aggiunta di un utente  
 \-Test sull'eliminazione degli utenti    
 \-Creazione `MobileSteps` inserisci user, password e nome  
 
`ToolBox`  Contenente tutte le utilities  
 \- _Utils_ gestisce le properties  
 \- _Screen_ gestisce gli screenshot  
 \- _StringStylist_ abbellisce i nomi dei metodi   


 - Introdotto il template per gli steps mobile
 - Le classi steps hanno la possibilità di prendere il nome del metodo che viene lanciato per riportarlo nel report
 


- Gestione delle properties in file separati (2 per classe se web, uno web classico e uno web mobile)


