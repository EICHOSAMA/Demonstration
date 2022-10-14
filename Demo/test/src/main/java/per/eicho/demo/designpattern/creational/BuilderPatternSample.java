package per.eicho.demo.designpattern.creational;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import per.eicho.demo.designpattern.annotation.GoFDesignPattern;

/**
 * <p>创建型模式 - 生成器模式</p>
 * <pre>
 *  生成器（builder）是非常经典的创建复杂对象的一种设计模式。
 *  配合函数式编程会使得你创建复杂对象的代码变得极其丝滑。
 * </pre>
 * 
 * <pre>
 *  典型的实际运用有很多如：
 *   - Spring Batch里的JobBuilder、
 *   - JDK里的SpringBuilder
 *   - Lombok里的@Builder（注：Lombok不推荐生产环境使用，仅限个人玩玩）
 * </pre>
 */
@GoFDesignPattern
final class BuilderPatternSample {

    /** 串行任务 */
    static class SequentialTask {
        final List<Runnable> unmodifiableSteps;

        SequentialTask(List<Runnable> steps) {
            Objects.nonNull(steps);
            unmodifiableSteps = Collections.unmodifiableList(steps);
        }

        /** 顺序执行给定的所有子任务 */
        public void execute() {
            for (Runnable step : unmodifiableSteps) step.run();
        }
    }

    /** 串行任务Builder */
    static class SequentialTaskBuilder {

        LinkedList<Runnable> steps;
        Runnable lastStep;

        SequentialTaskBuilder() {}

        /** 开始构建串行任务，调用Build方法之前必须执行本方法 */
        public SequentialTaskBuilder start(Runnable firstStep) {
            Objects.nonNull(firstStep);
            steps = new LinkedList<>();
            steps.add(firstStep);
            return this;
        }

        /** 添加一个子任务，调用本方法之前必须调用过start方法 */
        public SequentialTaskBuilder then(Runnable step) {
            Objects.nonNull(step);
            steps.add(step);
            return this;
        }

        /** 为串行任务指定最终子任务，重复调用会覆盖之前设置的最终子任务。*/
        public SequentialTaskBuilder last(Runnable lastStep) {
            Objects.nonNull(lastStep);
            this.lastStep = lastStep;
            return this;
        }

        /** 基于已给定的信息构建一个串行任务实例 */
        public SequentialTask build() {
            if (steps == null) 
                throw new IllegalStateException("请先调用start方法");
            assert(steps != null && steps.isEmpty() == false);
            if (lastStep != null) steps.add(lastStep);
            return new SequentialTask(steps);
        }
    }

    public static void main(String[] args) {
        final SequentialTaskBuilder builder = new SequentialTaskBuilder();
        final SequentialTask task = builder
            .start(() -> System.out.println("串行任务开始"))
            .then(() -> System.out.println("执行1号任务"))
            .then(() -> System.out.println("执行2号任务"))
            .last(() -> System.out.println("串行任务结束"))
            .build();
        task.execute();
    }
    
    private BuilderPatternSample() {}
}
