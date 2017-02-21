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
- [ ] Realistinen kimpoaminen esteistä

####Luokkakaavio
![Luokkakaavio](Pong_refaktoroinnin_jalkeen_esteilla.png)

####Sekvenssikaavio pelitypin valitsemisesta
![Sekvenssikaavio pelityypin valitsemisesta](sekvenssikaavio_pelinvalinta.png)

####Sekvenssikaavio pausettamisesta
![Sekvenssikaavio pelin pausettamisesta](sekvenssikaavio_pausettaminen.png)
