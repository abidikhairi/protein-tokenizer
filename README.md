# Protein language tokenizer

This is a tokenizer based on our paper `What is (not) a peptide: A computational approach towards understanding the language of life`.

### Available vocabularies
1.  `data/vocab-10k.json`: All words that have a coverage $> 0.1\%$ of the swissprot database.
2.  `data/vocab-38k.json`: Contains the top $10k$ words for each $k$-mer.

### How to use
Before using the tokenizer, build it with `mvn clean package -DskipTests`.
#### Tokenize a FASTA file
```bash
java -jar target/tokenizer-1.0-SNAPSHOT-jar-with-dependencies.jar \
  --inputFile path/to/sequences.fasta \
  --vocabPath path/to/vocab.json \
  --fileFormat fasta \
  --maxTokenLength 6
  --outputFile path/to/tokenized-sequences.txt
```
#### Tokenize a TSV file
```bash
java -jar target/tokenizer-1.0-SNAPSHOT-jar-with-dependencies.jar \
  --inputFile path/to/sequences.tsv \
  --vocabPath path/to/vocab.json \
  --fileFormat fasta \
  --maxTokenLength 6
  --outputFile path/to/tokenized-sequences.txt
```
When tokenizing a tsv file, the tokenizer expects the input file to have two columns `Entry` and `Sequence` (in this order).
#### Tokenize a Text file
`Not implemented yet`
