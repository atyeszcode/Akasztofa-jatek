# Akasztófa Játék - Java

Egy klasszikus, konzolos akasztófa játék, amely különböző nehézségi szinteket és külső fájlból beolvasott szókészletet használ.

## Funkciók
- **Három nehézségi szint:** Könnyű, Közepes, Nehéz.
- **Dinamikus szóválasztás:** A program véletlenszerűen választ szót a megfelelő `.txt` fájlból.
- **Bemeneti ellenőrzés:** Csak betűket fogad el, kezeli a kis- és nagybetűket.
- **Vizuális visszajelzés:** ASCII art segítségével jeleníti meg az akasztófát a hibás tippek után.
- **Újrajátszási lehetőség:** A játék végén eldöntheted, hogy szeretnél-e új kört indítani.

## Technika
- **Nyelv:** Java (JDK 17+ ajánlott a `switch` kifejezések miatt).
- **Adatkezelés:** `BufferedReader` a fájlok hatékony beolvasásához.
- **Algoritmus:** `String.substring()` alapú karaktercsere a kitalált betűk megjelenítéséhez.

## Használat
1. Másold le a repót.
2. Győződj meg róla, hogy a `.txt` fájlok a projekt gyökérében vannak.
3. Fordítsd le és futtasd a `Main.java` fájlt.

## Tervezett fejlesztések
- Már tippelt betűk tárolása és ellenőrzése (`Set` használatával).
- Többnyelvű szótár támogatása.
- Grafikus felület (GUI) készítése.