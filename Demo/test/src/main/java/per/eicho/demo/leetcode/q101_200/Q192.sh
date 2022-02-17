# Title: 192. Word Frequency
# Link: https://leetcode.com/problems/word-frequency/
# Read from the file words.txt and output the word frequency list to stdout.

# xargs : https://atmarkit.itmedia.co.jp/ait/articles/1801/19/news014.html
#    -n : -n 個数	--max-args=個数	1回のコマンドラインで使用する引数の上限
# sort  : https://atmarkit.itmedia.co.jp/ait/articles/1611/09/news020.html
#    -n	: --numeric-sort	文字列を数値と見なして並べ替える
#    -r : --reverse	逆順で並べ替える
# uniq  : https://atmarkit.itmedia.co.jp/ait/articles/1611/14/news021.html
#    -c : 各行の前に出現回数を出力する
# awk   : https://atmarkit.itmedia.co.jp/ait/articles/1706/15/news021.html
cat words.txt | xargs -n1 | sort | uniq -c | sort -nr | awk '{print $2" "$1}'