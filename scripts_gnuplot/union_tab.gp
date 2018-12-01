set term pdfcairo font ",8.5"		# Sortie PDF avec une font de taille 8.5
set output '../graphiques/union_tab.pdf'	

set style data histograms
set boxwidth 0.8
set title "Temps d'exécution de l'union de deux tas de même taille vers un tas min géré avec un tableau"
set ylabel "Temps de construction du tas en milliseconde"
set xlabel "Taille du tas min en nombre de clé" offset 0,-1
set style fill solid						# Les barres sont pleines
set yrange [0 to 143]						# Commence la graduation à 0
set key spacing 1.5							# Ajuste l'espace des éléments de la légende
set key left top							# Position de la légende
set xrange [0.5 to *]						# Ajuste le placement des barres

plot "../resultats/union_tab.csv" using 2:xtic(1) title "Temps d'exécution moyen" lt rgb "#00B37B",\
     "" using 3 title "Temps d'exécution maximum" lt rgb "#558DDB",\
     "" using 0:($2 + 3.0):2 with labels notitle,\
     "" using 0:($3 + 7.0):3 with labels offset 2.7 notitle				# notitle permet de ne pas avoir l'élément présent dans la légende
