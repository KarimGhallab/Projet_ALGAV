set term pdfcairo font ",8.5"		# Sortie PDF avec une font de taille 8.5
set output '../graphiques/consIter_tab.pdf'	

set style data histograms
set boxwidth 0.8
set title "Temps d'exécution de la construction d'un tas min géré avec un tableau"
set ylabel "Temps d'exécution en milliseconde"
set xlabel "Taille du tas min en nombre de clé" offset 0,-1
set style fill solid						# Les barres sont pleines
set yrange [0 to *]							# Commence la graduation à 0
set key spacing 1.5							# Ajuste l'espace des éléments de la légende
set key left top							# Position de la légende
set xrange [0.5 to *]						# Ajuste le placement des barres

plot "../resultats/consIter_tab.csv" using 2:xtic(1) title "Temps d'exécution moyen" lt rgb "#00B37B",\
     "" using 3 title "Temps d'exécution maximum" lt rgb "#558DDB",\
     "" using 0:($2 + 0.2):2 with labels notitle,\
     "" using 0:($3 + 0.4):3 with labels offset 2.7 notitle				# notitle permet de ne pas avoir l'élément présent dans la légende
