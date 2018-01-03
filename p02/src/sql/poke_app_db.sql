DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS pokedex;
DROP TABLE IF EXISTS entrenador;

CREATE TABLE pokemon (
       id    INTEGER PRIMARY KEY AUTOINCREMENT,
       name  VARCHAR(32),
       url   VARCHAR(32)
);

CREATE TABLE entrenador (
       id    INTEGER PRIMARY KEY AUTOINCREMENT,
       name  VARCHAR(32)
);

CREATE TABLE pokedex (
       id_pokemon    INTEGER,
       id_entrenador INTEGER,
       counter	     INTEGER,
       PRIMARY KEY(id_pokemon, id_entrenador),
       FOREIGN KEY(id_pokemon) references pokemon(id),
       FOREIGN KEY(id_entrenador) references entrenador(id)
);

BEGIN TRANSACTION;

INSERT INTO entrenador(name) VALUES("Paulo");
INSERT INTO entrenador(name) VALUES("Iti");
INSERT INTO entrenador(name) VALUES("Palo");
INSERT INTO entrenador(name) VALUES("Chipes");

INSERT INTO pokemon(name, url) VALUES("Abra","static/Abra.png");
INSERT INTO pokemon(name, url) VALUES("Aerodactyl","static/Aerodactyl.png");
INSERT INTO pokemon(name, url) VALUES("Alakazam","static/Alakazam.png");
INSERT INTO pokemon(name, url) VALUES("Arbok","static/Arbok.png");
INSERT INTO pokemon(name, url) VALUES("Arcanine","static/Arcanine.png");
INSERT INTO pokemon(name, url) VALUES("Articuno","static/Articuno.png");
INSERT INTO pokemon(name, url) VALUES("Beedrill","static/Beedrill.png");
INSERT INTO pokemon(name, url) VALUES("Bellsprout","static/Bellsprout.png");
INSERT INTO pokemon(name, url) VALUES("Blastoise","static/Blastoise.png");
INSERT INTO pokemon(name, url) VALUES("Bulbasaur","static/Bulbasaur.png");
INSERT INTO pokemon(name, url) VALUES("Butterfree","static/Butterfree.png");
INSERT INTO pokemon(name, url) VALUES("Caterpie","static/Caterpie.png");
INSERT INTO pokemon(name, url) VALUES("Chansey","static/Chansey.png");
INSERT INTO pokemon(name, url) VALUES("Charizard","static/Charizard.png");
INSERT INTO pokemon(name, url) VALUES("Charmander","static/Charmander.png");
INSERT INTO pokemon(name, url) VALUES("Charmeleon","static/Charmeleon.png");
INSERT INTO pokemon(name, url) VALUES("Clefable","static/Clefable.png");
INSERT INTO pokemon(name, url) VALUES("Clefairy","static/Clefairy.png");
INSERT INTO pokemon(name, url) VALUES("Cloyster","static/Cloyster.png");
INSERT INTO pokemon(name, url) VALUES("Cubone","static/Cubone.png");
INSERT INTO pokemon(name, url) VALUES("Dewgong","static/Dewgong.png");
INSERT INTO pokemon(name, url) VALUES("Diglett","static/Diglett.png");
INSERT INTO pokemon(name, url) VALUES("Ditto","static/Ditto.png");
INSERT INTO pokemon(name, url) VALUES("Dodrio","static/Dodrio.png");
INSERT INTO pokemon(name, url) VALUES("Doduo","static/Doduo.png");
INSERT INTO pokemon(name, url) VALUES("Dragonair","static/Dragonair.png");
INSERT INTO pokemon(name, url) VALUES("Dragonite","static/Dragonite.png");
INSERT INTO pokemon(name, url) VALUES("Dratini","static/Dratini.png");
INSERT INTO pokemon(name, url) VALUES("Drowzee","static/Drowzee.png");
INSERT INTO pokemon(name, url) VALUES("Dugtrio","static/Dugtrio.png");
INSERT INTO pokemon(name, url) VALUES("Eevee","static/Eevee.png");
INSERT INTO pokemon(name, url) VALUES("Ekans","static/Ekans.png");
INSERT INTO pokemon(name, url) VALUES("Electabuzz","static/Electabuzz.png");
INSERT INTO pokemon(name, url) VALUES("Electrode","static/Electrode.png");
INSERT INTO pokemon(name, url) VALUES("Exeggcute","static/Exeggcute.png");
INSERT INTO pokemon(name, url) VALUES("Exeggutor","static/Exeggutor.png");
INSERT INTO pokemon(name, url) VALUES("Farfetchd","static/Farfetchd.png");
INSERT INTO pokemon(name, url) VALUES("Fearow","static/Fearow.png");
INSERT INTO pokemon(name, url) VALUES("Flareon","static/Flareon.png");
INSERT INTO pokemon(name, url) VALUES("Gastly","static/Gastly.png");
INSERT INTO pokemon(name, url) VALUES("Gengar","static/Gengar.png");
INSERT INTO pokemon(name, url) VALUES("Geodude","static/Geodude.png");
INSERT INTO pokemon(name, url) VALUES("Gloom","static/Gloom.png");
INSERT INTO pokemon(name, url) VALUES("Golbat","static/Golbat.png");
INSERT INTO pokemon(name, url) VALUES("Goldeen","static/Goldeen.png");
INSERT INTO pokemon(name, url) VALUES("Golduck","static/Golduck.png");
INSERT INTO pokemon(name, url) VALUES("Golem","static/Golem.png");
INSERT INTO pokemon(name, url) VALUES("Graveler","static/Graveler.png");
INSERT INTO pokemon(name, url) VALUES("Grimer","static/Grimer.png");
INSERT INTO pokemon(name, url) VALUES("Growlithe","static/Growlithe.png");
INSERT INTO pokemon(name, url) VALUES("Gyarados","static/Gyarados.png");
INSERT INTO pokemon(name, url) VALUES("Haunter","static/Haunter.png");
INSERT INTO pokemon(name, url) VALUES("Hitmonchan","static/Hitmonchan.png");
INSERT INTO pokemon(name, url) VALUES("Hitmonlee","static/Hitmonlee.png");
INSERT INTO pokemon(name, url) VALUES("Horsea","static/Horsea.png");
INSERT INTO pokemon(name, url) VALUES("Hypno","static/Hypno.png");
INSERT INTO pokemon(name, url) VALUES("Ivysaur","static/Ivysaur.png");
INSERT INTO pokemon(name, url) VALUES("Jigglypuff","static/Jigglypuff.png");
INSERT INTO pokemon(name, url) VALUES("Jolteon","static/Jolteon.png");
INSERT INTO pokemon(name, url) VALUES("Jynx","static/Jynx.png");
INSERT INTO pokemon(name, url) VALUES("Kabuto","static/Kabuto.png");
INSERT INTO pokemon(name, url) VALUES("Kabutops","static/Kabutops.png");
INSERT INTO pokemon(name, url) VALUES("Kadabra","static/Kadabra.png");
INSERT INTO pokemon(name, url) VALUES("Kakuna","static/Kakuna.png");
INSERT INTO pokemon(name, url) VALUES("Kangaskhan","static/Kangaskhan.png");
INSERT INTO pokemon(name, url) VALUES("Kingler","static/Kingler.png");
INSERT INTO pokemon(name, url) VALUES("Koffing","static/Koffing.png");
INSERT INTO pokemon(name, url) VALUES("Krabby","static/Krabby.png");
INSERT INTO pokemon(name, url) VALUES("Lapras","static/Lapras.png");
INSERT INTO pokemon(name, url) VALUES("Lickitung","static/Lickitung.png");
INSERT INTO pokemon(name, url) VALUES("Machamp","static/Machamp.png");
INSERT INTO pokemon(name, url) VALUES("Machoke","static/Machoke.png");
INSERT INTO pokemon(name, url) VALUES("Machop","static/Machop.png");
INSERT INTO pokemon(name, url) VALUES("Magikarp","static/Magikarp.png");
INSERT INTO pokemon(name, url) VALUES("Magmar","static/Magmar.png");
INSERT INTO pokemon(name, url) VALUES("Magnemite","static/Magnemite.png");
INSERT INTO pokemon(name, url) VALUES("Magneton","static/Magneton.png");
INSERT INTO pokemon(name, url) VALUES("Mankey","static/Mankey.png");
INSERT INTO pokemon(name, url) VALUES("Marowak","static/Marowak.png");
INSERT INTO pokemon(name, url) VALUES("Meowth","static/Meowth.png");
INSERT INTO pokemon(name, url) VALUES("Metapod","static/Metapod.png");
INSERT INTO pokemon(name, url) VALUES("Mew","static/Mew.png");
INSERT INTO pokemon(name, url) VALUES("Mewtwo","static/Mewtwo.png");
INSERT INTO pokemon(name, url) VALUES("Moltres","static/Moltres.png");
INSERT INTO pokemon(name, url) VALUES("Muk","static/Muk.png");
INSERT INTO pokemon(name, url) VALUES("Nidoking","static/Nidoking.png");
INSERT INTO pokemon(name, url) VALUES("Nidoqueen","static/Nidoqueen.png");
INSERT INTO pokemon(name, url) VALUES("NidoranF","static/NidoranF.png");
INSERT INTO pokemon(name, url) VALUES("NidoranM","static/NidoranM.png");
INSERT INTO pokemon(name, url) VALUES("Nidorina","static/Nidorina.png");
INSERT INTO pokemon(name, url) VALUES("Nidorino","static/Nidorino.png");
INSERT INTO pokemon(name, url) VALUES("Ninetales","static/Ninetales.png");
INSERT INTO pokemon(name, url) VALUES("Oddish","static/Oddish.png");
INSERT INTO pokemon(name, url) VALUES("Omanyte","static/Omanyte.png");
INSERT INTO pokemon(name, url) VALUES("Omastar","static/Omastar.png");
INSERT INTO pokemon(name, url) VALUES("Onix","static/Onix.png");
INSERT INTO pokemon(name, url) VALUES("Parasect","static/Parasect.png");
INSERT INTO pokemon(name, url) VALUES("Paras","static/Paras.png");
INSERT INTO pokemon(name, url) VALUES("Persian","static/Persian.png");
INSERT INTO pokemon(name, url) VALUES("Pidgeot","static/Pidgeot.png");
INSERT INTO pokemon(name, url) VALUES("Pidgeotto","static/Pidgeotto.png");
INSERT INTO pokemon(name, url) VALUES("Pidgey","static/Pidgey.png");
INSERT INTO pokemon(name, url) VALUES("Pikachu","static/Pikachu.png");
INSERT INTO pokemon(name, url) VALUES("Pinsir","static/Pinsir.png");
INSERT INTO pokemon(name, url) VALUES("Poliwag","static/Poliwag.png");
INSERT INTO pokemon(name, url) VALUES("Poliwhirl","static/Poliwhirl.png");
INSERT INTO pokemon(name, url) VALUES("Poliwrath","static/Poliwrath.png");
INSERT INTO pokemon(name, url) VALUES("Ponyta","static/Ponyta.png");
INSERT INTO pokemon(name, url) VALUES("Porygon","static/Porygon.png");
INSERT INTO pokemon(name, url) VALUES("Primeape","static/Primeape.png");
INSERT INTO pokemon(name, url) VALUES("Psyduck","static/Psyduck.png");
INSERT INTO pokemon(name, url) VALUES("Raichu","static/Raichu.png");
INSERT INTO pokemon(name, url) VALUES("Rapidash","static/Rapidash.png");
INSERT INTO pokemon(name, url) VALUES("Raticate","static/Raticate.png");
INSERT INTO pokemon(name, url) VALUES("Rattata","static/Rattata.png");
INSERT INTO pokemon(name, url) VALUES("Rhydon","static/Rhydon.png");
INSERT INTO pokemon(name, url) VALUES("Rhyhorn","static/Rhyhorn.png");
INSERT INTO pokemon(name, url) VALUES("Sandshrew","static/Sandshrew.png");
INSERT INTO pokemon(name, url) VALUES("Sandslash","static/Sandslash.png");
INSERT INTO pokemon(name, url) VALUES("Scyther","static/Scyther.png");
INSERT INTO pokemon(name, url) VALUES("Seadra","static/Seadra.png");
INSERT INTO pokemon(name, url) VALUES("Seaking","static/Seaking.png");
INSERT INTO pokemon(name, url) VALUES("Seel","static/Seel.png");
INSERT INTO pokemon(name, url) VALUES("Shellder","static/Shellder.png");
INSERT INTO pokemon(name, url) VALUES("Slowbro","static/Slowbro.png");
INSERT INTO pokemon(name, url) VALUES("Slowpoke","static/Slowpoke.png");
INSERT INTO pokemon(name, url) VALUES("Snorlax","static/Snorlax.png");
INSERT INTO pokemon(name, url) VALUES("Spearow","static/Spearow.png");
INSERT INTO pokemon(name, url) VALUES("Squirtle","static/Squirtle.png");
INSERT INTO pokemon(name, url) VALUES("Starmie","static/Starmie.png");
INSERT INTO pokemon(name, url) VALUES("Staryu","static/Staryu.png");
INSERT INTO pokemon(name, url) VALUES("Tangela","static/Tangela.png");
INSERT INTO pokemon(name, url) VALUES("Tauros","static/Tauros.png");
INSERT INTO pokemon(name, url) VALUES("Tentacool","static/Tentacool.png");
INSERT INTO pokemon(name, url) VALUES("Tentacruel","static/Tentacruel.png");
INSERT INTO pokemon(name, url) VALUES("Vaporeon","static/Vaporeon.png");
INSERT INTO pokemon(name, url) VALUES("Venomoth","static/Venomoth.png");
INSERT INTO pokemon(name, url) VALUES("Venonat","static/Venonat.png");
INSERT INTO pokemon(name, url) VALUES("Venusaur","static/Venusaur.png");
INSERT INTO pokemon(name, url) VALUES("Victreebel","static/Victreebel.png");
INSERT INTO pokemon(name, url) VALUES("Vileplume","static/Vileplume.png");
INSERT INTO pokemon(name, url) VALUES("Voltorb","static/Voltorb.png");
INSERT INTO pokemon(name, url) VALUES("Vulpix","static/Vulpix.png");
INSERT INTO pokemon(name, url) VALUES("Wartortle","static/Wartortle.png");
INSERT INTO pokemon(name, url) VALUES("Weedle","static/Weedle.png");
INSERT INTO pokemon(name, url) VALUES("Weepinbell","static/Weepinbell.png");
INSERT INTO pokemon(name, url) VALUES("Weezing","static/Weezing.png");
INSERT INTO pokemon(name, url) VALUES("Wigglytuff","static/Wigglytuff.png");
INSERT INTO pokemon(name, url) VALUES("Zapdos","static/Zapdos.png");
INSERT INTO pokemon(name, url) VALUES("Zubat","static/Zubat.png");


END TRANSACTION;
