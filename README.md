# WrittenAmount - Numeric Value to Words Conversion
_[Celso R. Vitorino]([https://github.com/celsorv])_

The **WrittenAmount** project is a **Java** implementation that converts numeric values to words, supporting different 
languages, including Portuguese. It can handle both integer and decimal numbers, and supports the pluralization of 
scales such as thousand, million, billion, and others.

🌎 [Leia a versão em Português](./README_PT.md)

## Table of Contents

- [Main Components](#main-components)
    - [1. WrittenAmount (Abstract Class)](#1-writtenamount-abstract-class)
    - [2. LocalizedNumberFormatter (Interface)](#2-localizednumberformatter-interface)
    - [3. PortugueseWrittenAmount (Concrete Class)](#3-portuguesewrittenamount-concrete-class)
    - [4. PortugueseNumberDefinitions (Class)](#4-portuguesenumberdefinitions-class)
    - [5. NumberDefinitions (Interface)](#5-numberdefinitions-interface)
    - [6. SC (Enum)](#6-sc-enum)
- [Usage Example](#usage-example)
- [Notes](#notes)
- [Useful Links](#useful-links)

## Main Components

### 1. WrittenAmount (Abstract Class)
The main class responsible for converting numeric values into words.

- It uses the concept of groups of three (hundreds, thousands, millions, etc.) to split the number into parts 
and apply the correct formatting.
- The class has methods to handle negative numbers, pluralization, and centavos formatting.

#### Main Methods:

- `setValue(BigDecimal value)`: Defines the value to be converted.
- `convertToWords()`: Converts the numeric value into words.
- `addString(StringBuilder sb, String text)`: Adds a string to a `StringBuilder`, supporting connectors.
- `splitIntoGroupsOfThree()`: Splits the integer value into groups of three digits.
- `decomposeNumber(int value)`: Decomposes a number into hundreds, tens, and ones.
- `getScaleGroupsWord(SC sc)`: Returns the scale name (thousand, million, etc.) based on the group index.

### 2. PortugueseWrittenAmount (Concrete Class)
Implementation of the **WrittenAmount** class specifically for the Portuguese language. It handles the specifics 
of the Portuguese language, such as correctly using scales and pluralizing terms like "mil", "milhão", etc.

#### Main Methods:

- `formatWord(int groupIndex, int previousValue)`: Formats the words for each group of three digits.
- `adjustAfterFormatting()`: Adjusts the final formatting, including inserting connectors and correcting the currency label.

### 3. PortugueseNumberDefinitions (Class)
Defines the words for ones, tens, hundreds, and scales for the Portuguese language.

#### Main Methods:

- `getOnesWord(int index)`: Returns the word for the unit number.
- `getTensWord(int index)`: Returns the word for the tens number.
- `getHundredsWord(int index)`: Returns the word for the hundreds number.
- `getScaleGroupsWord(SC sc, TF tf)`: Returns the scale name (e.g., thousand, million, etc.).

### 4. NumberDefinitions (Interface)
An interface that defines methods to obtain the words representing numbers in different languages. 
Implemented specifically for each language (in this case, Portuguese).

### 5. SC (Enum)
An enum used to represent different numeric scales, such as units, thousands, millions, billions, etc.

## Usage Example

### Setting and Converting Values
To use the **PortugueseWrittenAmount** class, simply instantiate an object of this class, set the value, 
and call the `convertToWords()` method:

```java
import br.com.crv.numtowords.language.pt.PortugueseWrittenAmount;
import br.com.crv.numtowords.language.pt.PortugueseNumberDefinitions;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PortugueseNumberDefinitions numberDefinitions = new PortugueseNumberDefinitions();
        PortugueseWrittenAmount writtenAmount = new PortugueseWrittenAmount(numberDefinitions);
        
        writtenAmount.setValue(new BigDecimal("5001012000.03"));
        String result = writtenAmount.convertToWords();
        
        System.out.println(result);
        // Output: "Five Billion, One Million, Twelve Thousand Dollars and Three Cents"
    }
}
```

### Usage in Different Languages

```java
Locale locale = new Locale("es", "ES");

WrittenAmountFactory factory = new WrittenAmountFactory();
WrittenAmount writtenAmount = factory.createWrittenAmount(locale);

writtenAmount.setValue(BigDecimal.valueOf(100.00));
System.out.println(writtenAmount.convertToWords());
```

## How to Run the Demonstration

This project includes a demonstration class (Main.java) to make it easier to visualize the conversion process. You can find this class at:
`src/test/java/br/com/crv/numtowords/demo/Main.java`

To run it, simply open the project in your IDE (such as IntelliJ IDEA) and execute the main method of this class.

## Useful Links
- [Num2Word: Number to Words Converter - USD](https://num2word.com/number-to-words-usd)
- [https://math.tools/calculator/currency/words](https://math.tools/calculator/currency/words)
- [Number to Words in French](https://number-to-words.com/convert-number-to-words-in-french/)
- [Clevert: Numbers to Words Converter](https://clevert.com.br/t/en/numbers-to-words/index/fr)
- [Fluency: Números em Francês](https://fluency.io/br/blog/numeros-em-frances/?utm_content=#section-0)


## License
This project is licensed under the **Apache License 2.0** - see the [LICENSE](./LICENSE) file for more details.
