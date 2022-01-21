package per.eicho.demo.leetcode.q1401_1500;

/**
 * <p>1472. Design Browser History 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-browser-history/">
 *   1472. Design Browser History</a>
 */
public final class Q1472 {
    private static final class BrowserHistory {

        private class Node {
            final String url;
            Node previous;
            Node next;

            Node(String url) {
                this.url = url;
            }

        }

        final Node head;
        Node current;

        /**
         * Initializes the object with the homepage of the browser
         */
        public BrowserHistory(String homepage) {
            head = new Node(homepage);
            current = head;
        }
        
        /**
         * Visits url from the current page. It clears up all the forward history.
         * @param url
         */
        public void visit(String url) {
            current.next = new Node(url);
            current.next.previous = current;

            current = current.next;
        }
        
        /**
         * Move steps back in history. 
         * If you can only return x steps in the history and steps > x, 
         * you will return only x steps. Return the current url after moving back in history at most steps.
         */
        public String back(int steps) {
            while (steps != 0 && current.previous != null) {
                steps--;
                current = current.previous;
            }
            return current.url;
        }
        
        /**
         * Move steps forward in history. 
         * If you can only forward x steps in the history and steps > x, you will forward only x steps. 
         * Return the current url after forwarding in history at most steps.
         */
        public String forward(int steps) {
            while (steps != 0 && current.next != null) {
                steps--;
                current = current.next;
            }
            return current.url;
        }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");           // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");         // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");          // You are in "facebook.com". Visit "youtube.com"
        System.out.println(browserHistory.back(1));   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        System.out.println(browserHistory.back(1));   // You are in "facebook.com", move back to "google.com" return "google.com"
        System.out.println(browserHistory.forward(1));// You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");         // You are in "facebook.com". Visit "linkedin.com"
        System.out.println(browserHistory.forward(2));// You are in "linkedin.com", you cannot move forward any steps.
        System.out.println(browserHistory.back(2));   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        System.out.println(browserHistory.back(7));  
    }
}
