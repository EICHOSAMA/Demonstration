package per.eicho.demo.leetcode.q101_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import per.eicho.demo.leetcode.debug.OutputUtils;

/**
 * <p>126. Word Ladder II 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/word-ladder-ii/">126. Word Ladder II</a>
 */
public final class Q126 {

    List<List<String>> result;
    String endWord;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 1. build graph
        final Map<String, Set<String>> graph = new HashMap<>();
        graph.put(beginWord, new HashSet<>());
        for (String word : wordList) {
            graph.put(word, new HashSet<>());
        }

        // 1.1. Use BFS to append edges.
        final Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(beginWord)) wordSet.add(beginWord);
        wordSet.remove(beginWord);

        final Queue<String> bfsQueue = new LinkedList<>();
        bfsQueue.add(beginWord);

        final Map<String, Integer> stairInfo = new HashMap<>();
        stairInfo.put(beginWord, 0);

        while (!bfsQueue.isEmpty()) {
            final String word = bfsQueue.poll();
            final char[] sequence = word.toCharArray();
            final int stair = stairInfo.get(word);
            final Integer iNextStair = stair + 1; // opt. manual boxing int to integer

            if (word.equals(endWord)) break;

            for (int i = 0; i < sequence.length; i++) {
                final char originalChar = sequence[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    sequence[i] = c;
                    final String next = String.valueOf(sequence);
                    if (stairInfo.containsKey(next) && stairInfo.get(next).equals(iNextStair)) {
                        graph.get(word).add(next);
                    }

                    if (!wordSet.contains(next)) continue; // no such word
                    wordSet.remove(next);


                    bfsQueue.add(next);
                    stairInfo.put(next, iNextStair);
                    
                    graph.get(word).add(next);
                }
                sequence[i] = originalChar;
            }
        }

        // back tracking
        result = new LinkedList<>();
        this.endWord = endWord;
        final Set<String> mark = new HashSet<>();
        final LinkedList<String> solution = new LinkedList<>();
        solution.add(beginWord);
        mark.add(beginWord);
        if (!stairInfo.containsKey(endWord)) return result;
        search(solution, mark, graph, stairInfo.get(endWord));

        return result;
    }

    private void search(LinkedList<String> solution, Set<String> mark, Map<String, Set<String>> graph, int remain) {
        final String currentNode = solution.getLast();

        if (currentNode.equals(endWord)) {
            result.add(new ArrayList<>(solution));
            return;
        }

        if (remain == 0) return; // opt.

        final Set<String> nebors = graph.get(currentNode);

        for (String nebor : nebors) {
            if (mark.contains(nebor)) continue;

            mark.add(nebor);
            solution.add(nebor);
            search(solution, mark, graph, remain - 1);
            solution.removeLast();
            mark.remove(nebor);
        }
    }

    public static void main(String[] args) {
        Q126 q126 = new Q126();
        for (List<String> solution : q126.findLadders("cet", "ism", Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"))) {
            OutputUtils.println(solution);
        }

        // for (List<String> solution : q126.findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"))) {
        //     OutputUtils.println(solution);
        // }
    }
}
