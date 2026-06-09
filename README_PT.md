# WrittenAmount - Conversão de Valores Numéricos para Extenso
_[Celso R. Vitorino]([https://github.com/celsorv])_

O projeto **WrittenAmount** é uma implementação em **Java** que converte valores numéricos em escrita por extenso, 
com suporte para diferentes idiomas.Ele pode lidar com números inteiros e decimais, além de suportar a pluralização 
de escalas como mil, milhão, bilhão, etc.

🌎 [Read the English version](README.md)

## Índice

- [Principais Componentes](#principais-componentes)
   - [1. WrittenAmount (Classe Abstrata)](#1-writtenamount-classe-abstrata)
   - [2. LocalizedNumberFormatter (Interface)](#2-localizednumberformatter-interface)
   - [3. PortugueseWrittenAmount (Classe Concreta)](#3-portuguesewrittenamount-classe-concreta)
   - [4. PortugueseNumberDefinitions (Classe)](#4-portuguesenumberdefinitions-classe)
   - [5. NumberDefinitions (Interface)](#5-numberdefinitions-interface)
   - [6. SC (Enum)](#6-sc-enum)
- [Exemplo de Uso](#exemplo-de-uso)
- [Notas](#notas)
- [Links úteis](#links-úteis)

## Principais Componentes

### 1. WrittenAmount (Classe Abstrata)
A classe principal, responsável pela conversão de valores numéricos para extenso.

- Utiliza o conceito de grupos de três (centenas, milhares, milhões, etc.) para dividir o número em partes e 
aplicar a formatação correta.
- A classe possui métodos para tratar números negativos, pluralização e ajuste de formato de centavos.

#### Métodos principais:

- `setValue(BigDecimal value)`: Define o valor a ser convertido.
- `convertToWords()`: Converte o valor numérico em string de extenso.
- `addString(StringBuilder sb, String text)`: Adiciona uma string a um `StringBuilder`, com suporte a conectores.
- `splitIntoGroupsOfThree()`: Divide o valor inteiro em grupos de três dígitos.
- `decomposeNumber(int value)`: Decompõe um número em centenas, dezenas e unidades.
- `getScaleGroupsWord(SC sc)`: Retorna o nome da escala (mil, milhão, etc.) com base no índice do grupo.

### 2. PortugueseWrittenAmount (Classe Concreta)
Implementação da classe **WrittenAmount** específica para o idioma português. Lida com as particularidades da língua 
portuguesa, como a forma correta de usar as escalas e a pluralização de termos como "mil", "milhão", etc.

#### Métodos principais:

- `formatWord(int groupIndex, int previousValue)`: Formata as palavras de cada grupo de três dígitos.
- `adjustAfterFormatting()`: Ajusta a formatação final, incluindo a inserção de conectores e a correção 
do rótulo da moeda.

### 3. PortugueseNumberDefinitions (Classe)
Define as palavras para unidades, dezenas, centenas e escalas para o idioma português.

#### Métodos principais:

- `getOnesWord(int index)`: Retorna a palavra para o número da unidade.
- `getTensWord(int index)`: Retorna a palavra para o número da dezena.
- `getHundredsWord(int index)`: Retorna a palavra para o número da centena.
- `getScaleGroupsWord(SC sc, TF tf)`: Retorna o nome da escala (ex: mil, milhão, etc.).

### 4. NumberDefinitions (Interface)
Interface que define os métodos usados para obter as palavras que representam os números em diferentes idiomas. 
Implementada de forma específica para cada idioma (no exemplo, o português).

### 5. SC (Enum)
Enum utilizado para representar diferentes escalas numéricas, como unidades, milhares, milhões, bilhões, etc.

## Exemplo de Uso

### Configuração e Conversão de Valores
Para usar a classe **PortugueseWrittenAmount**, basta instanciar um objeto dessa classe, definir o valor e 
chamar o método `convertToWords()`:

```java
import br.com.crv.numtowords.language.pt.PortugueseWrittenAmount;
import br.com.crv.numtowords.language.pt.PortugueseNumberDefinitions;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        PortugueseNumberDefinitions numberDefinitions = new PortugueseNumberDefinitions();
        PortugueseWrittenAmount writtenAmount = new PortugueseWrittenAmount(numberDefinitions);
        
        writtenAmount.setValue(new BigDecimal("100118100105.01"));
        String result = writtenAmount.convertToWords();
        
        System.out.println(result);
        // Saída: "Cem Bilhões, Cento e Dezoito Milhões, Cem Mil Cento e Cinco Reais e Um Centavo"
    }
}
```

### Uso em Diferentes Idiomas

```java
Locale locale = new Locale("es", "ES");

WrittenAmountFactory factory = new WrittenAmountFactory();
WrittenAmount writtenAmount = factory.createWrittenAmount(locale);

writtenAmount.setValue(BigDecimal.valueOf(100.00));
System.out.println(writtenAmount.convertToWords());
```

## Como Executar a Demonstração
Este projeto contém uma classe de demonstração (`Main.java`) para facilitar a visualização da conversão.
Você encontrará esta classe em:
`src/test/java/br/com/crv/numtowords/demo/Main.java`

Para executá-la, basta abrir o projeto em sua IDE (como IntelliJ) e executar o método `main` desta classe.

## Links Úteis
- [Num2Word: Number to Words Converter - USD](https://num2word.com/number-to-words-usd)
- [https://math.tools/calculator/currency/words](https://math.tools/calculator/currency/words)
- [Number to Words in French](https://number-to-words.com/convert-number-to-words-in-french/)
- [Clevert: Numbers to Words Converter](https://clevert.com.br/t/en/numbers-to-words/index/fr)
- [Fluency: Números em Francês](https://fluency.io/br/blog/numeros-em-frances/?utm_content=#section-0)

## Licença
Este projeto está licenciado sob a **Apache License 2.0** - veja o arquivo [LICENSE](./LICENSE) para mais detalhes.
