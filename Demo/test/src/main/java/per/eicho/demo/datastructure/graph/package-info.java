/**
 * <p>本java包将用于收录图相关的数据结构，以便参考查询。</p>
 * 
 * <pre>
 * 为了存储图所必要的信息。一般情况下我们会有两种比较常用的图的存储方式。
 * 
 *   1. 邻接矩阵   (Adjacency Matrix) 
 *   2. 邻接表     (Adjacency List)
 * 
 * 以及一些其他的一些图的存储方式。
 *   3. 十字链表   (Orthogonal linked list)
 *   4. 邻接多重表 (Adjacency Multillsts)
 *   5. 边集数组   (Edge Array)
 * </pre>
 * 
 * <pre>
 * 图也有很多种种类。本节简单介绍图的几种分类方式。
 * 
 *   1. 通过边有无方向来区分。
 *      无向图： Undirected graph, 边无方向区分。 a<-->b 的边意味着两边能互相连通。
 *      有向图： Oriented graph, 边有明确的方向区分，a->b 的边不等同于 b->a的边。
 *   2. 边的数量多少来区分。
 *      稀疏图： Sparse graph, N个节点的图，理论最大边的数量为 N * (N - 1), 当图的边的数量大大小于理论最大边的数量时
 *              可以称该图为稀疏图。
 *      密集图： Dense graph, 于稀疏图相对，当图的边的数量大致于理论最大边数量相当时，则该图被称为密集图。
 *      
 *      稀疏图/密集图一般是决定使用邻接矩阵存储图信息或邻接表存储图信息的重要因素。
 *      稀疏图通常使用邻接表来存储。
 *      反之密集图则通常使用邻接矩阵来存储。
 *   3. 根据边是否有权重（如线路长度，网路延迟等）来区分
 *      加权图：Weighted Graph, 加权图的边上都有一个权重（数值）信息。
 *      无权图：Unweighted Graph, 无权图的边上没有权重信息，例如简单存储人与人是否认识的图就是无权图。
 *      
 *      无权图可以理解为所有边相同权重的加权图，既无权图和加权图可以使用相同/类似的数据结构进行存储。
 *      也因为无权图不需要存储权重信息。相较加权图所占用空间会少一点。
 *   4. 树形图
 *      树也是图的一种，但通常为了区分用途以及适用算法/数据结构的不同，通常把树单独拎出去自成一派。
 *      但树本质是图的一种。
 * </pre>
 */
package per.eicho.demo.datastructure.graph;