# Лаборатори №4: Нэгжийн тестийн эхлэл — JUnit 5

**Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест  
**Оюутан:** Б. Мөнгөншагай  
**Оюутны код:** B232270013


## Алхам 1: Орчин бэлдэх
``` 
shagai@SHAGAI:/mnt/c/MUST/26-27A/F.CSA313/lab4$ java -version   
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1-1-22.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-22.04-Ubuntu, mixed mode, sharing)
shagai@SHAGAI:/mnt/c/MUST/26-27A/F.CSA313/lab4$ mvn -version 
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.6.87.2-microsoft-standard-wsl2", arch: "amd64", family: "unix" ```


## 3. Төслийн бүтэц

```text
lab04-junit/
├── pom.xml
├── results/
│   ├── mvn-test.txt
│   └── mvn-test-mutant.txt
├── src/
│   ├── main/
│   │   └── java/
│   │       └── mn/edu/must/sqat/
│   │           └── GradeCalculator.java
│   └── test/
│       └── java/
│           └── mn/edu/must/sqat/
│               └── GradeCalculatorTest.java
└ README.md
```

`target/`, `.idea/`, `.DS_Store` файлууд болон хавтаснуудыг `.gitignore` файлд оруулсан.

---

## 4. GradeCalculator

`GradeCalculator` класс нь дараах үндсэн хоёр функцтэй.

### `letterGrade(double score)`

Оноог дараах дүрмийн дагуу үсгэн дүнд хөрвүүлнэ.

| Оноо | Дүн |
|---|---|
| 90–100 | A |
| 80–89.99 | B |
| 70–79.99 | C |
| 60–69.99 | D |
| 0–59.99 | F |

0-ээс бага эсвэл 100-аас их оноо өгвөл `IllegalArgumentException` шиднэ.

### `totalScore(...)`

Нийт оноог дараах бүрэлдэхүүн хэсгүүдээр тооцно.

- Ирц — 10 оноо
- Лаборатори болон бие даалт — 40 оноо
- Явцын шалгалт 1 — 10 оноо
- Явцын шалгалт 2 — 10 оноо
- Шалгалт — 30 оноо
- Нийт — 100 оноо

Аль нэг үзүүлэлт сөрөг эсвэл зөвшөөрөгдсөн дээд хэмжээнээс их байвал `IllegalArgumentException` шиднэ.

---

## 5. JUnit 5 тест

`GradeCalculatorTest` класс дотор нийт **18 тестийн метод** бичсэн.

Үүнд энгийн `@Test` болон `@ParameterizedTest` тестүүд багтсан.

Тестүүдэд Arrange–Act–Assert (AAA) бүтцийг ашигласан бөгөөд `@DisplayName` ашиглан тестийн зориулалтыг ойлгомжтой нэрлэсэн.

### Хязгаарын тестүүд

Дараах чухал хязгаарын утгуудыг шалгасан:

```text
90
89.99
60
59.99
0
100
```

Мөн ердийн утгуудаас:

```text
95 → A
85 → B
75 → C
65 → D
30 → F
```

тохиолдлуудыг шалгасан.

Буруу утгын тестүүдэд `assertThrows()` ашиглан `IllegalArgumentException` зөв шидэгдэж байгаа эсэхийг шалгасан.

---

## 6. Parameterized тест

Ижил логиктой олон тестийг давхар код бичихгүйгээр шалгахын тулд `@ParameterizedTest` болон `@CsvSource` ашигласан.

### letterGrade parameterized test

Дараах утгуудыг нэг parameterized тестээр шалгасан.

```text
95    → A
90    → A
89.99 → B
80    → B
70    → C
60    → D
59.99 → F
0     → F
```

### totalScore parameterized test

`totalScore()` функцийг мөн олон төрлийн онооны комбинациар parameterized тест ашиглан шалгасан.

Ингэснээр лабораторийн шаардлагын дагуу `letterGrade` болон `totalScore` функц тус бүрд parameterized тест хэрэгжүүлсэн.

---

## 7. Тестийн эцсийн үр дүн

Тестийг дараах командаар ажиллуулсан.

```bash
mkdir -p results && mvn test 2>&1 | tee results/mvn-test.txt
```

Эцсийн үр дүн:

```text
Tests run: 28, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

- **Тестийн методын тоо:** 18
- **Tests run:** 28
- **Failures:** 0
- **Errors:** 0
- **Skipped:** 0
- **Build:** SUCCESS

Parameterized тестийн `@CsvSource` мөр бүр тусдаа test execution болж тоологддог учраас тестийн методын тоо 18 боловч Maven-ийн `Tests run` тоо 28 болсон.

Ногоон тестийн бүрэн гаралтыг:

```text
results/mvn-test.txt
```

файлаас харж болно.

---

## 8. Mutation test

Тестүүд бодит алдааг илрүүлж чадаж байгаа эсэхийг шалгахын тулд `letterGrade()` функцийн A дүнгийн хязгаарын нөхцөлд зориудаар mutation хийсэн.

Зөв нөхцөл:

```java
score >= 90
```

Mutation хийсэн нөхцөл:

```java
score > 90
```

Ингэснээр яг `90` гэсэн оноо A дүн болохоо больсон.

Mutation-ийн дараа:

```bash
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```

командыг ажиллуулсан.

`90 → A` хязгаарын тохиолдлыг шалгадаг тестүүд энэхүү зориудын алдааг илрүүлэх ёстой бөгөөд mutation тестийн бүтэлгүйтсэн гаралтыг:

```text
results/mvn-test-mutant.txt
```

файлд хадгалсан.

Mutation test хийсний дараа `score > 90` нөхцөлийг буцааж:

```java
score >= 90
```

болгон засаж, бүх тестийг дахин ажиллуулсан. Эцсийн `results/mvn-test.txt` файл нь `BUILD SUCCESS` үр дүнтэй хадгалагдсан.

---

## 9. Дүгнэлт

Энэ лабораторийн ажлаар JUnit 5 ашиглан Java програмд нэгжийн тест бичиж сурлаа. Ердийн утгуудыг шалгахаас гадна хязгаарын утгуудыг шалгах нь маш чухал болохыг `90` онооны тестээс харсан. `score >= 90` нөхцөлийг зориудаар `score > 90` болгон өөрчлөхөд 95 онооны тест алдааг илрүүлэх боломжгүй боловч яг 90 оноог шалгадаг boundary test энэхүү алдааг илрүүлэх боломжтой болсон. Энэ нь зөвхөн олон тест бичихээс илүү зөв test case сонгох нь чухал гэдгийг харуулсан. Мөн `assertThrows` ашигласнаар зөв утгуудаас гадна буруу болон хязгаараас хэтэрсэн оролтын үед програм зөв ажиллаж байгаа эсэхийг шалгасан. `@ParameterizedTest` болон `@CsvSource` ашигласнаар ижил логиктой олон оролтыг давхардсан тестийн код багатайгаар шалгах боломжтой болсон. Mutation testing нь тестүүд зөвхөн амжилттай ажиллаж байгааг бус, бодит алдааг илрүүлж чадаж байгаа эсэхийг шалгахад хэрэгтэй арга болохыг ойлгосон.