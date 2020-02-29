package per.eicho.demo.algorithm.sort.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import per.eicho.demo.algorithm.sort.MergeAscendingSortTest;
import per.eicho.demo.algorithm.sort.ParallelMergeAscendingSortTest;

@RunWith(Suite.class)
@SuiteClasses({MergeAscendingSortTest.class, ParallelMergeAscendingSortTest.class})
public class BenchMark4MergeSortAndParalleEdition {

}
