# Fouille de chroniques pour la maintenance prédictive

Ce projet de recherche s'inscrit dans le cadre des travaux de recherche menés par le laboratoire [ICube] (https://icube.unistra.fr "Site officiel du laboratoire ICube").

## Motivation

## But
Ce projet a pour but de fournir une implémentation efficace de l'algorithme Chronicle Mining for Predictive Maintenance. Basé sur la fichier _chronicle.java_ (voir tag v0), il s'agit d'automatiser l'extraction de chroniques _fermées_ et de fournir une première parallélisation (multi-threading) de l'approche. Il sera aussi question de séparer correctement les différentes unités de compilation, et de commenter correctement le code. La dernière étape de cette première partie est la tâche de visualisation des chroniques, à l'aide de bibliothèques telles que [JGraphX](https://github.com/jgraph/jgraphx).

## Système de fichiers

- Le dossier _docs_ contient des documents de références, bibliographiques mais aussi les documents produits lors de ce projet.
- Le dossier _src_ contient une implémentations de l'algorithme étudié en Java. Cette implémentation utilise une version modifiée de la bibliothèque de Data Mining [SPMF](http://www.philippe-fournier-viger.com/spmf/), la bibliothèque d'analyse de données WEKA et la bibliothèque de visualisation de graphes [JGraphX](https://github.com/jgraph/jgraphx). Pour une première version fonctionelle, on pourra consulter le tag v1.

## Exécution

L'utilisation de Maven permet l'exécution _simplifiée_. L'instruction est du type:

    Shell :
    mvn exec:java "-Dexec.mainClass=halfback.App" "-Dexec.args=-p -if data/fichier_entree.txt -of data/fichier_sortie -m 0.7"
    Windows PowerShell :
    mvn --% exec:java "-Dexec.mainClass=halfback.App" "-Dexec.args='+if' 'data\outliers_cat.txt' '+of' 'data\output\outliers_cat' '+m' '0.5'"
    Pour des IDE : 
    Il suffit d'exécuter avec les arguments, e.g. -p -if data/fichier_entree.txt -of data/fichier_sortie -m 0.7

N.B. PowerShell ne reconnaissant pas très bien les paramètres du programme (if, of, m) s'ils sont précédés par un tiret, il est possible d'utiliser des _+_ ou tout autre symbole mieux adapté en changeant la ligne 11 de la classe `halfback.Utils` (variable `ARG_CHAR`).

### Paramètres d'exécution

`if` : input file, chemin vers le fichier d'entrée.
`of` : output file, chemin vers le fichier de sortie. Cette valeur est utilisée comme racine pour plusieurs fichiers (spmf, chroniques, séquences fréquentes fermées...) 
`m` : minimum support. Nombre décimal (double) dans l'intervalle [0, 1].
`p` : exécuter les étapes de pré-traitement. Si cette option n'est pas spécifiée, le fichier spécifié par `if` doit être au format SPMF. Si cette option est spécifiée, il faut que le fichier soit en format CSV et que les deux dernières colonnes soient le timestamp (10 chiffres) et le résultat du test de défaillance (0 pour pas de problème ou 1 pour panne). Finalement, la première ligne doit contenir les noms des attributs.
(pas encore implémenté) `o` : optimized binning, optimization des intervalles de discrétisation lors du pré-traitement.
(pas encore implémenté) `b n` : utiliser _n_ intervalles de discrétisation pour les valeurs des attributs.