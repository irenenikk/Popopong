### Aihemäärittely

Tarkoituksena on koodata klassinen [Pong-peli](https://en.wikipedia.org/wiki/Pong).
Pelaaja voi valita pelaako hän tekoälyä vai kaveria vastaan.

TODO:
- [X] Suunta-enum
- [X] Järkevä tietorakenne pelaajille
- [X] Aloitus ja ohjeet
- [X] Lopetus ja uudelleen aloittaminen
- [X] Pausetus
- [X] Tekoäly
- [X] Pelityylin valinta
- [ ] Esteet
- [X] Pisteet näytölle
- [ ] Pallo kimpoaa kiinnostavasti/ei mene mailan läpi jos lyödään ylä- tai alapäällä
- [X] Pallo nopeutuu pelin edetessä
- [X] Pallo odottaa hetken maalin jälkeen

####Luokkakaavio
![Luokkakaavio](Pong_refaktoroinnin_jalkeen.png)

####Sekvenssikaavio pelitypin valitsemisesta
![Sekvenssikaavio pelityypin valitsemisesta](sekvenssikaavio_pelinvalinta.png)

####Sekvenssikaavio pausettamisesta
![Sekvenssikaavio pelin pausettamisesta](sekvenssikaavio_pausettaminen.png)
