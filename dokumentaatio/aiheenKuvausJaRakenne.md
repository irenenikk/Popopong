### Aihemäärittely

Tarkoituksena on koodata klassinen [Pong-peli](https://en.wikipedia.org/wiki/Pong).
Pelaaja voi valita pelaako hän tekoälyä vai kaveria vastaan. Pallon liike nopeutuu pelin edetessä, ja kentälle ilmestyy esteitä. Esteet varoittavat ilmestymisestään harmaalla värillä.

TODO:
- [X] Suunta-enum
- [X] Järkevä tietorakenne pelaajille
- [X] Aloitus ja ohjeet
- [X] Lopetus ja uudelleen aloittaminen
- [X] Pausetus
- [X] Tekoäly
- [X] Pelityylin valinta
- [X] Esteet
- [X] Pisteet näytölle
- [X] Pallo ei mene mailan läpi jos lyödään ylä- tai alapäällä
- [X] Pallo nopeutuu pelin edetessä
- [X] Pallo odottaa hetken maalin jälkeen
- [X] Esteiden hallinta
- [X] Realistinen kimpoaminen esteistä

####Rakennekuvaus
Pelin loogiset pääkomponentit ovat Pong-, Kentta- ja KomponenttiHallintaluokat. Pong pitää kirjaa pelin tilasta, kenttä luo ja säilyttää pelin komponentteja, eli palloa, mailaa ja esteitä, ja komponenttihallinta hallinnoi näiden osasten liikettä kentällä, esimerkiksi tarkistaen, onko aika luoda uusi este, ja tuleeko komponentteja liikuttaa. Pallon törmäilemisten käsittely on siirretty oman luokan vastuulle, samoin mailojen kentän ulkopuolelle menemisen estäminen. Pisteistä pidetään kirjaa Tilasto-luokan avulla. Logiikka kommunikoi käyttöliittymän kanssa Paivitettava-rajapinnan kautta.

####Luokkakaavio
![Luokkakaavio](Pong_vko6.png)

####Sekvenssikaavio pelitypin valitsemisesta
![Sekvenssikaavio pelityypin valitsemisesta](sekvenssikaavio_pelinvalinta.png)

####Sekvenssikaavio pausettamisesta
![Sekvenssikaavio pelin pausettamisesta](sekvenssikaavio_pausettaminen.png)
