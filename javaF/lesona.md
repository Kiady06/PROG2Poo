# 📘 Java – Fiche mémo (Collections, List, Streams, For-each)

---

## 1. 🧱 Les tableaux en Java

### Déclaration

```java
int[] nombres = new int[5];
```

### Initialisation directe

```java
int[] nombres = {10, 20, 30, 40};
```

### Parcours

```java
for (int i = 0; i < nombres.length; i++) {
    System.out.println(nombres[i]);
}
```

---

## 2. 🔁 For-each (équivalent de for-of)

### Syntaxe

```java
for (Type variable : collection) {
}
```

### Exemple avec int[]

```java
int[] nums = {1, 2, 3};

for (int n : nums) {
    System.out.println(n);
}
```

### Exemple avec String[]

```java
String[] mots = {"bonjour", "salut"};

for (String mot : mots) {
    System.out.println(mot);
}
```

👉 Le type doit correspondre aux éléments du tableau.

---

## 3. 📦 List en Java

### Définition

Une List est une structure de données dynamique (taille variable).

---

### Déclaration

```java
List<String> noms = new ArrayList<>();
```

### Ajout

```java
noms.add("Jean");
noms.add("Marie");
```

---

### Différence avec tableau

| Tableau     | List             |
| ----------- | ---------------- |
| Taille fixe | Taille dynamique |
| Rapide      | Flexible         |
| Basique     | Méthodes utiles  |

---

## ⚠️ Type obligatoire

```java
List<String> noms = new ArrayList<>();
```

👉 Obligatoire de préciser le type `<String>`

❌ Mauvais :

```java
List noms = new ArrayList();
```

---

## 4. 🔁 For-each avec List

```java
List<String> noms = List.of("Jean", "Marie");

for (String nom : noms) {
    System.out.println(nom);
}
```

👉 Le type dans le for-each = type de la List

---

## 5. 🧠 Collection en Java

### Définition

Une Collection est une structure qui regroupe plusieurs objets.

### Types principaux :

* List → ordonnée
* Set → unique
* Queue → file d’attente

```java
List<Integer> list;
Set<Integer> set;
```

---

## 6. 🗺️ HashMap

### Définition

Structure clé → valeur

```java
HashMap<String, Integer> ages = new HashMap<>();

ages.put("Jean", 25);
ages.put("Marie", 30);
```

### Récupération

```java
ages.get("Jean");
```

---

## 7. 🌊 Stream

### Définition

Un Stream permet de traiter des données étape par étape.

---

### Exemple

```java
List<Integer> nums = List.of(1, 2, 3, 4);

nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .forEach(System.out::println);
```

---

## 8. ⚙️ map vs collect

### map()

Transforme les éléments

```java
.map(n -> n * 2)
```

### collect()

Récupère une List

```java
.collect(Collectors.toList())
```

---

### Exemple complet

```java
List<Integer> result = nums.stream()
    .map(n -> n * 2)
    .collect(Collectors.toList());
```

👉 équivalent JS : `.map()`

⚠️ Mais en Java il faut collect pour terminer.

---

## 9. 🔥 Résumé Stream

* stream() → commencer
* filter() → filtrer
* map() → transformer
* collect() → récupérer

👉 Un Stream = pipeline de traitement

---

## 10. 🧪 JUnit (tests)

### Exemple

```java
import static org.junit.jupiter.api.Assertions.*;

@Test
void test() {
    assertEquals(4, 2 + 2);
}
```

---

## 11. ✔️ Assertions utiles

```java
assertEquals(a, b);
assertTrue(condition);
assertFalse(condition);
assertNull(obj);
assertNotNull(obj);
```

---

## 💡 Résumé global

* Tableau → fixe
* List → dynamique
* Collection → groupe d’objets
* HashMap → clé/valeur
* Stream → traitement en pipeline
* map → transformation
* collect → finalisation
* for-each → boucle simplifiée

---

## 🧠 Astuce finale

> Stream = “je décris ce que je veux faire”

> List = “je stocke des données”

---


List → stream() → STREAM (pipeline)
                 ↓
        map / filter / sorted
                 ↓
            collect()
                 ↓
            List finale