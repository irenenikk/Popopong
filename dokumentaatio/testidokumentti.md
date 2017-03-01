## Testidokumentti

Testien rivikattavuus on noin 90%, ja mutaatiokattavuus noin 70%.
Automaattinen testaaminen keskittyy lähinnä pallon liikkeiden, ja pelin
tilamuutosten, kuten maalien ja esteiden luomisen estoihin ja rajatapauksiin.

### Asioita joita en testannut automaattisesti:
- _Pallon osuminen mailan ylä- tai alapäähän._ Tämä osoittautui yllättävän haastavaksi,
ja myönnänkin, etten saanut näitä testejä mitenkään toimimaan.
Testien epäonnistuminen johtuu luultavasti siitä, että kulmaan se kimpoaa sivuille
vertikaalisesti kimpoamisen sijaan, mikä johtuu törmäysten toteuttamisesta Rectangle-olioiden avulla.
- _Toisen luokan kutsuminen._ Integraatiotestaus sujuu mielestäni luontevimmin järjestelmätestauksen kautta.
Peliä käynnistäessä voidaan esimerkiksi varmistua siitä, että päälogiikkaluokka Pong kutsuu rajapinnan Paivitettava
toteuttavaa käyttoliittymäluokkaa, sillä muuten käyttöliittymän tila ei muuttuis ollenkaan, ja
voidaan vakuuttua siitä, että KomponenttiHallinta-luokka kutsuu pallon törmäykset tarkistavaa luokkaa, jos
pallo kimpoaa mailoista ja reunoista. Olen kuitenkin käyttänyt
integraatiotestausta esimerkiksi tekoälyn käyttäytymistä testatessa, sillä mailan liikkumisen
testaaminen oli aika suoraviivaista.
- _Satunnaisuus._ Pallolle arvotaan maalin jälkeen uusi suunta. Huomataan, että
pallon suunta vaihtuu joskus maalin jälkeen, eli satunnaisuus toimii.


### Bugeja
Ainota bugit jotka olen löytänyt, liittyvät pallon kimpoamiseen.
Välillä pallo saattaa kimmota mailan "läpi", eli esimerkiksi osuessaan mailaan
alhaalta päin, se voi singota vasemmalle tai oikealle, vaikka periaatteessa sen kuuluisi kimmota takaisin alas.
Tämä on oikeastaan toivottu efekti, sillä se helpottaa pelaamista huomattavasti, mutta saattaa ajoittain
näyttää hieman hassulta.
Samoin pallo saattaa ajoittain osuessaan esteeseen olla kimpoamatta siitä, jolloin
este katoaa, ja pallo jatkaa samaa rataa.

Pallon kimpoaminen mailoista ja esteistä on toteutettu saman rajapinnan kautta,
luoden jokaiselle sivulle oman Rectangle-olion, jonka avulla voidaan tarkistaa,
onko pallo osunut siihen. Sivujen tarkistajat venyvät olion päähän asti, mikä aiheuttaa
joskus oudonnäköisen sivuille kimpoamisen.
Esteen poistettavuuden tarkistaminen sen sijaan tarkistetaan "kokonaisvaltaisen" Rectangle-olion
avulla, joka välttämättä huomaa myös kulmatapaukset, jotka jäävät sivujen "sensoreilta" huomaamatta.
Täten jos pallo osuu esteen pieneen kulmaan, joka ei ole minkään sivutarkastajan vastuulla, poistuu este,
mutta pallo ei kimpoa siitä.

Ongelman olisi voinut poistaa venyttämällä esteiden sivujen rajoja korkeammalle,
mikä olisi kuitenkin aiheuttanut ennenaikaista kimpoamista, sekä enemmän kimpoamista sivuille.
Huomiotta jäävä alue on hyvin pieni, 2x2 pikseliä, ja mielestäni esteistä epäloogisesti
sivuille kimpoaminen oli vakavamman näköistä. Jätin bugin siis sikseen.
