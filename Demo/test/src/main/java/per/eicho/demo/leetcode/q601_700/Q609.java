package per.eicho.demo.leetcode.q601_700;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>609. Find Duplicate File in System 的题解代码 </p>
 * 
 * @see <a href="https://leetcode.com/problems/can-place-flowers/">609. Find Duplicate File in System</a>
 */
public final class Q609 {

    private static final class File {
        private final String dir;
        private final String filename;
        private final String content;

        File(String dir, String filename, String content) {
            this.dir = dir;
            this.filename = filename;
            this.content = content;
        }

        private String fullPath() {
            return dir + "/" + filename;
        }
    }

    public List<List<String>> findDuplicate(String[] paths) {
        // 1. 1 <= paths.length <= 2 * 10^4
        // 2. 1 <= paths[i].length <= 3000
        // 3. 1 <= sum(paths[i].length) <= 5 * 105
        // 4. paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
        // 5. You may assume no files or directories share the same name in the same directory.
        // 6. You may assume each given directory info represents a unique directory. 
        //    A single blank space separates the directory path and file info.
        // Follow up:
        // 1. Imagine you are given a real file system, how will you search files? DFS or BFS?
        // 2. If the file content is very large (GB level), how will you modify your solution?
        // 3. If you can only read the file by 1kb each time, how will you modify your solution?
        // 4. What is the time complexity of your modified solution? 
        //    What is the most time-consuming part and memory-consuming part of it? How to optimize?
        // 5. How to make sure the duplicated files you find are not false positive?        
        final Map<String, List<File>> map = new HashMap<>();

        for (String path : paths) {
            final List<File> fileList = parse(path);
            for (File file : fileList) {
                if (!map.containsKey(file.content)) map.put(file.content, new LinkedList<>());
                map.get(file.content).add(file);          
            }
        }

        final List<List<String>> result = new LinkedList<>();
        for (String content : map.keySet()) {
            final List<File> files = map.get(content);
            if (files.size() == 1) continue;

            final List<String> fileList = files.stream().map(File::fullPath).collect(Collectors.toList());
            result.add(fileList);
        }
        return result;
    }

    private List<File> parse(String path) {
        // root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content)
        List<File> fileList = new ArrayList<>();
        int p = 0;
        
        final StringBuilder sb = new StringBuilder();
        // 1. parse dir
        char ch;
        while ((ch = path.charAt(p)) != ' ') { 
            sb.append(ch);
            p++; 
        }
        p++;
        // assert ch == ' '
        final String dir = sb.toString();
        clear(sb);

        // 2. parse filename & file content
        final int bound = path.length();
        while (p < bound) {
            // 2.1 parse filename
            while ((ch = path.charAt(p)) != '(') {
                sb.append(ch);
                p++;
            }
            p++;
            final String filename = sb.toString();
            clear(sb);

            while ((ch = path.charAt(p)) != ')') {
                sb.append(ch);
                p++;
            }
            p += 2; // skip ") "

            final String content = sb.toString();
            clear(sb);

            fileList.add(new File(dir, filename, content));
        }
        return fileList;
    }

    private void clear(StringBuilder sb) {
        sb.delete(0, sb.length());
    }
}
