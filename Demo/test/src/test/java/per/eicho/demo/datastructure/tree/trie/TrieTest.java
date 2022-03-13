package per.eicho.demo.datastructure.tree.trie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrieTest {

    @Test
	public void testAddThenFind() {
        Trie trie = new Trie();

        assertFalse(trie.find("word"));
        trie.addWord("word");
        assertTrue(trie.find("word"));
    }

    @Test
	public void testConstructorAndFind() {
        Trie trie = new Trie(new String[]{"word"});

        assertTrue(trie.find("word"));
    }

    @Test
    public void testCaseInsensitive() {
        Trie trie = new Trie(new String[]{"WoRD"});
        
        assertTrue(trie.find("wOrd"));
    }

    @Test
    public void testAppendSameWordTwice() {
        Trie trie = new Trie(new String[]{"WoRD"});
        
        trie.addWord("word");
        assertTrue(trie.find("wOrd"));
    }

    @Test
    public void testConstructor() {
        String[] words = new String[]{"asdasdasd", "sadadasda", "sdasdasdas", "asdasdasdas", "sdafgasdasdas"};
        Trie trie = new Trie(words);

        for (String word : words) {
            assertTrue(trie.find(word));
        }
    }
    
}
