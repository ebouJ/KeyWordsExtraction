One way to summarize the contents of an article is by describing it in terms of its keywords. For example, 
an article about cats might be described by keywords such as “cats", “pet", “domesticated", and "feline".
In this question, we will develop a program that will automatically extract keywords from an article, using 
techniques from information retrieval and computational linguistics.

The intuition behind the method that we will implement is as follows. First, one way to identify potential keywords is 
to simply take words that appear frequently in the article; that is, an article about cats is likely to contain many 
occurrences of the word “cat". So, we can rank words according to how frequent they are (this is called the term frequency),
and select the top ranking words as the keywords.
Using term frequency alone, however, will not work well. In particular, there are many words which are common across all
kinds of articles in general. For examples, words such as “the", “of", “a”, “and", etc. appear very frequently in English 
texts, because they are used to help us relate different parts of a sentence to each other grammatically. These words 
would rank highly in terms of term frequency, but are not good keywords.
