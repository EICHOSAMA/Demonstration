package per.eicho.demo.leetcode.q301_400;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <p>355. Design Twitter 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/design-twitter/">355. Design Twitter</a>
 */
@SuppressWarnings("unused")
public final class Q355 {
    private static final class Twitter {

        private static final class HeapNode {
            final Integer userId;
            final List<Integer> posts;
            int p = 0; // pointer

            HeapNode(Integer iUserId, List<Integer> posts) {
                this.userId = iUserId;
                this.posts = posts;
            }

            boolean hasNext() { return p < posts.size(); }
            Integer getCurrent() { return posts.get(p); }
            void move2Next() { p++; }
        }

        /** HashMap<UserId, LinkedList<postId>> */
        final Map<Integer, List<Integer>> posts;

        /** HashMap<UserId, HashSet<FollowerUserId>> */
        final Map<Integer, Set<Integer>> followers;

        /** HashMap<UserId, HashSet<FollowingUserId>> */
        final Map<Integer, Set<Integer>> followings;

        /** HashMap<postId, time (0-based)> */
        final Map<Integer, Integer> postId2Order;

        /** Initializes your twitter object. */
        public Twitter() {
            // 1 <= userId, followerId, followeeId <= 500
            followers = new HashMap<>(500);
            followings = new HashMap<>(500);

            posts = new HashMap<>(500);
            postId2Order = new HashMap<>(500);
        }
        
        /** 
         * Composes a new tweet with ID tweetId by the user userId. 
         * Each call to this function will be made with a unique tweetId. 
         */
        public void postTweet(int userId, int tweetId) {
            final Integer iUserId = userId;
            final Integer iTweetId = tweetId;

            final List<Integer> userPosts;
            if (!posts.containsKey(iUserId)) {
                userPosts = new LinkedList<>();
                posts.put(iUserId, userPosts);
            } else {
                userPosts = posts.get(iUserId);
            }

            userPosts.add(0, iTweetId);
            postId2Order.put(iTweetId , postId2Order.size());
        }
        
        /**
         * Retrieves the 10 most recent tweet IDs in the user's news feed. 
         * Each item in the news feed must be posted by users who the user followed or by the user themself. 
         * Tweets must be ordered from most recent to least recent.
         */
        public List<Integer> getNewsFeed(int userId) {
            final Integer iUserId = userId; 


            // 1. get relation userIds (following userIds + user himself)
            final Integer[] relationIds = getRelationIdsByUserId(iUserId);
            
            // 2. 
            final List<Integer> tweetIds = new LinkedList<>();
            final PriorityQueue<HeapNode> heap = new PriorityQueue<>((n1, n2) -> {
                final Integer t1 = postId2Order.get(n1.getCurrent()); // time 1
                final Integer t2 = postId2Order.get(n2.getCurrent()); // time 2
                return t2.compareTo(t1);
            });

            for (Integer id : relationIds) {
                final List<Integer> post = posts.get(id);
                if (post != null && post.size() > 0) heap.add(new HeapNode(id, post));
            }

            for (int i = 0; i < 10; i++) {
                if (heap.isEmpty()) break; // run out of data.

                final HeapNode node = heap.poll();
                tweetIds.add(node.getCurrent());
                node.move2Next();
                if (node.hasNext()) heap.add(node);
            }

            return tweetIds;
        }

        private Integer[] getRelationIdsByUserId(Integer iUserId) {
            if (!followings.containsKey(iUserId)) return new Integer[]{iUserId};
            final Set<Integer> following = followings.get(iUserId);
            final int count = following.size() + 1;
            final Integer[] userIds = new Integer[count];
            int p = 0;
            for (Integer id : following) userIds[p++] = id;
            userIds[p] = iUserId;
            return userIds;
        }
        
        /** The user with ID followerId started following the user with ID followeeId. */
        public void follow(int followerId, int followeeId) {
            final Integer iFolloweeId = followeeId;
            final Integer iFollowerId = followerId;
            
            // 1. add to followers
            final Set<Integer> followerSet = getSet(followers, iFolloweeId);
            followerSet.add(iFollowerId);

            // 2. add to following
            final Set<Integer> followingSet = getSet(followings, iFollowerId);
            followingSet.add(iFolloweeId);
        }

        private Set<Integer> getSet(Map<Integer, Set<Integer>> map, Integer userId) {
            if (!map.containsKey(userId)) {
                map.put(userId, new HashSet<>());    
            }
            return map.get(userId);
        }
        
        /** The user with ID followerId started unfollowing the user with ID followeeId. */
        public void unfollow(int followerId, int followeeId) {
            final Integer iFolloweeId = followeeId;
            final Integer iFollowerId = followerId;

            // 1. remove from followers
            final Set<Integer> followerSet = getSet(followers, iFolloweeId);
            safelyRemove(followerSet, iFollowerId);

            // 2. remove from following
            final Set<Integer> followingSet = getSet(followings, iFollowerId);
            safelyRemove(followingSet, iFolloweeId);
        }

        private void safelyRemove(Set<Integer> set, Integer key) {
            if (set.contains(key)) set.remove(key);
        } 
    }
}
