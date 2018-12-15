set term pdfcairo font ",6.0"		# Sortie PDF avec une font de taille 8.5
set output '../graphiques/union_tas_vs_fileBinomiale.pdf'	

set style data histograms
set boxwidth 0.8
set title "Temps d'exécution de l'union de deux structures : tableau vs file binomiale"
set ylabel "Temps d'exécution en milliseconde"
set xlabel "Taille de la structure en nombre de clé" offset 0,-1
set style fill solid						# Les barres sont pleines
set yrange [0 to *]							# Commence la graduation à 0
set key spacing 1.5							# Ajuste l'espace des éléments de la légende
set key left top							# Position de la légende
set xrange [0.5 to *]						# Ajuste le placement des barres

plot "../resultats/union_tas_vs_fileBinomiale.csv"\
	    using 2:xtic(1) title "Temps d'exécution moyen avec tableau" lt rgb "#33cc33",\
     "" using 3 title "Temps d'exécution maximum avec un tableau" lt rgb "#1f7a1f",\
     "" using 4:xtic(1) title "Temps d'exécution moyen avec une file binomiale" lt rgb "#a64dff",\
	 "" using 5 title "Temps d'exécution maximum avec une file binomiale" lt rgb "#7300e6",\
     "" using 0:($2 + 0.5):2 with labels offset -3 notitle,\
     "" using 0:($3 + 1.0):3 with labels offset 0 notitle,\
     "" using 0:($4 + 0.5):4 with labels offset 3.5 notitle,\
     "" using 0:($5 + 1.0):5 with labels offset 5 notitle				# notitle permet de ne pas avoir l'élément présent dans la légende
