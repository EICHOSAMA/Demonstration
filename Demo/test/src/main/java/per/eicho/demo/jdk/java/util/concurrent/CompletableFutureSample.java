package per.eicho.demo.jdk.java.util.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * <p>CompletableFuture类的使用例</p>
 * 
 * <pre>
 *  {@link CompletableFuture}类实现了{@link CompletionStage}和{@link Future}接口。
 *  相比Future，CompletableFuture通过实现CompletionStage接口使得其能支持函数式编程。
 *  和Lambda表达式引一并与Java8引入。
 *  
 *  <b>后续计划会加入更多使用例...</b>
 * </pre>
 */
final class CompletableFutureSample {

    /**
     * <p>样例一</p>
     * <pre>
     *  异步计算任务完成后，基于前一步任务的<b>结果</b>可以继续运算
     * 
     *  thenApply(Function) : 当前线程继续执行下阶段任务 
     *  thenApplyAsync(Function) : 不继续执行下阶段任务，而是提交下阶段任务到默认Executor（ForkJoinPool无法胜任时ThreadPerTaskExecutor）
     *  thenApplyAsync(Function, Executor) : 不继续执行下阶段任务，提交下阶段任务到指定Executor
     * </pre>
     */
    static final class CFSample1 {
        static final int RESULT_COUNTER_ERROR = -1;
        static final int RESULT_TASK_CANCELLED = -2;

        static int countFiles() {
            final CompletableFuture<Integer> countFileTask = CompletableFuture
                .supplyAsync(() -> 5) // 模拟扫描A文件夹找到5个目标文件
                .thenApplyAsync(cnt -> cnt + 3); // 模拟扫描B文件夹找到3个目标
                
                try {
                    final int cnt = countFileTask.get(); // 阻塞到任务完成 
                    return cnt; // 任务成功完成，获取到文件数量。
                } catch (InterruptedException e) {
                    /* 
                     * 假设等待时可能会被“线程管理机制”叫停执行（外部中断信号）
                     * 有需要时就得叫停我们旗下的子任务。
                     * 并且对这个中断信号进行处理，要么像本例直接处理为返回值RESULT_TASK_CANCELLED
                     * 要么也可以把异常丢给Call我们的，在方法申明时就表态本方法不处理InterruptedException。
                     */

                    /*
                     * mayInterruptIfRunning: 
                     *   this value has no effect in this implementation 
                     *   because interrupts are not used to control processing.
                     */
                    final boolean PARA_NOT_USED = true; // 注意看注释
                    countFileTask.cancel(PARA_NOT_USED);
                    return RESULT_TASK_CANCELLED;
                } catch (ExecutionException e) {
                    /*
                     * 那么假设我们上面的程序在扫盘统计某类文件时，突然故障了，就会抛出这个异常。
                     * 那么我们要么套一层我们当前服务层的Exception，要么说在当前层处理了异常，返回一个
                     * 错误值给外部，根据程序设计的异常处理策略而定。
                     */
                    return RESULT_COUNTER_ERROR;
                }
                /*
                 * 至于CancellationException，因为没有暴露countFileTask给外部，
                 * 所以不会被调用cancel方法，本例这里不需要处理。
                 * 如果暴露给外部则有被cancel的风险，需要处理。
                 */
        }

        private CFSample1() {}
    }

    private CompletableFutureSample() {}

    public static void main(String[] args) {
        System.out.println(CFSample1.countFiles());
    }
}
