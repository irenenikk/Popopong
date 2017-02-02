### Aihemäärittely

Tarkoituksena on koodata klassinen [Pong-peli](https://en.wikipedia.org/wiki/Pong).
Pelaajalla on valinta pelattavasta versiosta: voittaa voi joko saavuttamalla tietyn pistemäärän ensin, saamalla enemmän pisteitä aikarajassa, tai pelaajan missatessa pallon kerran. Toinen laajennusmahdollisuus on, että pelaaja voi valita, haluaako hän pelata tekoälyä vai kaveria vastaan. Tekoälystä voi tehdä tyhmempiä ja älykkäämpiä versioita.

Ideoita pelin vaikeuttamiseksi:
- "Tennisverkon" tuntumaan alkaa ilmestyä neliöitä, jotka blokkaavat pallon liikettä. Blokit voivat varoittaa ilmestymisestään.
- "Ilkeä pallo": kun tarpeeksi onnistuneita osumia peräkkäin, pallo vaihtaa varoittamatta suuntaa
- Varoittamatta ilmestyvä este keskelle kenttää

TODO:
- [ ] Suunta-enum
- [ ] Järkevä tietorakenne pelaajille
- [ ] Pelityylin valinta
- [ ] Esteet
- [ ] Pisteet näytölle

![Luokkakaavio](luokkakaavio_tarkempi.png)
