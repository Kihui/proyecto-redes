#!/bin/bash
set -eu

limit=$(ls media/ | wc -l)
counter=14
sed --in-place " /\\end{document}/ d" $1
for ((i=1;i<=limit;i++))
do
  echo "Iteración número $i"
  cmd="$(ls -tr media/ | cut -d "." -f 1 | head -n "$i" | tail -n 1)"
  text="\\includegraphics[width=\\textwidth]{${cmd/$'\n'}}"
  #sed  --in-place "$counter i $text"  >>  $1
  echo $text >> $1
  ((counter++))
done

echo "\\end{document}" >> $1
