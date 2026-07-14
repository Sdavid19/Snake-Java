# Snake Game

Egyszerű Snake játék Java nyelven, Swing grafikus felülettel megvalósítva.

## Funkciók

- Klasszikus Snake játékmenet
- A kígyó irányítása a billentyűzet nyílbillentyűivel
- Véletlenszerűen generált ételek
- Véletlenszerűen megjelenő kövek a pálya nehezítésére
- Pontszámok nyilvántartása
- Eredmények mentése MySQL adatbázisba
- Játékosnév és elért pontszám tárolása

## Technológiák

- Java
- Swing
- MySQL
- Docker Compose
- IntelliJ IDEA

## Projekt struktúra

```text
snake-game/
├── deployment/
│   └── compose.yaml
├── src/
│   └── ...
├── pom.xml / build.gradle
└── README.md
```

## Adatbázis indítása

A pontszámok tárolásához MySQL adatbázis szükséges.

A Docker Compose konfiguráció a `deployment` mappában található.

Indítás:

```bash
cd deployment
docker compose up -d
```

Az adatbázis elindítása után a játék képes a játékosok neveit és pontszámait eltárolni.

## Játék indítása

Nyisd meg a projektet IntelliJ IDEA-ban, majd futtasd a fő osztályt.

Példa:

```bash
Main.java
```

## Irányítás

| Billentyű | Művelet |
|-----------|----------|
| ↑ | Mozgás felfelé |
| ↓ | Mozgás lefelé |
| ← | Mozgás balra |
| → | Mozgás jobbra |

## Játékszabályok

- A játékos egy kígyót irányít a pályán.
- Az étel elfogyasztásával a kígyó hossza növekszik.
- Minden elfogyasztott étel pontot ér.
- A játék kezdetén véletlenszerű kövek jelennek meg a pályán, amelyek akadályként szolgálnak.
- A játék véget ér, ha:
  - a kígyó nekiütközik a falnak,
  - a kígyó nekiütközik saját magának,
  - a kígyó nekiütközik egy kőnek.

## Pontszámok

A játék végén az elért pontszám elmentésre kerül az adatbázisba a játékos nevével együtt.

Példa:

| Játékos | Pontszám |
|----------|----------|
| Dávid | 25 |
| Anna | 18 |
| Péter | 14 |

## Fejlesztési lehetőségek

- Toplista megjelenítése
- Több nehézségi szint
- Hanghatások
- Különböző pályák
- Mentés és visszatöltés

## Készítette

Sulyok Dávid
