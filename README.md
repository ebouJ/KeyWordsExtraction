# KeyWordsExtraction
One way to summarize the contents of an article is by describing it in terms of its keywords. For example,
an article about cats might be described by keywords such as \cats", \pet", \domesticated", and \feline".
In this question, we will develop a program that will automatically extract keywords from an article,
using techniques from information retrieval and computational linguistics.
The intuition behind the method that we will implement is as follows. First, one way to identify potential
keywords is to simply take words that appear frequently in the article; that is, an article about cats is
likely to contain many occurrences of the word \cat". So, we can rank words according to how frequent
they are (this is called the term frequency), and select the top ranking words as the keywords.
Using term frequency alone, however, will not work well. In particular, there are many words which are
common across all kinds of articles in general. For examples, words such as \the", \of", \a", \and",
etc. appear very frequently in English texts, because they are used to help us relate dierent parts of a
sentence to each other grammatically. These words would rank highly in terms of term frequency, but
are not good keywords.
To adjust for this, we will incorporate a second factor called the inverse document frequency into our
ranking. We will penalize words according to how many documents they appear in, in a collection of
Page 2
texts. So, words that appear in all or nearly all the documents, such as \the", would be heavily penalized,
and would rank lower than words that are specic to a particular article, such as \cat".
The product of the two factors above is called TF-IDF (term frequency times inverse document fre-
quency). The equation of the TF-IDF score of a word w in a document d is thus:
tfidf(w; d) = tf(w; d)  idf(w); (1)
where tf(w; d) is the number of times word w occurs in document d, and idf(w) is the IDF of word w
in a collection of texts that we have, to be dened more specically below.
We will write some code to compute the TF-IDF scores of words in an article, in order to extract
keywords from that article. TF-IDF is a very popular techniques used in information retrieval, and is a
key component of modern search engines.
To begin, download the starter code and the data set that was released along with this document. The
data set consists of 40 arbitrarily selected articles extracted from the English Wikipedia, found in the
docs subdirectory. The articles are in 40 dierent text les, numbered \1.txt", \2.txt", ... \40.txt".
You will separate your code into two classes: DocumentFrequency.java, and KeywordExtractor.java.
Your job is to implement the following methods in the specied classes. Feel free to dene helper methods
to make your code easier to understand.
2.1: DocumentFrequency.java
In DocumentFrequency.java, implement the following methods:
2.1a: extractWordsFromDocument This method takes one argument, a String which is a le name.
It reads the indicated le, and returns the words that are found in the le as a HashSet<String>. You
may use the split() method of String to get the words in the documents. A method, normalize, is
provided in the starter code that will convert words into lowercase, and remove any extra whitespace
or punctuation. You should ensure that the empty string \" is not a vocabulary item in the returned
HashSet<String>.
For example, if the method reads a document containing this sentence:
\Cats are the most popular pet in the world, and are now found in almost every place where humans
live."
then the HashSet<String> should contain the following values:
c a t s ar e the
most popular pet
in world and
now found almos t
every pl a c e where
humans l i v e
2.1b: extractDocumentFrequencies This method takes a String, which points to a directory, and
an int, which is the number of documents to read from the directory. The method should process les
in the directory, starting from \1.txt", \2.txt", etc., up to the indicated number of les, and compute
the document frequencies of all of the words in all of the les, returning the result as a HashMap<String,
Integer>. For example, if the HashMap contains the key:value pair \cat":4, it means that the word
\cat" appeared in 4 of the documents that the method read.
Page 3
2.1c: writeDocumentFrequencies This method takes two arguments: (1) A HashMap<String, Integer>
of the document frequencies of words (as produced by extractDocumentFrequencies), and (2) a String
representing a le name. The method should write the contents of the HashMap to the indicated le.
The format of the output should be such that each word is written on its own line followed by its fre-
quency, separated by a space. The words should be sorted by the default order of Strings (i.e., by the
ASCII value of the characters). You may use existing Java methods to help you sort the words. In
particular, you may nd the Collections.sort() method useful, though you will have to gure out
how to turn the keys of the HashMap into a List.
For example, the rst several lines of the HashMap might be:
almos t 3
and 10
ar e 8
cat 4
. . .
Remember to include try-catch blocks to handle exceptions during the process of le input/output.
2.1d: main In the main method, write some code that will run the above methods on the 40 text les
that we provided, and save the output document frequencies to a le called \freqs.txt". You should
pass in the directory name as an input argument to the program (e.g., run DocumentFrequency ./docs
should read the les from a directory called docs that is a subdirectory of the current directory in which
the .java le is located). The le names, and the number of les can be hardcoded. Save all output les
in the current directory.
2.2: KeywordExtractor.java
In KeywordExtractor.java, implement the following methods:
2.2a: readDocumentFrequencies This method reads a frequency le in the format stored by
writeDocumentFrequencies and returns a HashMap<String, Integer> of the document frequencies of
words.
2.2b: computeTermFrequencies This method takes a String as input, which is a le name. It
reads the contents of the indicated le and returns a HashMap<String, Integer> containing the term
frequencies of words in the le. It should make the same assumptions about getting words as
extractWordsFromDocument does.
For example, if the method reads a document containing this sentence:
\Cats are the most popular pet in the world, and are now found in almost every place where humans
live."
then the HashMap<String, Integer> should contain the following key:value pairs:
c a t s : 1 ar e : 2 the : 2
most : 1 popular : 1 pet : 1
in : 2 world : 1 and : 1
now: 1 found : 1 almos t : 1
every : 1 pl a c e : 1 where : 1
humans : 1 l i v e : 1
Page 4
2.2c: computeTFIDF This method takes three arguments: (1) a HashMap<String, Integer> of term
frequencies, (2) a HashMap<String, Integer> of document frequencies, (3) a double containing the
number of documents that we have.
This method returns a HashMap<String, Double> which contains the TF-IDF scores of all of the words
in the HashMap of term frequencies. In other words, the keys of the term frequency HashMap should
be the same as the keys of the TF-IDF HashMap that is returned.
In terms of the actual computation of the TF-IDF score, we will use the formulation of TF and TF-IDF
given above, and the following equation for the IDF:
idf(w) = log
N
df(w)
; (2)
where log(x) is the natural logarithm (base e, as provided by Math.log), N is the total number of
documents that we have (40 in our case), and df(w) is the document frequency of word w, which we
computed above.
2.2d: main In the main method, write code that will call these methods on the provided data set
in order to extract the TF-IDF scores of the words in the articles. Pass in the input folder with
the text les as a command-line argument as you did for 2.1d. In addition, use the provided method
printTopKeywords, which takes the output of computeTFIDF, and prints the top k keywords by TF-IDF
score. Generate the top 5 keywords of each of the 40 articles in the following format:
1.txt
keyword1 <TF-IDF score>
keyword2 <TF-IDF score>
keyword3 <TF-IDF score>
keyword4 <TF-IDF score>
keyword5 <TF-IDF score>
2.txt
keyword1 <TF-IDF score>
keyword2 <TF-IDF score>
keyword3 <TF-IDF score>
keyword4 <TF-IDF score>
keyword5 <TF-IDF score>
...
Save this output in a le called \output.txt". You do not need to write code to do this; just copy the
printed output from the Interactions pane to a text le.
How did your algorithm work? Write a few sentences at the bottom of the le to discuss whether the
method worked. (No longer than one paragraph.)
