# Java Concurrency and Multithreading

## Case studies

### 1. Optimizing Latency using Multiple Threads

<table>
<thead>
<tr>
<th><img src="https://github.com/mohamedsugal/Java-Concurrency-and-Multithreading/assets/32971892/4e45ba9a-6930-447c-b5e7-94ac9d6246a1" width="400"></th>
<th><img src="https://github.com/mohamedsugal/Java-Concurrency-and-Multithreading/assets/32971892/313d1671-3242-4bb6-b238-6c6268fd6e35" width="400"></th>
</tr>
</thead>
<tbody>
<tr>
<td>Flowers before re-coloring</td>
<td>Flowers after re-coloring</td>
</tr>
</tbody>
</table>

In the initial set of flowers, we observe a few stunning shades of purple amidst predominantly white blossoms. However, in the subsequent image to the right, after undergoing recoloring, all the flowers have transformed to purple. For this image processing task, we employed Java's `BufferedImage` to read the image and execute the recoloring process both sequentially—using a [single thread](https://github.com/mohamedsugal/Java-Concurrency-and-Multithreading/blob/main/optimizing-for-latency-example/src/Main.java#L71) and in parallel, utilizing [multiple threads](https://github.com/mohamedsugal/Java-Concurrency-and-Multithreading/blob/main/optimizing-for-latency-example/src/Main.java#L41). The comparison revealed a significant performance enhancement with the multi-threaded approach, which efficiently partitioned the image processing workload across the designated number of threads. Below, you will find a comprehensive performance comparison detailing these observations

<img src="https://github.com/mohamedsugal/Java-Concurrency-and-Multithreading/assets/32971892/1df38b5a-a4e7-4498-a69f-f4bfd717e986" width="850">
