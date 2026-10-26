# Přístavní adventura

Textová adventura v češtině. Hráč prochází přístavem, plní hlavní úkol „Světlo pro přístav“ a může pomoci také Vojtovi s vedlejším úkolem.

## Požadavky a spuštění

Projekt používá Javu 17 nebo novější a Maven. Z kořenového adresáře spusť:

```text
mvn test
mvn package
```

Po sestavení lze aplikaci spustit z kořenového adresáře například příkazem:

```text
java -cp target/classes main.Start
```

Produkční zdrojové soubory jsou ve standardní Maven složce `src/main/java` a testy v `src/test/java`. Balíčky (`logika`, `main`, `uiText`) zůstaly zachované.

### Spuštění testů v IntelliJ IDEA

Otevři projektový soubor `semestralka.iml` nebo projektový kořen. Zkontroluj, že složka `src/test/java` je označená jako **Test Sources Root**. V okně Project otevři `src/test/java/logika`, klikni pravým tlačítkem na `HraTest` nebo `KosicekTest` a zvol **Run**. Závislost JUnit 5 je uvedená v `pom.xml` a její IntelliJ knihovna je nakonfigurovaná v `.idea/libraries/JUnit5.xml`.

## Ovládání

Příkaz `napoveda` vypíše cíle a dostupné příkazy. Mezi důležité příkazy patří `jdi`, `mluv`, `seber`, `poloz`, `pouzij`, `dej`, `prozkoumej`, `typ`, `inventar` a `konec`. Názvy míst a předmětů lze zadávat bez diakritiky.

Příkazy `uloz cesta.txt` a `prehraj cesta.txt` ukládají a obnovují stav hry. Soubor obsahuje historii herních příkazů, z níž se při načtení bezpečně sestaví místnost, inventář, předměty v místnostech, postup úkolů i počet tahů. Historické soubory s jedním příkazem na řádek zůstávají kompatibilní. Příkaz `prehraj` nejprve ověří celý záznam; neplatný soubor aktuální hru nezmění. Prázdný nebo chybějící soubor je oznámen hráči. Cesty k souborům mohou obsahovat mezery.

Po 30 tazích hra varuje před blížící se bouří a po 40 tazích hráč prohraje. Nápověda, inventář a operace se souborem tahy nespotřebovávají.
