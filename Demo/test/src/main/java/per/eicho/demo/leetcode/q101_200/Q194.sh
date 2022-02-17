# Title: 194. Transpose File
# Link: https://leetcode.com/problems/transpose-file/
# Read from the file file.txt and print its transposed content to stdout.

#  headはテキストファイルの最初の10行を、tailは最後の10行を表示するコマンドです。
#  表示する行数は、オプションで変更することができます。
#  最初行: heap -1 ファイル名 
#  head [オプション] ファイル名        
#  Ref: https://atmarkit.itmedia.co.jp/ait/articles/1603/07/news023.html

# 「wc」コマンドはテキストファイルの行数や単語数（word count）、文字数を数えるコマンドです。
#  単語は、空白や改行文字で区切られたものを数えます。
#  wc [オプション] [ファイル……]
#  オプション: -w	--words	単語数を表示する
#  Ref: https://atmarkit.itmedia.co.jp/ait/articles/1611/07/news026.html
COL_NUM=`head -1 file.txt | wc -w`

# 「awk」は空白などで区切られたテキストを処理するコマンドです。
#  演算機能もあり、プログラミング言語としても使用されています。
#  awk [オプション] [コマンド] [ファイル……]
#  オプション: -v 変数名=値	変数を定義する
#  Ref: https://atmarkit.itmedia.co.jp/ait/articles/1706/15/news021.html
for ((i = 1; i <= $COL_NUM; i++)); do
    # 「xargs」は、「標準入力やファイルからリストを読み込み、
    #  コマンドラインを作成して実行する」というコマンドです。
    #  xargs [オプション] コマンド [コマンドの引数]
    #  Ref: https://atmarkit.itmedia.co.jp/ait/articles/1801/19/news014.html
    awk -v col=$i '{print $col}' file.txt | xargs
done